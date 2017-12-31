import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class Given_Extractor_Temp {

	public static String givenVariable = "Obj";
	public static ArrayList<String> givenValue = new ArrayList<String>();
	public static ArrayList<String> givenUnit = new ArrayList<String>();
	
	public static void Given(){

		  MaxentTagger tagger = new MaxentTagger("models/english-left3words-distsim.tagger");
		  BufferedReader reader = null;
		  String line = null, tempLine=null;

		  System.out.println("");
		  String inputString = tagger.tagString(PhySol_Main.inputProblem);
		  //System.out.println(inputString);
		  String[] rawInput = inputString.split(" ");
		  String[] normalizer;
		  for(int x=0; x<rawInput.length; x++) {
			  if(rawInput[x].contains("_CD")) {
				  if(rawInput[x+1].contains("_NN")) {
					  normalizer = rawInput[x].split("_");
					  givenValue.add(normalizer[0]);
					  normalizer = rawInput[x+1].split("_");
					  givenUnit.add(normalizer[0]);
				  }
			  }
			  else if(rawInput[x].contains("_NN")) {
				  normalizer = rawInput[x].split("_");
				  if(!givenUnit.contains(normalizer[0])) {
					  try {
						  File file = new File("PhySolData/IdealOutput_Required/physics_areas.physol");
						  reader = new BufferedReader(new FileReader(file));
						  while ((tempLine = reader.readLine()) != null) {
							  line += tempLine;
						  }
						  if(!line.contains(normalizer[0])) {
							  givenVariable = normalizer[0];
						  }
					  }
					  catch (IOException e){
						  System.out.println("Error: "+e);
					  }
				  }
			  }
		  }//for(int x=0; x<rawInput.length; x++) {
	}//public static void Given(){

}//public class Given_Extractor_Temp {
