/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.tblQuiz;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import minhtn.tblQuestionBank.TblQuestionBankDTO;

/**
 *
 * @author minhv
 */
public class QuizObject implements Serializable {
    private Date timeStartQuiz;
    private Date timeEndQuiz;
    private String subId;
    private List<TblQuestionBankDTO> listQuestion;
    private int timeDoQuiz;

    public QuizObject() {
    }

    public QuizObject(Date timeStartQuiz, Date timeEndQuiz, String subId, List<TblQuestionBankDTO> listQuestion, int timeDoQuiz) {
        this.timeStartQuiz = timeStartQuiz;
        this.timeEndQuiz = timeEndQuiz;
        this.subId = subId;
        this.listQuestion = listQuestion;
        this.timeDoQuiz = timeDoQuiz;
    }

    /**
     * @return the timeStartQuiz
     */
    public Date getTimeStartQuiz() {
        return timeStartQuiz;
    }

    /**
     * @param timeStartQuiz the timeStartQuiz to set
     */
    public void setTimeStartQuiz(Date timeStartQuiz) {
        this.timeStartQuiz = timeStartQuiz;
    }

    /**
     * @return the timeEndQuiz
     */
    public Date getTimeEndQuiz() {
        return timeEndQuiz;
    }

    /**
     * @param timeEndQuiz the timeEndQuiz to set
     */
    public void setTimeEndQuiz(Date timeEndQuiz) {
        this.timeEndQuiz = timeEndQuiz;
    }

    /**
     * @return the listQuestion
     */
    public List<TblQuestionBankDTO> getListQuestion() {
        return listQuestion;
    }

    /**
     * @param listQuestion the listQuestion to set
     */
    public void setListQuestion(List<TblQuestionBankDTO> listQuestion) {
        this.listQuestion = listQuestion;
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
}
