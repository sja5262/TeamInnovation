package Model;


import javax.swing.*;
//import com.google.cloud.translate.Translate;
//import com.google.cloud.translate.Translate.TranslateOption;
//import com.google.cloud.translate.TranslateOptions;
//import com.google.cloud.translate.Translation;

public class Message {

    public String decryptCaesar(String informedcode)
        {
            String message, decryptedMessage = "";
		int key;
		char character;
                JTextArea allResults;
		
		message = informedcode;
                allResults = new JTextArea("");
                
                for (key = 1;key<27;++key)
                {
                    decryptedMessage = "";
		for(int i = 0; i < message.length(); ++i)
                {
			character = message.charAt(i);
			if(character >= 'a' && character <= 'z')
                        {
	            character = (char)(character - key);
	            if(character < 'a')
                    {
	                character = (char)(character + 'z' - 'a' + 1);
	            }
	            
	            decryptedMessage += character;
	        }
	        else if(character >= 'A' && character <= 'Z')
                {
	            character = (char)(character - key);
	            
	            if(character < 'A')
                    {
	                character = (char)(character + 'Z' - 'A' + 1);
	            }
	            
	            decryptedMessage += character;
	        }
	        else {
	        	decryptedMessage += character;
	        }
		//Translate translate = createTranslateService();
                //List<Detection> detections = translate.detect(ImmutableList.of(sourceText));
                //System.out.println("Langauge(s) detected:");
                //for (Detection detection : detections){
                //System.out.println("\t%s\n", dections;
                //Scanner scan = new Scanner(System.in);  
                //String word = scan.nextLine();  
                //checkEnglish checkEng = new checkEnglish();  
                //if(checkEng.wordCheck(word)){  
               
                }
                allResults.append("Caesar Key "+key +": "+decryptedMessage+"\n");
        }
//}
                return allResults.getText();
}
}