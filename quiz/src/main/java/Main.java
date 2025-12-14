import Answers.Answer;
import Answers.IAnswer;
import FileHandling.SaveAndReadJSON;
import Questions.Question;
import Quizes.Quiz;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = "quiz_data.json";
        List<Question<Object>> questions = new ArrayList<>();

        Map<Character, String> opt1 = new HashMap<>();
        opt1.put('A', "implements"); opt1.put('B', "extends");
        questions.add(new Question<>("Q1", "Inheritance keyword?", "b", 10, "OOP", opt1));

        Map<Character, String> opt2 = new HashMap<>();
        opt2.put('A', "6"); opt2.put('B', "8");
        questions.add(new Question<>("Q2", "2^3?", "b", 5, "Math", opt2)); // Note: Integer 8

        Quiz<Object> quiz = new Quiz<>("EXAM-1", "Java Master", questions);

        System.out.println("--- STARTING QUIZ: " + quiz.getTitle() + " ---");

        for (Question<Object> q : quiz.getQuestions()) {
            q.displayQuestion();
            System.out.print("Your Answer: ");
            String input = scanner.nextLine().trim();

            Answer<Object> ans = new Answer<>(q.getId(), input);

            quiz.userAnswers(ans);

            if (q.checkAnswer(ans)) {
                System.out.println("Correct! (+" + q.getScore() + " pts)");
            } else {
                System.out.println("Wrong. Correct was: " + q.getCorrectAns());
            }
        }

        int finalScore = quiz.calculateScore();
        System.out.println("\n===========================");
        System.out.println("FINAL SCORE: " + finalScore + " / " + 15); // 15 is total possible
        System.out.println("===========================");

        System.out.println("Saving progress...");
        SaveAndReadJSON.saveQuiz(Collections.singletonList(quiz), filename);
        System.out.println("Saved to " + filename);
    }
}