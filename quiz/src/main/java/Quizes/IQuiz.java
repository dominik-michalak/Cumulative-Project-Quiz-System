package Quizes;

import Answers.IAnswer;
import Questions.IQuestion;

import java.util.List;

public interface IQuiz<T> {
    void addQuestion (IQuestion<T> question);
    boolean removeQuestion (int questionId);
    IQuestion<T> getQuestion (int index);
    List<IQuestion<T>> getAllQuestions ();
    void userAnswers (IAnswer<T> answer);
    int calculateScore ();
    int getTotalScore ();
    int getQuestionCount ();
    String getQuizId ();
    String getTitle ();
    void setTitle (String title);
}
