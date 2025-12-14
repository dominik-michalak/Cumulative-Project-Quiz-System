package Answers;

public class Answer<T> implements IAnswer<T> {
    private String questionId;
    private T answerText;
    public Answer() {} //for gson to read it

    public Answer(String questionId, T answerText) {
        this.questionId = questionId;
        this.answerText = answerText;
    }
    public String getQuestionId() { return questionId; }
    public T getAnswerText() { return answerText; }
    public void setAnswerText(T answerText) { this.answerText = answerText; }

    public T answer() { return answerText; }

    @Override
    public String toString() { return String.valueOf(answerText); }
}