import java.util.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.lang.Thread;
/**
* This class runs the main game of the thing
* This is test documentation
*
*/
public class Zuck{
private static String head = "Mark: ";
private static ArrayList<String> markQuotes = new ArrayList<String>();

public static void main(int blockSize){

  System.out.print(Zuck.imgToString("zuck.png",blockSize," .,:-=+*#%@"));
  slowPrint(head+"Would you like to play a game? > ",50);
  Scanner james = new Scanner(System.in);
  String response = james.nextLine();
  double sentiment = Review.totalSentiment(response,true,false);
  //System.out.println();
  if(sentiment>0){
slowPrint(head+"you're gonna have a bad time...",50);
zSleep(600);
System.out.println();
slowPrint(head+"Think of a facebook service or product. I'll try to guess what it is.",50);
zSleep(2000);
System.out.println();
slowPrint("Press return to start > ",40);

james.nextLine();

System.out.println();
slowPrint(head+randomQuote(),50);
zSleep(600);
System.out.println();
slowPrint(head+"Are you thinking of a social media platform or communication app? > ",50);
  sentiment = Review.totalSentiment(james.nextLine(),true,false);
  if(sentiment>0){
    slowPrint(head+"Is this service primarily used for direct messaging? > ",50);
    sentiment = Review.totalSentiment(james.nextLine(),true,false);
    if(sentiment>0){
      slowPrint(head+randomQuote(),50);
    slowPrint(head+"Is this service Facebook Messenger? > ",50);
    sentiment = Review.totalSentiment(james.nextLine(),true,false);
    if(sentiment>0){
      epicWin();
    }else{
          slowPrint(head+"How about WhatsApp? > ",50);
    sentiment = Review.totalSentiment(james.nextLine(),true,false);
    if(sentiment>0){
      epicWin();
    }else{
        slowPrint(head+"Instagram? > ",50);
    sentiment = Review.totalSentiment(james.nextLine(),true,false);
    if(sentiment>0){
      epicWin();
    }else{
      slowPrint(head+randomQuote(),50);
      slowPrint(head+"Sorry, I'm hopelessly lost.",100);
    }}}
    }else{ //social media, not for messaging - facebook, instagram, portal perhaps?
  slowPrint(head+"Would you buy something here? > ",50);
  sentiment = Review.totalSentiment(james.nextLine(),true,false);
  if(sentiment>0){
    slowPrint(head+randomQuote(),50);
      slowPrint(head+"Is it Facebook marketplace/shops? > ",50);
  sentiment = Review.totalSentiment(james.nextLine(),true,false);
  if(sentiment>0){
epicWin();
  }else{
slowPrint(head+"Is it the instagram marketplace? > ",50);
  sentiment = Review.totalSentiment(james.nextLine(),true,false);
  if(sentiment>0){
    epicWin();
  }}
  }else{
    slowPrint(head+randomQuote(),50);
slowPrint(head+"Is it Facebook? > ",50);
  sentiment = Review.totalSentiment(james.nextLine(),true,false);
  if(sentiment>0){
epicWin();
  }else{
    slowPrint(head+"Instagram? > ",50);
  sentiment = Review.totalSentiment(james.nextLine(),true,false);
  if(sentiment>0){
    epicWin();
  }}}}}
  else{ // not social media, add to this
slowPrint(head+"Is this a physical product? > ",50);
  sentiment = Review.totalSentiment(james.nextLine(),true,false);
  if(sentiment>0){
slowPrint(head+"Is it Oculus? > ",50);
  sentiment = Review.totalSentiment(james.nextLine(),true,false);
  if(sentiment>0){
    epicWin();
  }else{
    slowPrint(head+randomQuote(),50);
    slowPrint(head+"Is it Portal? > ",50);
  sentiment = Review.totalSentiment(james.nextLine(),true,false);
  if(sentiment>0){
epicWin();
  }
  }


  }else{ // not social media, communication, or hardware
    slowPrint(head+"Is it some sort of weird, obscure augmented reality thing? > ",50);
  sentiment = Review.totalSentiment(james.nextLine(),true,false);
  if(sentiment>0){
slowPrint(head+"There's your answer I guess...",50);
  }else{
        slowPrint(head+"Is it for businesses? > ",50);
  sentiment = Review.totalSentiment(james.nextLine(),true,false);
  if(sentiment>0){
slowPrint(head+"Business advertising program?> ",50);
  sentiment = Review.totalSentiment(james.nextLine(),true,false);
  if(sentiment>0){epicWin();}
}
  }


  }

  }


  }else if(sentiment<0){
slowPrint(head+"(something ominous)",50);
  }else{
  slowPrint(head+"i don't understand.",50);
  }
}

static void epicWin(){
slowPrint(head+randomQuote(),50);
System.out.println();
slowPrint(head+" Congratulations on winning this intensely difficult challenge. Enjoy a glass of water. ",50);
System.out.println();
playDrinkAnimation(20);

}

public static void playDrinkAnimation(int blockSize){
  String[] drinkStrings = new String[32];
  for(int i = 0; i<30; i++){
  drinkStrings[i] = imgToString("drink/drink"+i+".png",blockSize," .,:-=+*##%%@@");
  System.out.println("Creating frame "+i+" of "+"29");
  }
  for(int rep = 0; rep<15; rep++){
  for(int i = 0; i<30; i++){
    clearScreen();
    System.out.println(drinkStrings[i]);
    zSleep(60);
  }}

}

  static{
 try {
      Scanner input = new Scanner(new File("zuckQuotes2.txt"));
      while(input.hasNextLine()){
        String temp = input.nextLine().trim();
        markQuotes.add(temp);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing zuckQuotes2.txt\n" + e);
    }
  }
    public static String randomQuote()
  {
    int index = (int)(Math.random() * markQuotes.size());
    return markQuotes.get(index)+"\n";
  }



public static void zSleep(int ms){
    try{Thread.sleep(ms);
    }catch(Exception e){} 
}

public static void slowPrint(String toPrint,int delay){
  for(int i = 0; i<toPrint.length(); i++){

      System.out.print(toPrint.charAt(i));
      if(toPrint.charAt(i)=='.')
      zSleep(delay*6);
      else
      zSleep(delay);
  }
}
public static void clearScreen() {  
  System.out.print("\033[H\033[2J");  
  // System.out.flush();  
}  
public static String imgToString(String fileName, int blockSize, String gradient){
  BufferedImage img;
  String finalOutput = "";
  try {
    img = ImageIO.read(new File(fileName));
    for(int i = 0; i<img.getHeight()-blockSize; i+=blockSize){
      String thisLineOutput = "";
      for(int j = 0; j<img.getWidth()-blockSize; j+=blockSize){
        int total = 0;
        int sampleCount = 0;
        for(int iSm = 0; iSm<blockSize; iSm++){
          for(int jSm = 0; jSm<blockSize; jSm++){
            int rgb=img.getRGB(j+jSm,i+iSm);
          int r=(rgb>>16) & 0xff; //a byte shift? not entirely sure how this works
            int g=(rgb>>8) & 0xff;
            int b=(rgb) & 0xff;
            total+=r+g+b; 
            sampleCount++; //uses this to average pixels in 15x15 block
          }
        }
      int val = (int)(Math.floor(0.5+ (double)total/sampleCount/(255*3)*gradient.length()-1));//shrinks from 0-765 to 0-gradient.length
      for(int w = 0; w<2; w++)
      if(val>=0) //sometimes this ends up as -1 for some reason?
      thisLineOutput+= gradient.charAt(val);
      else
      thisLineOutput+= gradient.charAt(0);
    }
  //  System.out.println(thisLineOutput);
    finalOutput+= thisLineOutput + "\n";
    }
    //System.out.println(img);

} catch (IOException e) {return "that didn't work.";}
return finalOutput;
}


}
