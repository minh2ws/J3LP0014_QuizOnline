/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.tblQuestionBank;

import java.io.Serializable;

/**
 *
 * @author minhv
 */
public class QuestionBankError implements Serializable {
    private String updateQuestionError;

    /**
     * @return the updateQuestionError
     */
    public String getUpdateQuestionError() {
        return updateQuestionError;
    }

    /**
     * @param updateQuestionError the updateQuestionError to set
     */
    public void setUpdateQuestionError(String updateQuestionError) {
        this.updateQuestionError = updateQuestionError;
    }
}
