/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.tblQuestionBank;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import minhtn.utilities.DBUtils;

/**
 *
 * @author minhv
 */
public class TblQuestionBankDAO implements Serializable {
    private List<TblQuestionBankDTO> listQuestionBank;

    public List<TblQuestionBankDTO> getListQuestionBank() {
        return listQuestionBank;
    }
    
    private List<TblQuestionBankDTO> listRandomQuestion;

    public List<TblQuestionBankDTO> getListRandomQuestion() {
        return listRandomQuestion;
    }
    
    private Map<String, String> mapQuestionVsAnswer;

    public Map<String, String> getMapQuestionVsAnswer() {
        return mapQuestionVsAnswer;
    }
    
    public boolean insertQuestion(String questionId, String questionContent, 
            String optionA, String optionB, String optionC, String optionD,
            String answerCorrect, String status, String subId)
            throws SQLException, NamingException{
        Connection cn = null;
        PreparedStatement ps = null;
        
        try {
            cn = DBUtils.makeConnection();
           
            if (cn != null) {
                String sql = "INSERT INTO TblQuestionBank (questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, status, subId) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                ps = cn.prepareStatement(sql);
                ps.setString(1, questionId);
                ps.setString(2, questionContent);
                ps.setString(3, optionA);
                ps.setString(4, optionB);
                ps.setString(5, optionC);
                ps.setString(6, optionD);
                ps.setString(7, answerCorrect);
                ps.setString(8, status);
                ps.setString(9, subId);
                
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
    
    public void loadQuestionBank() throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "SELECT questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, qb.subId\n" +
                    "FROM TblQuestionBank qb, TblSubject s \n" +
                    "WHERE qb.subId = s.subID " +
                    "GROUP BY questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, qb.subId \n" +
                    "ORDER BY qb.subId, questionContent DESC \n" +
                    "OFFSET 0 ROWS \n" +
                    "FETCH NEXT 20 ROWS ONLY";
                
                ps = cn.prepareStatement(sql);
                
                rs = ps.executeQuery();
                while (rs.next()) {                    
                    String questionId = rs.getString("questionId");
                    String questionContent = rs.getString("questionContent");
                    String optionA = rs.getString("optionA");
                    String optionB = rs.getString("optionB");
                    String optionC = rs.getString("optionC");
                    String optionD = rs.getString("optionD");
                    String answerCorrect = rs.getString("answerCorrect");
                    Date createDate = rs.getDate("createDate");
                    String status = rs.getString("status");
                    String subId = rs.getString("subId");
                    
                    if (listQuestionBank == null)
                        listQuestionBank = new ArrayList<>();
                    
                    TblQuestionBankDTO question = new TblQuestionBankDTO(questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, subId);
                    listQuestionBank.add(question);
                }
            }
        } finally {
            if (rs != null)
                rs.close();
            
            if (ps != null) 
                ps.close();
            
            if (cn != null) 
                cn.close();
        }
    }
    
    public void searchQuestionBank(String searchValue, String searchSubject, 
            String status, int page) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "SELECT questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, qb.subId\n" +
                    "FROM TblQuestionBank qb, TblSubject s \n" +
                    "WHERE qb.subId = s.subID ";
                if (!searchValue.trim().isEmpty())
                    sql += "AND questionContent LIKE N'%" + searchValue + "%' ";
                
                if (!searchSubject.trim().isEmpty())
                    sql += "AND qb.subId = '"+ searchSubject + "' ";
                
                if (!status.trim().isEmpty())
                    sql += "AND status = '"+ status +"' ";
                
                sql += "GROUP BY questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, qb.subId \n" +
                    "ORDER BY qb.subId, questionContent DESC \n" +
                    "OFFSET ? ROWS \n" +
                    "FETCH NEXT 20 ROWS ONLY";
                
                ps = cn.prepareStatement(sql);
                //set page
                ps.setInt(1, (page - 1) * 20);
                
                rs = ps.executeQuery();
                while (rs.next()) {                    
                    String questionId = rs.getString("questionId");
                    String questionContent = rs.getString("questionContent");
                    String optionA = rs.getString("optionA");
                    String optionB = rs.getString("optionB");
                    String optionC = rs.getString("optionC");
                    String optionD = rs.getString("optionD");
                    String answerCorrect = rs.getString("answerCorrect");
                    Date createDate = rs.getDate("createDate");
                    String statusQuery = rs.getString("status");
                    String subId = rs.getString("subId");
                    
                    if (listQuestionBank == null)
                        listQuestionBank = new ArrayList<>();
                    
                    TblQuestionBankDTO question = new TblQuestionBankDTO(questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, statusQuery, subId);
                    listQuestionBank.add(question);
                }
            }
        } finally {
            if (rs != null)
                rs.close();
            
            if (ps != null) 
                ps.close();
            
            if (cn != null) 
                cn.close();
        }
    }
    
    public int getTotalQuestion() throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "SELECT count(questionId) AS totalQuestion \n" +
                    "FROM TblQuestionBank \n";
                ps = cn.prepareStatement(sql);
                
                rs = ps.executeQuery();
                if (rs.next())           
                   return rs.getInt("totalQuestion");
            } //end if connection is connected
        } finally {
            if (rs != null)
                rs.close();
            
            if (ps != null)
                ps.close();
            
            if (cn != null)
                cn.close();
        }
        return 0;
    }
    
    public int getTotalQuestionSearched(String searchValue, String searchSubject, 
            String status) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "SELECT count(questionId) AS totalQuestion \n" +
                    "FROM TblQuestionBank qb, TblSubject s \n" +
                    "WHERE qb.subId = s.subID ";
                if (!searchValue.trim().isEmpty())
                    sql += "AND questionContent LIKE N'%" + searchValue + "%' ";
                
                if (!searchSubject.trim().isEmpty())
                    sql += "AND qb.subId = '"+ searchSubject + "' ";
                
                if (!status.trim().isEmpty())
                    sql += "AND status = '"+ status +"' ";
                
                ps = cn.prepareStatement(sql);
                
                rs = ps.executeQuery();
                if (rs.next())           
                   return rs.getInt("totalQuestion");
            } //end if connection is connected
        } finally {
            if (rs != null)
                rs.close();
            
            if (ps != null)
                ps.close();
            
            if (cn != null)
                cn.close();
        }
        return 0;
    }
    
    public void updateStatusQuestion(String questionId, String status) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "UPDATE TblQuestionBank "
                        + "SET status = ? "
                        + "WHERE questionId = ? ";
                ps = cn.prepareStatement(sql);
                ps.setString(1, status);
                ps.setString(2, questionId);
                
                ps.executeUpdate();
            } //end if connection is connected
        } finally {            
            if (ps != null)
                ps.close();
            
            if (cn != null)
                cn.close();
        }
    }
    
    public TblQuestionBankDTO getQuestion(String questionId) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "SELECT questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, subId \n" +
                    "FROM TblQuestionBank \n" +
                    "WHERE questionId = ? ";
                
                ps = cn.prepareStatement(sql);
                ps.setString(1, questionId);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    String questionContent = rs.getString("questionContent");
                    String optionA = rs.getString("optionA");
                    String optionB = rs.getString("optionB");
                    String optionC = rs.getString("optionC");
                    String optionD = rs.getString("optionD");
                    String answerCorrect = rs.getString("answerCorrect");
                    Date createDate = rs.getDate("createDate");
                    String statusQuery = rs.getString("status");
                    String subId = rs.getString("subId");
                    
                    return new TblQuestionBankDTO(questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, statusQuery, subId);
                } //end if question is existed        
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
    
    public void updateQuestion(String questionId, String txtQuestionContent, 
            String txtOptionA, String txtOptionB, String txtOptionC, 
            String txtOptionD, String cmbCorrectAnswer, String cmbSubject, 
            String cmbStatus) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "UPDATE TblQuestionBank \n"
                        + "SET questionContent = ?, optionA = ?, optionB = ?, "
                        + "optionC = ?, optionD = ?, answerCorrect = ?, "
                        + " status = ?, subId = ? \n"
                        + "WHERE questionId = ?";
                ps = cn.prepareStatement(sql);
                ps.setString(1, txtQuestionContent);
                ps.setString(2, txtOptionA);
                ps.setString(3, txtOptionB);
                ps.setString(4, txtOptionC);
                ps.setString(5, txtOptionD);
                ps.setString(6, cmbCorrectAnswer);
                ps.setString(7, cmbStatus);
                ps.setString(8, cmbSubject);
                ps.setString(9, questionId);
                ps.executeUpdate();
            } //end if connection is connected
        } finally {            
            if (ps != null)
                ps.close();
            
            if (cn != null)
                cn.close();
        }
    }
    
    public int getMinimunQuestionOfAllSubject() throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "SELECT COUNT(questionId) AS TotalQuestion\n" +
                    "FROM TblQuestionBank\n" +
                    "WHERE status = 'Active' \n" +
                    "GROUP BY subId \n" +
                    "HAVING COUNT(questionId) <= ALL (SELECT COUNT(questionId) \n" +
                    "					FROM TblQuestionBank \n" +
                    "					WHERE status = 'Active' \n" +
                    "					GROUP BY subId)";
                ps = cn.prepareStatement(sql);
                
                rs = ps.executeQuery();
                if (rs.next())           
                   return rs.getInt("TotalQuestion");
            } //end if connection is connected
        } finally {
            if (rs != null)
                rs.close();
            
            if (ps != null)
                ps.close();
            
            if (cn != null)
                cn.close();
        }
        return 0;
    }
    
    public void loadRandomQuestion(String subId, int numberQuestionOfQuiz) 
            throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "SELECT TOP(?) questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, s.subName \n" +
                    "FROM TblQuestionBank q, TblSubject s \n" +
                    "WHERE q.subId = s.subId AND status = 'active' AND s.subId = ? \n" +
                    "ORDER BY NEWID() ";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, numberQuestionOfQuiz);
                ps.setString(2, subId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionId = rs.getString("questionId");
                    String questionContent = rs.getString("questionContent");
                    String optionA = rs.getString("optionA");
                    String optionB = rs.getString("optionB");
                    String optionC = rs.getString("optionC");
                    String optionD = rs.getString("optionD");
                    String answerCorrect = rs.getString("answerCorrect");
                    Date createDate = rs.getDate("createDate");
                    String status = rs.getString("status");
                    
                    if (listRandomQuestion == null)
                        listRandomQuestion = new ArrayList<>();
                    
                    TblQuestionBankDTO dto = new TblQuestionBankDTO(questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, subId);
                    listRandomQuestion.add(dto);
                    
                    if (mapQuestionVsAnswer == null)
                        mapQuestionVsAnswer = new HashMap<>();
                    
                    mapQuestionVsAnswer.put(questionId, answerCorrect);
                }
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
    
    public void loadAnswerQuestion(String quizId) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "SELECT  qa.questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, subId, quizId, answer \n" +
                    "FROM TblQuestionBank q, TblQuizAnswer qa\n" +
                    "WHERE  q.questionId = qa.questionId AND qa.questionId IN (SELECT questionId \n" +
                    "								FROM TblQuizAnswer \n" +
                    "								WHERE quizId = ?)";
                ps = cn.prepareStatement(sql);
                ps.setString(1, quizId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionId = rs.getString("questionId");
                    String questionContent = rs.getString("questionContent");
                    String optionA = rs.getString("optionA");
                    String optionB = rs.getString("optionB");
                    String optionC = rs.getString("optionC");
                    String optionD = rs.getString("optionD");
                    String answerCorrect = rs.getString("answerCorrect");
                    Date createDate = rs.getDate("createDate");
                    String status = rs.getString("status");
                    String subId = rs.getString("subId");
                    
                    //for student answer
                    String answer = rs.getString("answer");
                    
                    if (listQuestionBank == null)
                        listQuestionBank = new ArrayList<>();
                    
                    TblQuestionBankDTO dto = new TblQuestionBankDTO(questionId, questionContent, optionA, optionB, optionC, optionD, answerCorrect, createDate, status, subId);
                    listQuestionBank.add(dto);
                    
                    if (mapQuestionVsAnswer == null)
                        mapQuestionVsAnswer = new HashMap<>();
                    
                    mapQuestionVsAnswer.put(questionId, answer);
                }
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
