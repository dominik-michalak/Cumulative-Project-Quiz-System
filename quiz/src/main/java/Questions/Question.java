package Questions;
import Answers.IAnswer;
import java.util.Map;

public abstract class Question<T> implements IQuestion<T> {
   private String id;
   private String question;
   private T correctAnswer;
   private int score;
   private String category;
   private Map<Character, String> answerOptions;

    public Question(String id, String question, T correctAnswer, int score, String category, Map<Character, String> answerOptions) {
        this.id = id;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.score = score;
        this.category = category;
        this.answerOptions = answerOptions;
    }

    public String getId() {
      return id;
   }

   public String getQuestion() {
      return question;
   }

   public T getCorrectAns() {
      return correctAnswer;
   }

   public int getScore() {
      return score;
   }

   public void setScore(int var1) {
      this.score = var1;
   }

   public String getCategory() {
      return this.category;
   }

   public void setCategory(String var1) {
      this.category = var1;
   }

   public void displayQuestion() {
      System.out.println("Q: " + this.question);
   }

   public boolean checkAnswer(IAnswer<T> answer) {
      if (answer == null || answer.getAnswerText() == null) {
         return false;
      }
      return answer.getAnswerText().equals(this.correctAnswer);
   }

   public String toString() {
      return String.format("[%s] %s (%d points) - Category: %s", this.id, this.question, this.score, this.category);
   }
}
