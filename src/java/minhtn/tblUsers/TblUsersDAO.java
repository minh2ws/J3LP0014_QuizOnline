/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.tblUsers;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import minhtn.utilities.DBUtils;
import minhtn.utilities.HashingUtils;

/**
 *
 * @author minhv
 */
public class TblUsersDAO implements Serializable {
     public TblUsersDTO checkLogin(String email, String password) 
	throws SQLException, NamingException, NoSuchAlgorithmException{
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            cn = DBUtils.makeConnection();
            
            if (cn != null) {
                String sql = "SELECT email, password, name, role, status " +
                        "FROM TblUsers " +
                        "WHERE email = ? AND password = ?";
                ps = cn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, HashingUtils.hashingPasswordSHA256(password));
		rs = ps.executeQuery();
		if (rs.next()) {
		    String emailString = rs.getString("email");
		    if (email.equals(emailString)) {
			String name = rs.getString("name");
                        String role = rs.getString("role");
			String status = rs.getString("status");
                        TblUsersDTO dto = new TblUsersDTO(email, password, name, role, status);
			return dto;
		    } //end if user and password matched
		    return null;
		}//end if record is existed
            } //end if connection is existed
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
     
    public boolean registerAccount(String email, String password, String name) 
             throws SQLException, NamingException, NoSuchAlgorithmException {
        Connection cn = null;
        PreparedStatement ps = null;
        
        try {
            cn = DBUtils.makeConnection();
           
            if (cn != null) {
                String sql = "INSERT INTO TblUsers "
                        + "(email, password, name, role, status) "
                        + "VALUES (?, ?, ?, ?, ?) ";
                ps = cn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, HashingUtils.hashingPasswordSHA256(password));
                ps.setString(3, name);
                ps.setString(4, "Student");
                ps.setString(5, "New");
                
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
}
