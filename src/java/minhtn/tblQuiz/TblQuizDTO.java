/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.tblQuiz;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author minhv
 */
public class TblQuizDTO implements Serializable {
    private String quizId;
    private Date dateDidQuiz;
    private double score;
    private int numberOfCorrectAnswer;
    private int timeDoQuiz;
    private int numberQuestionOfQuiz;
    private String subId;
    private String email;

    public TblQuizDTO() {
    }

    public TblQuizDTO(String quizId, Date dateDidQuiz, double score, int numberOfCorrectAnswer, int timeDoQuiz, int numberQuestionOfQuiz, String subId, String email) {
        this.quizId = quizId;
        this.dateDidQuiz = dateDidQuiz;
        this.score = score;
        this.numberOfCorrectAnswer = numberOfCorrectAnswer;
        this.timeDoQuiz = timeDoQuiz;
        this.numberQuestionOfQuiz = numberQuestionOfQuiz;
        this.subId = subId;
        this.email = email;
    }

    /**
     * @return the quizId
     */
    public String getQuizId() {
        return quizId;
    }

    /**
     * @param quizId the quizId to set
     */
    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    /**
     * @return the dateDidQuiz
     */
    public Date getDateDidQuiz() {
        return dateDidQuiz;
    }

    /**
     * @param dateDidQuiz the dateDidQuiz to set
     */
    public void setDateDidQuiz(Date dateDidQuiz) {
        this.dateDidQuiz = dateDidQuiz;
    }

    /**
     * @return the score
     */
    public double getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * @return the timeDoQuiz
     */
    public int getTimeDoQuiz() {
        return timeDoQuiz;
    }

    /**
     * @param timeDoQuiz the timeDoQuiz to set
     */
    public void setTimeDoQuiz(int timeDoQuiz) {
        this.timeDoQuiz = timeDoQuiz;
    }

    /**
     * @return the numberQuestionOfQuiz
     */
    public int getNumberQuestionOfQuiz() {
        return numberQuestionOfQuiz;
    }

    /**
     * @param numberQuestionOfQuiz the numberQuestionOfQuiz to set
     */
    public void setNumberQuestionOfQuiz(int numberQuestionOfQuiz) {
        this.numberQuestionOfQuiz = numberQuestionOfQuiz;
    }

    /**
     * @return the subId
     */
    public String getSubId() {
        return subId;
    }

    /**
     * @param subId the subId to set
     */
    public void setSubId(String subId) {
        this.subId = subId;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the numberOfCorrectAnswer
     */
    public int getNumberOfCorrectAnswer() {
        return numberOfCorrectAnswer;
    }

    /**
     * @param numberOfCorrectAnswer the numberOfCorrectAnswer to set
     */
    public void setNumberOfCorrectAnswer(int numberOfCorrectAnswer) {
        this.numberOfCorrectAnswer = numberOfCorrectAnswer;
    }
}
