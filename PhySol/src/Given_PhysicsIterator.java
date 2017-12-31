import static org.nd4j.linalg.indexing.NDArrayIndex.all;
import static org.nd4j.linalg.indexing.NDArrayIndex.point;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.DataSetPreProcessor;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.INDArrayIndex;

@SuppressWarnings("serial")
public class Given_PhysicsIterator implements DataSetIterator {
    private final WordVectors wordVectors;
    private final int batchSize;
    private final int vectorSize;
    private final int truncateLength;
    private int maxLength;
    private final String dataDirectory;
    private final List<Pair<String, List<String>>> parameterClassificationData = new ArrayList<>();
    private int cursor = 0;
    private int totalPhysicsProblem = 0;
    private final TokenizerFactory tokenizerFactory;
    private int physicProblemPosition = 0;
    private final List<String> labels;
    private int currentParameterClassification = 0;

    private Given_PhysicsIterator(String dataDirectory, WordVectors wordVectors, int batchSize, int truncateLength, boolean train, TokenizerFactory tokenizerFactory)
    {
        this.dataDirectory = dataDirectory;
        this.batchSize = batchSize;
        this.vectorSize = wordVectors.getWordVector(wordVectors.vocab().wordAtIndex(0)).length;
        this.wordVectors = wordVectors;
        this.truncateLength = truncateLength;
        this.tokenizerFactory = tokenizerFactory;
        this.populateData(train);
        this.labels = new ArrayList<>();
        for (int i = 0; i < this.parameterClassificationData.size(); i++) {
            this.labels.add(this.parameterClassificationData.get(i).getKey().split(",")[1]);
        }
    }

    public static Builder Builder() {
        return new Builder();
    }

    @Override
    public DataSet next(int num) {
        if (cursor >= this.totalPhysicsProblem) throw new NoSuchElementException();
        try {
            return nextDataSet(num);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private DataSet nextDataSet(int num) throws IOException {
        List<String> physicsProblem = new ArrayList<>(num);
        int[] physicsArea = new int[num];

        for (int i = 0; i < num && cursor < totalExamples(); i++) {
            if (currentParameterClassification < parameterClassificationData.size()) {
            	physicsProblem.add(this.parameterClassificationData.get(currentParameterClassification).getValue().get(physicProblemPosition));
            	physicsArea[i] = Integer.parseInt(this.parameterClassificationData.get(currentParameterClassification).getKey().split(",")[0]);
            	currentParameterClassification++;
                cursor++;
            } else {
            	currentParameterClassification = 0;
            	physicProblemPosition++;
                i--;
            }
        }

        List<List<String>> allTokens = new ArrayList<>(physicsProblem.size());
        maxLength = 0;
        for (String s : physicsProblem) {
            List<String> tokens = tokenizerFactory.create(s).getTokens();
            List<String> tokensFiltered = new ArrayList<>();
            for (String t : tokens) {
                if (wordVectors.hasWord(t)) tokensFiltered.add(t);
            }
            allTokens.add(tokensFiltered);
            maxLength = Math.max(maxLength, tokensFiltered.size());
        }

        if (maxLength > truncateLength) maxLength = truncateLength;

        INDArray features = Nd4j.create(physicsProblem.size(), vectorSize, maxLength);
        INDArray labels = Nd4j.create(physicsProblem.size(), this.parameterClassificationData.size(), maxLength);

        INDArray featuresMask = Nd4j.zeros(physicsProblem.size(), maxLength);
        INDArray labelsMask = Nd4j.zeros(physicsProblem.size(), maxLength);

        int[] temp = new int[2];
        for (int i = 0; i < physicsProblem.size(); i++) {
            List<String> tokens = allTokens.get(i);
            temp[0] = i;

            for (int j = 0; j < tokens.size() && j < maxLength; j++) {
                String token = tokens.get(j);
                INDArray vector = wordVectors.getWordVectorMatrix(token);
                features.put(new INDArrayIndex[]{point(i),
                    all(),
                    point(j)}, vector);

                temp[1] = j;
                featuresMask.putScalar(temp, 1.0);
            }
            int idx = physicsArea[i];
            int lastIdx = Math.min(tokens.size(), maxLength);
            labels.putScalar(new int[]{i, idx, lastIdx - 1}, 1.0);
            labelsMask.putScalar(new int[]{i, lastIdx - 1}, 1.0);
        }

        DataSet ds = new DataSet(features, labels, featuresMask, labelsMask);
        return ds;
    }

    public INDArray loadFeaturesFromFile(File file, int maxLength) throws IOException {
        String news = FileUtils.readFileToString(file);
        return loadFeaturesFromString(news, maxLength);
    }

    public INDArray loadFeaturesFromString(String reviewContents, int maxLength) {
        List<String> tokens = tokenizerFactory.create(reviewContents).getTokens();
        List<String> tokensFiltered = new ArrayList<>();
        for (String t : tokens) {
            if (wordVectors.hasWord(t)) tokensFiltered.add(t);
        }
        int outputLength = Math.max(maxLength, tokensFiltered.size());

        INDArray features = Nd4j.create(1, vectorSize, outputLength);

        for (int j = 0; j < tokens.size() && j < maxLength; j++) {
            String token = tokens.get(j);
            INDArray vector = wordVectors.getWordVectorMatrix(token);
            features.put(new INDArrayIndex[]{point(0),
                all(),
                point(j)}, vector);
        }

        return features;
    }

    private void populateData(boolean train) {
        File categories = new File(this.dataDirectory + File.separator + "parameter_classification.physol");

        try (BufferedReader brCategories = new BufferedReader(new FileReader(categories))) {
            String temp = "";
            while ((temp = brCategories.readLine()) != null) {
                String curFileName = train == true ?
                    this.dataDirectory + File.separator + "TrainingData" + File.separator + temp.split(",")[0] + ".physol":
                    this.dataDirectory + File.separator + "TestData" + File.separator + temp.split(",")[0] + ".physol";
                File currFile = new File(curFileName);
                BufferedReader currBR = new BufferedReader((new FileReader(currFile)));
                String tempCurrLine = "";
                List<String> tempList = new ArrayList<>();
                while ((tempCurrLine = currBR.readLine()) != null) {
                    tempList.add(tempCurrLine);
                    this.totalPhysicsProblem++;
                }
                currBR.close();
                Pair<String, List<String>> tempPair = Pair.of(temp, tempList);
                this.parameterClassificationData.add(tempPair);
            }
            brCategories.close();
        } catch (Exception e) {
            System.out.println("Exception in reading file :" + e.getMessage());
        }
    }

    @Override
    public int totalExamples() {
        return this.totalPhysicsProblem;
    }

    @Override
    public int inputColumns() {
        return vectorSize;
    }

    @Override
    public int totalOutcomes() {
        return this.parameterClassificationData.size();
    }

    @Override
    public void reset() {
        cursor = 0;
        physicProblemPosition = 0;
        currentParameterClassification = 0;
    }

    public boolean resetSupported() {
        return true;
    }

    @Override
    public boolean asyncSupported() {
        return true;
    }

    @Override
    public int batch() {
        return batchSize;
    }

    @Override
    public int cursor() {
        return cursor;
    }

    @Override
    public int numExamples() {
        return totalExamples();
    }

    @Override
    public void setPreProcessor(DataSetPreProcessor preProcessor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getLabels() {
        return this.labels;
    }

    @Override
    public boolean hasNext() {
        return cursor < numExamples();
    }

    @Override
    public DataSet next() {
        return next(batchSize);
    }

    @Override
    public void remove() {

    }

    @Override
    public DataSetPreProcessor getPreProcessor() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public int getMaxLength() {
        return this.maxLength;
    }

    public static class Builder {
        private String dataDirectory;
        private WordVectors wordVectors;
        private int batchSize;
        private int truncateLength;
        TokenizerFactory tokenizerFactory;
        private boolean train;

        Builder() {
        }

        public Given_PhysicsIterator.Builder dataDirectory(String dataDirectory) {
            this.dataDirectory = dataDirectory;
            return this;
        }

        public Given_PhysicsIterator.Builder wordVectors(WordVectors wordVectors) {
            this.wordVectors = wordVectors;
            return this;
        }

        public Given_PhysicsIterator.Builder batchSize(int batchSize) {
            this.batchSize = batchSize;
            return this;
        }

        public Given_PhysicsIterator.Builder truncateLength(int truncateLength) {
            this.truncateLength = truncateLength;
            return this;
        }

        public Given_PhysicsIterator.Builder train(boolean train) {
            this.train = train;
            return this;
        }

        public Given_PhysicsIterator.Builder tokenizerFactory(TokenizerFactory tokenizerFactory) {
            this.tokenizerFactory = tokenizerFactory;
            return this;
        }

        public Given_PhysicsIterator build() {
            return new Given_PhysicsIterator(dataDirectory,
                wordVectors,
                batchSize,
                truncateLength,
                train,
                tokenizerFactory);
        }
    }
}