package Quizes;
import Questions.IQuestion;
import Answers.IAnswer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Quiz<T> implements IQuiz<T>{
    private String quizId;
    private String title;
    private List<IQuestion<T>> questions;
    private Map<String, IAnswer<T>> userAnswers;
    private int totalScore;

    public Quiz(String quizId, String title){
        this.quizId = quizId;
        this.title = title;
        this.questions = new ArrayList<>();
        this.userAnswers = new HashMap<>();
        this.totalScore = 0;
    }
    public void addQuestion(IQuestion<T> question){
        questions.add(question);
    }
    public boolean removeQuestion(int questionId){return questions.removeIf(q -> Integer.parseInt(q.getId()) == questionId);}
    public IQuestion<T> getQuestion(int index){
        if (index >= 0 && index < questions.size()){
            return questions.get(index);
        } return null;
    }
    public List<IQuestion<T>> getAllQuestions(){ return new ArrayList<>(questions); }
    public void userAnswers(IAnswer<T> answer){userAnswers.put(answer.getQuestionId(), answer);}
    public int calculateScore(){
        int score = 0;
        for (IQuestion question : questions){
            IAnswer answer = userAnswers.get(question.getId());
            if (answer != null && question.checkAnswer(answer)){
                score += question.getScore();
            }
        }
        return score;
    }
    public int getTotalScore(){ return totalScore; }
    public int getQuestionCount(){ return questions.size(); }
    public String getQuizId(){ return quizId; }
    public String getTitle(){ return title; }
    public void setTitle(String title){ this.title = title; }
    @Override
    public String toString(){
        return String.format("Quiz: %s (ID: %s, %d questions, %d points total)", title, quizId, questions.size(), calculateScore());}

}