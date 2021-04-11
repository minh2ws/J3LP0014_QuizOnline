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
public class TblQuizAnswerDTO implements Serializable {
    private String questionId;
    private String quizId;
    private String answer;

    public TblQuizAnswerDTO() {
    }

    public TblQuizAnswerDTO(String questionId, String quizId, String answer) {
        this.questionId = questionId;
        this.quizId = quizId;
        this.answer = answer;
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
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
