public class Main {
    public static void main(String[] args) {
        Navigator n = new Navigator();
        Quiz quiz = new Quiz();
        quiz.loadQuestionFromFile();

        QuizScreen quizScreen = new QuizScreen(n, quiz);
        n.addScreen("quiz", quizScreen);
        n.showScreen("quiz");
    }
}
