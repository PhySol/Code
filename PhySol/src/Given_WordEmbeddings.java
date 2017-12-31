import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import java.io.File;

public class Given_WordEmbeddings {

    @SuppressWarnings({ "deprecation", "unchecked" })
	public static void main(String[] args) throws Exception {

    	File f = new File("PhySolData/Physol_TaggedProblems.physol");
        String filePath = f.getAbsolutePath();
        SentenceIterator iter = new BasicLineIterator(filePath);
        TokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());

        Word2Vec vec = new Word2Vec.Builder()
        		.minWordFrequency(2)
                .iterations(10)
                .layerSize(100)
                .seed(42)
                .windowSize(20)
                .iterate(iter)
                .tokenizerFactory(t)
                .build();
        vec.fit();

        WordVectorSerializer.writeWordVectors(vec.lookupTable(),"PhySolData/PhysicsWordVectorGiven.physol");
        WordVectorSerializer.writeWordVectors(vec, "PhySolData/PhysicsWordVectorGivenReadable.physol");
        System.out.println("DONE!");
                
    }//public static void main(String[] args) throws Exception {
    
}//public class Given_WordEmbeddings {
