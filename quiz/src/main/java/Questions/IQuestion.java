package Questions;

import Answers.IAnswer;

public interface IQuestion {
    String getId();
    String getQuestion();
    String getCorrectAns();
    int getScore();
    void setScore(int score);
    String getCategory();
    void setCategory(String category);
    void displayQuestion();
    boolean checkAnswer(IAnswer answer);
}
