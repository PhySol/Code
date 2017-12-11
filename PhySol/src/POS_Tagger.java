//import edu.stanford.nlp.util.logging.Redwood;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.List;
//
//import edu.stanford.nlp.ling.SentenceUtils;
//import edu.stanford.nlp.ling.TaggedWord;
//import edu.stanford.nlp.ling.HasWord;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import com.sun.xml.internal.ws.util.StringUtils;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class POS_Tagger  {

  public static void main(String[] args) throws Exception {
	  MaxentTagger tagger = new MaxentTagger("models/english-left3words-distsim.tagger");
	  Scanner inputScan = new Scanner(System.in);
	  System.out.print("Enter the word problem: ");
	  String input = inputScan.nextLine();
	  String inputString = tagger.tagString(input);
	  Matcher tag = Pattern.compile("_(\\w+)").matcher(inputString);
	  Matcher word = Pattern.compile("(\\w+|\\W)_").matcher(inputString);
	  while(word.find()) {
		  System.out.println(word.group(0));
	  }
	  
	  
	  String lineFromFile;
	  Pattern pattern;
	  while (tag.find()) {
	    System.out.print(tag.group(1)+" ");
		pattern = Pattern.compile("\\b"+tag.group(1)+"\\b");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(new FileReader("models/lookUp.physol"));
		int counter = 0;
	    while (scanner.hasNextLine()) {
	    	 counter += 1 ;
			 lineFromFile = scanner.nextLine();
			 Matcher m = pattern.matcher(lineFromFile);
		     while(m.find()) { 
		         System.out.println("I found " +tag.group(1) + " in line number "+counter);
		     }//if(lineFromFile.contains(name)) { 
		  }//while (scanner.hasNextLine()) {
	  }//while (matcher.find()) {
	 
	  
  }//public static void main(String[] args) throws Exception {
}//public class POS_Tagger  {

