import java.util.*;
import java.io.*;

/**
 * Class that contains helper methods for the Review Lab
 * (method removePunctuation() was added from teacher code)
 **/
public class Review {
  
  private static HashMap<String, Double> sentiment = new HashMap<String, Double>();
  private static ArrayList<String> posAdjectives = new ArrayList<String>();
  private static ArrayList<String> negAdjectives = new ArrayList<String>();
 
  
  private static final String SPACE = " ";
  
  static{
    try {
      Scanner input = new Scanner(new File("cleanSentiment.csv"));
      while(input.hasNextLine()){
        String[] temp = input.nextLine().split(",");
        sentiment.put(temp[0],Double.parseDouble(temp[1]));
        //System.out.println("added "+ temp[0]+", "+temp[1]);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing cleanSentiment.csv");
    }
  
  
  //read in the positive adjectives in postiveAdjectives.txt
     try {
      Scanner input = new Scanner(new File("positiveAdjectives.txt"));
      while(input.hasNextLine()){
        String temp = input.nextLine().trim();
      //  System.out.println(temp);
        posAdjectives.add(temp);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    }   
 
  //read in the negative adjectives in negativeAdjectives.txt
     try {
      Scanner input = new Scanner(new File("negativeAdjectives.txt"));
      while(input.hasNextLine()){
        negAdjectives.add(input.nextLine().trim());
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing negativeAdjectives.txt");
    }   
  }
  
  /** 
   * returns a string containing all of the text in fileName (including punctuation), 
   * with words separated by a single space 
   */
  public static String textToString( String fileName )
  {  
    String temp = "";
    try {
      Scanner input = new Scanner(new File(fileName));
      
      //add 'words' in the file to the string, separated by a single space
      while(input.hasNext()){
        temp = temp + input.next() + " ";
      }
      input.close();
      
    }
    catch(Exception e){
      System.out.println("Unable to locate " + fileName);
    }
    //make sure to remove any additional space that may have been added at the end of the string.
    return temp.trim();
  }
  
  /**
   * @returns the sentiment value of word as a number between -1 (very negative) to 1 (very positive sentiment) 
   */
  public static double sentimentVal( String word )
  {
    try
    {
      return sentiment.get(word.toLowerCase());
    }
    catch(Exception e)
    {
      return 0;
    }
  }
  
  /**
   * Returns the ending punctuation of a string, or the empty string if there is none 
   */
  public static String getPunctuation( String word )
  { 
    String punc = "";
    for(int i=word.length()-1; i >= 0; i--){
      if(!Character.isLetterOrDigit(word.charAt(i))){
        punc = punc + word.charAt(i);
      } else {
        return punc;
      }
    }
    return punc;
  }
  
  /**
   * Returns the word after removing any beginning or ending punctuation
   */
  public static String removePunctuation( String word )
  {
    while(word.length() > 0 && !Character.isAlphabetic(word.charAt(0)))
    {
      word = word.substring(1);
    }
    while(word.length() > 0 && !Character.isAlphabetic(word.charAt(word.length()-1)))
    {
      word = word.substring(0, word.length()-1);
    }
    
    return word;
  }
  
  /** 
   * Randomly picks a positive adjective from the positiveAdjectives.txt file and returns it.
   */
  public static String randomPositiveAdj()
  {
    int index = (int)(Math.random() * posAdjectives.size());
    return posAdjectives.get(index);
  }
  
  /** 
   * Randomly picks a negative adjective from the negativeAdjectives.txt file and returns it.
   */
  public static String randomNegativeAdj()
  {
    int index = (int)(Math.random() * negAdjectives.size());
    return negAdjectives.get(index);
    
  }
  
  /** 
   * Randomly picks a positive or negative adjective and returns it.
   */
  public static String randomAdjective()
  {
    boolean positive = Math.random() < .5;
    if(positive){
      return randomPositiveAdj();
    } else {
      return randomNegativeAdj();
    }
  }

/** Activity 2: totalSentiment()
  * Write the code to total up the sentimentVals of each word in a review.
 */
  public static double totalSentiment(String filename, boolean giveAvg, boolean isFile)
  {
    // read in the file contents into a string using the textToString method with the filename
    String reviewString = "";
    if(isFile)
   reviewString = Review.textToString(filename);
   else
    reviewString = filename;

    int wordCount = 1;
    // set up a sentimentTotal variable
    double sentimentTotal = 0;
    String notWords = "!@#$%^&*(),.";
    String testingString = "";

    for(int i = 0; i<reviewString.length(); i++){
      boolean flag = false;
      for(int j = 0; j<notWords.length(); j++){
        if(notWords.charAt(j)==reviewString.charAt(i)){
          flag = true;
        }
      }
      if(!flag)
      testingString+=reviewString.charAt(i);

    }

    testingString = testingString.toLowerCase();

    while(testingString.indexOf(' ')>0){
     String tempWord = testingString.substring(0, testingString.indexOf(" "));
     testingString = testingString.substring(testingString.indexOf(" ")+1);
  //   System.out.println(tempWord);
     sentimentTotal += sentimentVal(tempWord);
      if(sentimentVal(tempWord)!=0)
        wordCount++;
    
    }
    sentimentTotal+=sentimentVal(testingString); //adds last word
   

  if(giveAvg)
    return sentimentTotal/wordCount;
   return sentimentTotal; 
  }


  /** Activity 2 starRating method
     Write the starRating method here which returns the number of stars for the review based on its totalSentiment.
  */
  public static int starRating(String filename)
  {
    int stars = 0;

      double sTotal = totalSentiment(filename,true,true);
      if (sTotal >= 2.5){
        stars = 5;
      } else if (sTotal >= 1 ){
        stars = 4;
      }else if (sTotal >= -0.5){
        stars = 3;
      }else if(sTotal >= -2){
        stars = 2;
      }else{
        stars = 1;
      }



    // return number of stars
    return stars; 
  }
public static String fakeReview(String fileName){
  String reviewString = Review.textToString(fileName);
  while(reviewString.indexOf('*')>=0){
    String cutString1 = reviewString.substring(reviewString.indexOf('*'));

    reviewString = reviewString.substring(0,reviewString.indexOf('*'))+ randomAdjective() + cutString1.substring(cutString1.indexOf(' '));

}

return reviewString;
}

public static String slantedReview(String fileName, boolean makeItPositive){

  String reviewString = Review.textToString(fileName);
  while(reviewString.indexOf('*')>=0){
    String adjToPick = randomNegativeAdj();
    if(makeItPositive)
      adjToPick = randomPositiveAdj();

    String cutString1 = reviewString.substring(reviewString.indexOf('*'));

    reviewString = reviewString.substring(0,reviewString.indexOf('*'))+ adjToPick + cutString1.substring(cutString1.indexOf(' '));

}

return reviewString;
}





}
