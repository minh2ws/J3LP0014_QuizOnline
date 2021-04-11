/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.tblSubject;

import java.io.Serializable;

/**
 *
 * @author minhv
 */
public class TblSubjectDTO implements Serializable {
    private String subId;
    private String subName;
    private int timeDoQuiz;
    private int numberQuestionOfQuiz;

    public TblSubjectDTO(String subId, String subName, int timeDoQuiz, int numberQuestionOfQuiz) {
        this.subId = subId;
        this.subName = subName;
        this.timeDoQuiz = timeDoQuiz;
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
     * @return the subName
     */
    public String getSubName() {
        return subName;
    }

    /**
     * @param subName the subName to set
     */
    public void setSubName(String subName) {
        this.subName = subName;
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
}
