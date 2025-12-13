package Answers;

public interface IAnswer<T> {
    String getQuestionId();
    T getAnswerText();
    T answer();
    void setAnswerText(T answerText);
}
