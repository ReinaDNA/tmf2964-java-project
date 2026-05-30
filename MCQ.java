// Question Type: Multiple Choice Question
// Inherits from Question to make different types of questions

public class MCQ extends Question {
    String[] options = {"A) ", "B) ", "C) ", "D) "};
    
    public MCQ(int index, String question, char correctAnswer, String[] options){
        super(index, question, correctAnswer);
        for(int i=0; i<options.length; i++){
            this.options[i] += options;
        }
    }

    @Override
    public boolean validateResponse(char choice){
        if(choice == correctAnswer){
            return true;
        }else{
            return false;
        }
    }
}
