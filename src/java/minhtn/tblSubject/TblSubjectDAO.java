/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.tblSubject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import minhtn.utilities.DBUtils;

/**
 *
 * @author minhv
 */
public class TblSubjectDAO implements Serializable {
    
    private List<TblSubjectDTO> listSubject;

    public List<TblSubjectDTO> getListSubject() {
        return listSubject;
    }
    
    public void loadListSubject() throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "SELECT subId, subName, timeDoQuiz, numberQuestionOfQuiz \n" +
                        "FROM TblSubject";
                
                ps = cn.prepareStatement(sql);
		rs = ps.executeQuery();
                
                while (rs.next()) {                    
                    String subId = rs.getString("subId");
                    String subName = rs.getString("subName");
                    int timeDoQuiz = rs.getInt("timeDoQuiz");
                    int numberQuestionOfQuiz = rs.getInt("numberQuestionOfQuiz");
                    
                    if (listSubject == null)
                        listSubject = new ArrayList<>();
                    
                    TblSubjectDTO subject = new TblSubjectDTO(subId, subName, timeDoQuiz, numberQuestionOfQuiz);
                    listSubject.add(subject);
                }
            } //end if connection is existed
        } finally {
            if (rs != null)
                rs.close();
            
            if (ps != null)
                ps.close();
            
            if (cn != null)
                cn.close();
        }
    }
    
    public void updateTimeAndNumOfSubjectForTakeQuiz(String subId, 
            int timeDoQuiz, int numberQuestionOfQuiz) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "UPDATE TblSubject\n" +
                    "SET timeDoQuiz = ?, numberQuestionOfQuiz = ? \n" +
                    "WHERE subId = ?";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, timeDoQuiz);
                ps.setInt(2, numberQuestionOfQuiz);
                ps.setString(3, subId);
                
                ps.executeUpdate();
            } //end if connection is connected
        } finally {            
            if (ps != null)
                ps.close();
            
            if (cn != null)
                cn.close();
        }
    }
}
