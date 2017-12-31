import java.io.*;
import java.util.*;

public class TrainingData_Initializer  {
	
//	private static Scanner inputScan;

	public static void main(String[] args) throws Exception {
		  
		  BufferedReader reader = null;
		  BufferedWriter writer = null;
		  String[] tempSentences;
		  ArrayList<String> collectionSentences = new ArrayList<String>();
		  
		  try
	        {
			    File input_file = new File("PhySolData/Physol_TaggedProblems.physol");
			    reader = new BufferedReader(new FileReader(input_file));
			    
			    String line;
			    while ((line = reader.readLine()) != null) {
			    	tempSentences = line.split(" ");
			    	for(int x=0;x<tempSentences.length;x++) {
			    		collectionSentences.add(tempSentences[x]);
			    	}
			    }
			    System.out.println("DONE INITIALIZING!");
	        } catch (IOException e)
	        {
	            System.out.println(e.getMessage());
	        }
		  	for(int x=0;x<collectionSentences.size();x++) {
		  		try {
				    File output_file_given = new File("PhySolData/IdealOutput_Given/TrainingData/0.physol");
				    File output_file_variable = new File("PhySolData/IdealOutput_Given/TrainingData/1.physol");
				    File output_file_others = new File("PhySolData/IdealOutput_Given/TrainingData/2.physol");

			  		if(collectionSentences.get(x).contains("_CD")) {
					    writer = new BufferedWriter(new FileWriter(output_file_given,true));
				    	writer.write(collectionSentences.get(x)+"\n");
				    	writer.close();
			  		}
			  		else if(collectionSentences.get(x).contains("_NN")) {
					    writer = new BufferedWriter(new FileWriter(output_file_variable,true));
				    	writer.write(collectionSentences.get(x)+"\n");
				    	writer.close();
			  		}
			  		else {
					    writer = new BufferedWriter(new FileWriter(output_file_others,true));
				    	writer.write(collectionSentences.get(x)+"\n");
				    	writer.close();
			  		}
		  			
		  		}
		  		catch(IOException e){
		  			System.out.print("Error: "+e);
		  		}
	    	}
		  	
		  
	      System.out.print("DONE SORTING!");
	  }//public static void main(String[] args) throws Exception {
}//public class Preprocessing_Phase  {

