/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.tblQuestionBank;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author minhv
 */
public class TblQuestionBankDTO implements Serializable {
    private String questionId;
    private String questionContent;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answerCorrect;
    private Date createDate;
    private String status;
    private String subId;

    public TblQuestionBankDTO() {
    }

    public TblQuestionBankDTO(String questionId, String questionContent, String optionA, String optionB, String optionC, String optionD, String answerCorrect, Date createDate, String status, String subId) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answerCorrect = answerCorrect;
        this.createDate = createDate;
        this.status = status;
        this.subId = subId;
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
     * @return the questionContent
     */
    public String getQuestionContent() {
        return questionContent;
    }

    /**
     * @param questionContent the questionContent to set
     */
    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    /**
     * @return the optionA
     */
    public String getOptionA() {
        return optionA;
    }

    /**
     * @param optionA the optionA to set
     */
    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    /**
     * @return the optionB
     */
    public String getOptionB() {
        return optionB;
    }

    /**
     * @param optionB the optionB to set
     */
    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    /**
     * @return the optionC
     */
    public String getOptionC() {
        return optionC;
    }

    /**
     * @param optionC the optionC to set
     */
    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    /**
     * @return the optionD
     */
    public String getOptionD() {
        return optionD;
    }

    /**
     * @param optionD the optionD to set
     */
    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    /**
     * @return the answerCorrect
     */
    public String getAnswerCorrect() {
        return answerCorrect;
    }

    /**
     * @param answerCorrect the answerCorrect to set
     */
    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
    
    
}
