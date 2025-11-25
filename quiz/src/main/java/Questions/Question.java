package Questions;
import Answers.IAnswer;

public class Question implements IQuestion {
   private String id;
   private String question;
   private String correctAns;
   private int score;
   private String category;

   public Question(String var1, String var2, String var3, int var4, String var5) {
      this.id = var1;
      this.question = var2;
      this.correctAns = var3;
      this.score = var4;
      this.category = var5;
   }

   public String getId() {
      return this.id;
   }

   public String getQuestion() {
      return this.question;
   }

   public String getCorrectAns() {
      return this.correctAns;
   }

   public int getScore() {
      return this.score;
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

   public boolean checkAnswer(IAnswer var1) {
      return var1 != null && var1.getAnswerText() != null ? var1.getAnswerText().trim().equalsIgnoreCase(this.correctAns.trim()) : false;
   }

   public String toString() {
      return String.format("[%s] %s (%d points) - Category: %s", this.id, this.question, this.score, this.category);
   }
}
