import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class Preprocessing_Phase  {
	
	private static Scanner inputScan;

	public static void main(String[] args) throws Exception {
		  
		  String serializedClassifier = "models/english.all.3class.distsim.crf.ser.gz";
		  AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);
		  MaxentTagger tagger = new MaxentTagger("models/english-left3words-distsim.tagger");
		  
		  ArrayList<String> problem_words = new ArrayList<String>();
		  ArrayList<String> problem_tags = new ArrayList<String>();
		  inputScan = new Scanner(System.in);
		  
		  System.out.print("\nEnter the word problem: ");
		  String input = inputScan.nextLine();
		  String[] input2 = {input};
		  System.out.println("");
		  
		  for (String str : input2) {
			  System.out.print(classifier.classifyToString(str, "slashTags", false));
		  }
		  System.out.println("");
		  
		  String inputString = tagger.tagString(input);
		  Matcher tag = Pattern.compile("_(\\w+)").matcher(inputString);
		  Matcher word = Pattern.compile("(\\w+|\\W)_").matcher(inputString);
		  
		  while(word.find()) {
			  problem_words.add(word.group(0).replaceFirst(".$", ""));
			  //System.out.println(word.group(0).replaceFirst(".$", ""));
		  }
		  System.out.println("");
		  
		  String lineFromFile;
		  Pattern pattern;
		  while (tag.find()) {
		    //System.out.print(tag.group(1)+" ");
			pattern = Pattern.compile("\\b"+tag.group(1)+"\\b");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(new FileReader("models/lookUp.physol"));
			@SuppressWarnings("unused")
			int counter = 0;
		    while (scanner.hasNextLine()) {
		    	 counter += 1 ;
				 lineFromFile = scanner.nextLine();
				 Matcher m = pattern.matcher(lineFromFile);
			     while(m.find()) { 
			    	 problem_tags.add(tag.group(1));
			         //System.out.println("I found " +tag.group(1) + " in line number "+counter);
			     }//if(lineFromFile.contains(name)) { 
			  }//while (scanner.hasNextLine()) {
		  }//while (matcher.find()) {
		 
		  int count = 0; 		
	      while (problem_tags.size() > count) {
		  System.out.print(problem_words.get(count)+" : ");
		  System.out.println(problem_tags.get(count));
	      count++;
	      }
	      
	  }//public static void main(String[] args) throws Exception {
}//public class Preprocessing_Phase  {

