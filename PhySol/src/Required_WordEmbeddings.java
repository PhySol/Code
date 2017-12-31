import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import java.io.File;

public class Required_WordEmbeddings {

    @SuppressWarnings({ "deprecation", "unchecked" })
	public static void main(String[] args) throws Exception {

    	File f = new File("PhySolData/PhySol_SampleProblems.physol");
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

        WordVectorSerializer.writeWordVectors(vec.lookupTable(),"PhySolData/PhysicsWordVector.physol");
        WordVectorSerializer.writeWordVectors(vec, "PhySolData/PhysicsWordVectorReadable.physol");
        System.out.println("DONE!");
        
//        @SuppressWarnings("resource")
//		Scanner inputScan = new Scanner(System.in);
//  	  	System.out.print("\n\nEnter the word: ");
//  	  	String input = inputScan.nextLine();
//        
//        Collection<String> relatedWord = vec.wordsNearest(input, 5);
//        System.out.println("5 Words Related to '"+input+"': " + relatedWord+"\n");
//        
//        String currentWord = input;
//
//        System.out.println(currentWord+"="+vec.getWordVectorMatrix(currentWord));
//        for(int x=0;x<relatedWord.size();x++) {
//        	currentWord = Iterables.get(relatedWord,x);
//            System.out.println(currentWord+"="+vec.getWordVectorMatrix(currentWord));
//           // System.out.println(vec.getWordVectorMatrix(currentWord).columns());
//        }//for(int x=0;x<relatedWord.size();x++) {
        //System.out.println(vec.lookupTable());        
        
    }//public static void main(String[] args) throws Exception {
    
}//public class Required_WordEmbeddings {
