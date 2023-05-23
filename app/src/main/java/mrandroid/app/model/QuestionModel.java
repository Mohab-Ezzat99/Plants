package mrandroid.app.model;

import java.io.Serializable;

public class QuestionModel implements Serializable {

    private String question;
    private String option1;
    private String option2;
    private int answer;
    private int selectedAnswer;

    public QuestionModel(String question, String option1, String option2, int answer,int selectedAnswer) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.answer = answer;
        this.selectedAnswer = selectedAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int correctAnswer) {
        this.answer = correctAnswer;
    }

    public int getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(int selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public boolean isFieldsEmpty() {
        return question.isEmpty() || option1.isEmpty() || option2.isEmpty();
    }

    public boolean isAnswerRequired() {
        return (answer == -1);
    }

    public boolean isAnswerCorrect() {
        return (answer == selectedAnswer);
    }

    @Override
    public String toString() {
        return "QuestionModel{" +
                "question='" + question + '\'' +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", answer=" + answer +
                ", selectedAnswer=" + selectedAnswer +
                '}';
    }
}
