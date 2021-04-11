/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.tblUsers;

import java.io.Serializable;

/**
 *
 * @author minhv
 */
public class TblUsersCreateError implements Serializable {
    private String emailExisted;

    /**
     * @return the emailExisted
     */
    public String getEmailExisted() {
        return emailExisted;
    }

    /**
     * @param emailExisted the emailExisted to set
     */
    public void setEmailExisted(String emailExisted) {
        this.emailExisted = emailExisted;
    }    
}
