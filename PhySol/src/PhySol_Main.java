import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.indexing.INDArrayIndex;
import org.nd4j.linalg.indexing.NDArrayIndex;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class PhySol_Main extends Given_Extractor_Temp{
	
	public static String inputProblem = null;
	public static ArrayList<String> givenEntailment = new ArrayList<String>();
	public static String requiredEntailment;
    public static String requiredKeyword;
    private static String WORD_VECTORS_PATH = "";
    private static WordVectors wordVectors;
    private static TokenizerFactory tokenizerFactory;
    private static String userDirectory = "PhySolData/";
    private static MultiLayerNetwork net;
	static Scanner sc = new Scanner(System.in);

    public static void main(String args[]){
        try {
            userDirectory = "PhySolData/";
            WORD_VECTORS_PATH = "PhySolData/PhysicsWordVector.physol";
            tokenizerFactory = new DefaultTokenizerFactory();
            tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());
            net = ModelSerializer.restoreMultiLayerNetwork(userDirectory + "PhySolModel.net");
            wordVectors = WordVectorSerializer.readWord2VecModel(new File(WORD_VECTORS_PATH));
        } catch (Exception e) {
        	System.out.println("RNN Error: "+e.getMessage());
        }
        InitializeInput();
    }//public static void main(String args[]) {
    
    
    //Given and Required Extractor
    public static void InitializeInput() { 
    	System.out.print("\nPhysics Problem: ");
    	inputProblem = sc.nextLine();
        DataSet testNews = prepareTestData(inputProblem);
        INDArray fet = testNews.getFeatureMatrix();
        INDArray predicted = net.output(fet, false);
        int arrsiz[] = predicted.shape();

        String DATA_PATH = userDirectory + "IdealOutput_Required";
        File categories = new File(DATA_PATH + File.separator + "physics_areas.physol");

        double max = 0;
        int pos = 0;
        for (int i = 0; i < arrsiz[1]; i++) {
            if (max < (double) predicted.getColumn(i).sumNumber()) {
                max = (double) predicted.getColumn(i).sumNumber();
                pos = i;
            }
        }

        try (BufferedReader brCategories = new BufferedReader(new FileReader(categories))) {
            String temp = "";
            List<String> labels = new ArrayList<>();
            while ((temp = brCategories.readLine()) != null) {
                labels.add(temp);
            }
            brCategories.close();
            Given();
            for(int x=0;x<givenValue.size();x++) {
            	givenEntailment.add(givenVariable+":"+givenValue.get(x)+":"+givenUnit.get(x));
            	//System.out.println("GIVEN:  "+givenEntailment.get(x));
            }
            requiredKeyword = labels.get(pos).split(",")[1];
            requiredEntailment = givenVariable+":"+requiredKeyword;
            //System.out.println("REQUIRED:  "+requiredEntailment);
            String[] givenParameter = ParameterHandling.ArrayListConversion(givenEntailment);
            ParameterHandling.formula_engine(givenParameter, requiredEntailment);
            
        } catch (Exception e) {
            System.out.println("File Exception : " + e.getMessage());
        }
    }//public static void InitializeInput() {

    
    
    private static DataSet prepareTestData(String i_physics) {
        List<String> physics = new ArrayList<>(1);
        int[] category = new int[1];
        physics.add(i_physics);

        List<List<String>> allTokens = new ArrayList<>(physics.size());
        int maxLength = 0;
        for (String s : physics) {
            List<String> tokens = tokenizerFactory.create(s).getTokens();
            List<String> tokensFiltered = new ArrayList<>();
            for (String t : tokens) {
                if (wordVectors.hasWord(t)) {
                	tokensFiltered.add(t);
                }
            }
            allTokens.add(tokensFiltered);
            maxLength = Math.max(maxLength, tokensFiltered.size());
        }


        INDArray features = Nd4j.create(physics.size(), wordVectors.lookupTable().layerSize(), maxLength);
        INDArray labels = Nd4j.create(physics.size(), 8, maxLength);
        INDArray featuresMask = Nd4j.zeros(physics.size(), maxLength);
        INDArray labelsMask = Nd4j.zeros(physics.size(), maxLength);

        int[] temp = new int[2];
        for (int i = 0; i < physics.size(); i++) {
            List<String> tokens = allTokens.get(i);
            temp[0] = i;
            for (int j = 0; j < tokens.size() && j < maxLength; j++) {
                String token = tokens.get(j);
                INDArray vector = wordVectors.getWordVectorMatrix(token);
                features.put(new INDArrayIndex[]{NDArrayIndex.point(i),
                        NDArrayIndex.all(),
                        NDArrayIndex.point(j)},
                    vector);

                temp[1] = j;
                featuresMask.putScalar(temp, 1.0);
            }
            int idx = category[i];
            int lastIdx = Math.min(tokens.size(), maxLength);
            labels.putScalar(new int[]{i, idx, lastIdx - 1}, 1.0);
            labelsMask.putScalar(new int[]{i, lastIdx - 1}, 1.0);
        }

        DataSet ds = new DataSet(features, labels, featuresMask, labelsMask);
        return ds;
    }//private static DataSet prepareTestData(String i_physics) {
}