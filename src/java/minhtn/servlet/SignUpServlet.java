/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import minhtn.tblUsers.TblUsersCreateError;
import minhtn.tblUsers.TblUsersDAO;

/**
 *
 * @author minhv
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {
    
    private final String LOGIN_PAGE = "login-page";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String url = LOGIN_PAGE;
        
        TblUsersCreateError error = new TblUsersCreateError();
        
        try {
            String txtEmail = request.getParameter("txtEmail");
            String txtName = request.getParameter("txtName");
            String txtPassword = request.getParameter("txtPassword");
            
            TblUsersDAO dao = new TblUsersDAO();
            dao.registerAccount(txtEmail, txtPassword, txtName);
            
        } catch (SQLException e) {
            log("CreateAccountServlet _ SQLException: " + e.getMessage());
            
            //check if email is existed or not
            if (e.getMessage().contains("duplicate")) {
                error.setEmailExisted("Email is already existed");
                request.setAttribute("CREATE_ACCOUNT_ERRORS", error);
            }
        } catch (NamingException e) {
            log("CreateAccountServlet _ NamingException: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            log("CreateAccountServlet _ NoSuchAlgorithmException: " + e.getMessage());
        } finally {
            ServletContext context = (ServletContext) request.getServletContext();
            Map<String, String> functionsMap = (Map<String, String>) context.getAttribute("FUNCTION_MAP");
            RequestDispatcher rd = request.getRequestDispatcher(functionsMap.get(url));
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
