/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.tblQuiz;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import minhtn.tblUsers.TblUsersDTO;
import minhtn.utilities.DBUtils;

/**
 *
 * @author minhv
 */
public class TblQuizDAO implements Serializable {
    
    private List<TblQuizDTO> listQuiz;

    public List<TblQuizDTO> getListQuiz() {
        return listQuiz;
    }
    
    public boolean insertQuiz(String quizId, double score, int numberOfCorrectAnswer,
            int timeDoQuiz, int numberQuestionOfQuiz, String subId, String email) 
             throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        
        try {
            cn = DBUtils.makeConnection();
           
            if (cn != null) {
                String sql = "INSERT INTO TblQuiz "
                        + "(quizId, score, numberOfCorrectAnswer, timeDoQuiz, numberQuestionOfQuiz, subId, email) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?) ";
                ps = cn.prepareStatement(sql);
                ps.setString(1, quizId);
                ps.setDouble(2, score);
                ps.setInt(3, numberOfCorrectAnswer);
                ps.setInt(4, timeDoQuiz);
                ps.setInt(5, numberQuestionOfQuiz);
                ps.setString(6, subId);
                ps.setString(7, email);
                
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            } //end if connection is connected
        } finally {
            if (ps != null) 
                ps.close();
            
            if (cn != null) 
                cn.close();
            
        }
        return false;
    }
    
    public TblQuizDTO getQuizDetail(String quizId) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "SELECT quizId, dateDidQuiz, score, numberOfCorrectAnswer, timeDoQuiz, numberQuestionOfQuiz, subId, email \n" +
                    "FROM TblQuiz \n" +
                    "WHERE quizId = ?";
                ps = cn.prepareStatement(sql);
                ps.setString(1, quizId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Date dateDidQuiz = rs.getDate("dateDidQuiz");
                    double score = rs.getDouble("score");
                    int numberOfCorrectAnswer = rs.getInt("numberOfCorrectAnswer");
                    int timeDoQuiz = rs.getInt("timeDoQuiz");
                    int numberQuestionOfQuiz = rs.getInt("numberQuestionOfQuiz");
                    String subId = rs.getString("subId");
                    String email = rs.getString("email");
                    
                    TblQuizDTO dto = new TblQuizDTO(quizId, dateDidQuiz, score, numberOfCorrectAnswer, timeDoQuiz, numberQuestionOfQuiz, subId, email);
                    return dto;
                } //end if quiz is existed
            } //end if connection is connected
        } finally {
            if (rs != null)
                rs.close();
            
            if (ps != null)
                ps.close();
            
            if (cn != null)
                cn.close();
        }
        return null;
    }
    
    public void searchQuiz (String email, String subId, String timeDoQuiz) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "SELECT quizId, dateDidQuiz, score, numberOfCorrectAnswer, timeDoQuiz, numberQuestionOfQuiz, subId, u.email \n" +
                    "FROM TblQuiz q, TblUsers u \n" +
                    "WHERE q.email = u.email ";
                
                if (!email.trim().isEmpty())
                    sql += " AND u.email = '" + email + "' ";
                
                if (!subId.trim().isEmpty())
                    sql += " AND subId = '" + subId + "' ";
                
                if (!timeDoQuiz.trim().isEmpty())
                    sql += " AND timeDoQuiz = '" + timeDoQuiz + "' ";
                
                ps = cn.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    String quizId = rs.getString("quizId");
                    Date dateDidQuiz = rs.getDate("dateDidQuiz");
                    double score = rs.getDouble("score");
                    int numberOfCorrectAnswer = rs.getInt("numberOfCorrectAnswer");
                    int timeDoQuizQuery = rs.getInt("timeDoQuiz");
                    int numberQuestionOfQuiz = rs.getInt("numberQuestionOfQuiz");
                    String subIdQuery = rs.getString("subId");
                    String emailQuery = rs.getString("email");
                    
                    if (listQuiz == null)
                        listQuiz = new ArrayList<>();
                    
                    TblQuizDTO dto = new TblQuizDTO(quizId, dateDidQuiz, score, numberOfCorrectAnswer, timeDoQuizQuery, numberQuestionOfQuiz, subIdQuery, emailQuery);
                    listQuiz.add(dto);
                } //end if quiz is existed
            } //end if connection is connected
        } finally {
            if (rs != null)
                rs.close();
            
            if (ps != null)
                ps.close();
            
            if (cn != null)
                cn.close();
        }
    }
    
    public void loadQuiz (String email) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "SELECT quizId, dateDidQuiz, score, numberOfCorrectAnswer, timeDoQuiz, numberQuestionOfQuiz, subId, u.email \n" +
                    "FROM TblQuiz q, TblUsers u \n" +
                    "WHERE q.email = u.email ";
                
                if (!email.trim().isEmpty())
                    sql += " AND u.email = '" + email + "' ";
                
                ps = cn.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    String quizId = rs.getString("quizId");
                    Date dateDidQuiz = rs.getDate("dateDidQuiz");
                    double score = rs.getDouble("score");
                    int numberOfCorrectAnswer = rs.getInt("numberOfCorrectAnswer");
                    int timeDoQuizQuery = rs.getInt("timeDoQuiz");
                    int numberQuestionOfQuiz = rs.getInt("numberQuestionOfQuiz");
                    String subIdQuery = rs.getString("subId");
                    String emailQuery = rs.getString("email");
                    
                    if (listQuiz == null)
                        listQuiz = new ArrayList<>();
                    
                    TblQuizDTO dto = new TblQuizDTO(quizId, dateDidQuiz, score, numberOfCorrectAnswer, timeDoQuizQuery, numberQuestionOfQuiz, subIdQuery, emailQuery);
                    listQuiz.add(dto);
                } //end if quiz is existed
            } //end if connection is connected
        } finally {
            if (rs != null)
                rs.close();
            
            if (ps != null)
                ps.close();
            
            if (cn != null)
                cn.close();
        }
    }
}
