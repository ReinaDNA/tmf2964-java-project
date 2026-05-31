public class TestQuiz {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.loadQuestionFromFile();
        
        // Quick validation
        System.out.println("=== Loaded " + quiz.getQuestionBank().size() + " questions ===");
        
        for(InterfaceQuestion q : quiz.getQuestionBank()) {
            System.out.println("Q" + q.getIndex() + ": " + q.getQuestion());
            System.out.println("Points: " + q.getPointValue());
            System.out.println("---");
        }
        
        // Basic assertions
        assert quiz.getQuestionBank().size() > 0 : "Should have loaded questions!";
        System.out.println("Basic test passed!");
    }
}