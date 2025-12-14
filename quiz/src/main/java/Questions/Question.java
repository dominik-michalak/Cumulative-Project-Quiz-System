package Questions;
import Answers.IAnswer;
import java.util.Map;

public class Question<T> implements IQuestion<T> {
   private String id;
   private String question;
   private T correctAnswer;
   private int score;
   private String category;
   private Map<Character, String> answerOptions;
   public Question(){}

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
   public String getCategory() {return this.category;}
   public void setCategory(String category) {
      this.category = category;
   }
   public Map<Character, String> getAnswerOptions() { return answerOptions; }
   public void displayQuestion() {
      System.out.println("Q: " + this.question);
      if (answerOptions != null) {
          for (Map.Entry<Character, String> entry : answerOptions.entrySet()) {
              System.out.println(entry.getKey() + ": " + entry.getValue());
          }
      }
   }
    public boolean checkAnswer(IAnswer<T> answer) {
        if (answer == null || answer.getAnswerText() == null || this.correctAnswer == null) {
            return false;
        }
        String userAns = String.valueOf(answer.getAnswerText()).trim();
        String correctAns = String.valueOf(this.correctAnswer).trim();
        return userAns.equalsIgnoreCase(correctAns);
    }
    @Override
    public String toString() {
        return String.format("[%s] %s (%d points)", this.id, this.question, this.score);
    }
}
