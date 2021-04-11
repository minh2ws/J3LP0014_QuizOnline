/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.tblQuizAnswer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import minhtn.utilities.DBUtils;

/**
 *
 * @author minhv
 */
public class TblQuizAnswerDAO implements Serializable {
    
    public boolean checkQuestion(String questionId) 
            throws SQLException, NamingException{
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "SELECT questionId "
                        + "FROM TblQuizAnswer "
                        + "WHERE questionId = ?";
                ps = cn.prepareStatement(sql);
                ps.setString(1, questionId);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
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
        return false;
    }
    
    public boolean insertQuizAnswer(String quizId, List<QuizAnswerObject> listQuizAnswer)
        throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        
        try {
            cn = DBUtils.makeConnection();
           
            if (cn != null) {
                String sql = "INSERT INTO TblQuizAnswer "
                        + "(questionId, quizId, answer) "
                        + "VALUES (?, ?, ?) ";
                ps = cn.prepareStatement(sql);
                
                cn.setAutoCommit(false);
                
                int totalRow = 0;
                for (QuizAnswerObject quizAnswer : listQuizAnswer) {
                    ps.setString(1, quizAnswer.getQuestionId());
                    ps.setString(2, quizId);
                    ps.setString(3, quizAnswer.getAnswerChoose());
                    
                    ps.addBatch();
                    totalRow++;
                }
                
                int[] result = ps.executeBatch();
                
                if (totalRow == result.length)
                    cn.commit();
                    return true;
            } //end if connection is connected
        } finally {
            if (ps != null) 
                ps.close();
            
            if (cn != null) 
                cn.close();
            
        }
        return false;
    }
}
