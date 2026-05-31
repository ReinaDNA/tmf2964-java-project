// Question Type: Multiple Choice Question
// Inherits from Question to make different types of questions

public class MCQ extends Question {
    String[] optionsDesc = {"A) ", "B) ", "C) ", "D) "};
    char[] options = {'a', 'b', 'c', 'd'};
    char correctAnswer;
    

    public MCQ(int index, String question, int pointValue, String[] optionsDesc, char correctAnswer){
        super(index, question, pointValue);
        this.correctAnswer = correctAnswer; 
        for(int i=0; i< optionsDesc.length; i++){
            this.optionsDesc[i] += optionsDesc[i];
        }
    }

    @Override
    public void validateResponse(){
        // Check if user is inputting correctly or no
    }
}
