import Answers.Answer;
import Quizes.Quiz;
import Questions.Question;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Quiz System ===\n");

        Quiz quiz = new Quiz("quiz1", "Sample Quiz");
        Question q1 = new Question("q1", "What is the capital of France?", "Paris", 10, "Geography");
        quiz.addQuestion(q1);
        
        System.out.println(quiz);
        System.out.println("\nQuestions in Quiz:");
        for (int i = 0; i < quiz.getQuestionCount(); i++) {
            System.out.println("  " + quiz.getQuestion(i));
        }
        
        System.out.println("\n=== Answer the Question ===\n");
        
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < quiz.getQuestionCount(); i++) {
            Question question = (Question) quiz.getQuestion(i);
            System.out.print(question.getQuestion() + "\nYour answer: ");
            String userAnswer = scanner.nextLine();
            
            Answer answer = new Answer(question.getId(), userAnswer);
            quiz.userAnswers(answer);
            
            if (question.checkAnswer(answer)) {
                System.out.println("Correct!\n");
            } else {
                System.out.println("Wrong! The correct answer is: " + question.getCorrectAns() + "\n");
            }
        }
        
        System.out.println("Quiz completed!");
        scanner.close();
    }
}
