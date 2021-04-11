/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.tblQuizAnswer;

import java.io.Serializable;

/**
 *
 * @author minhv
 */
public class QuizAnswerObject implements Serializable {
    private String questionId;
    private String answerChoose;

    public QuizAnswerObject() {
    }

    public QuizAnswerObject(String questionId, String answerChoose) {
        this.questionId = questionId;
        this.answerChoose = answerChoose;
    }

    /**
     * @return the questionId
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId the questionId to set
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    /**
     * @return the answerChoose
     */
    public String getAnswerChoose() {
        return answerChoose;
    }

    /**
     * @param answerChoose the answerChoose to set
     */
    public void setAnswerChoose(String answerChoose) {
        this.answerChoose = answerChoose;
    }
}
