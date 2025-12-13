package Answers;
public abstract class Answer<T> implements IAnswer <T>{
    private String questionId;
    private T answerText;
    public Answer(String questionId, T answerText){
        this.questionId = questionId;
        this.answerText = answerText;
    }
    public String getQuestionId(){ return questionId; }
    public T getAnswerText(){ return answerText; }
    public void setAnswerText(T answerText){ this.answerText = answerText; }
    @Override
    public String toString(){
        return String.format("Answer for Q%s: %s", questionId, answerText);
    }
}