package Quizes;

import Answers.IAnswer;
import Questions.IQuestion;

public interface IQuiz {
    void addQuestion (IQuestion question);
    boolean removeQuestion (int questionId);
    IQuestion getQuestion (int index);
    java.util.List<IQuestion> getAllQuestions ();
    void userAnswers (IAnswer answer);
    int calculateScore ();
    int getTotalScore ();
    int getQuestionCount ();
    String getQuizId ();
    String getTitle ();
    void setTitle (String title);
}
