// Quiz class to generate questions
// Created by:

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Quiz {
     public void loadQuestionFromFile(){
        try{
            File qBank = new File("test.txt");
            Scanner qLoader = new Scanner(qBank);

            while(qLoader.hasNextLine()){
                String data = qLoader.nextLine();
                System.out.println(data);
            }

        }catch(FileNotFoundException e){
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }
}
