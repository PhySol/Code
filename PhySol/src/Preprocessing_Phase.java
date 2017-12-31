import java.io.*;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class Preprocessing_Phase  {
	
	public static void main(String[] args) throws Exception {
		  
		  MaxentTagger tagger = new MaxentTagger("models/english-left3words-distsim.tagger");
		   
		  BufferedReader reader = null;
		  BufferedWriter writer = null;
		  
		  try
	        {
			    File input_file = new File("PhySolData/Physol_SampleProblems.physol");
			    File output_file = new File("PhySolData/Physol_TaggedProblems.physol");
			    reader = new BufferedReader(new FileReader(input_file));
			    writer = new BufferedWriter(new FileWriter(output_file));
		    	writer.write("");//reset file
			    
			    String line;
			    int counter = 0;
			    while ((line = reader.readLine()) != null) {
			    	counter += 1;
				    writer = new BufferedWriter(new FileWriter(output_file,true));
			    	System.out.println(counter+": "+tagger.tagString(line));
			    	writer.write(tagger.tagString(line)+"\n");
			    	writer.close();
			    }
			    System.out.println("DONE!");
	        } catch (IOException e)
	        {
	            System.out.println(e.getMessage());
	        }
		  
	      
	  }//public static void main(String[] args) throws Exception {
}//public class Preprocessing_Phase  {

