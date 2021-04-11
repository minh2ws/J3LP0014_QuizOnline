/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.servlet;

import java.io.IOException;
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
import minhtn.tblSubject.TblSubjectDAO;

/**
 *
 * @author minhv
 */
@WebServlet(name = "SetupQuizServlet", urlPatterns = {"/SetupQuizServlet"})
public class SetupQuizServlet extends HttpServlet {

    private final String LOAD_ADMIN_PAGE_SERVLET = "load-admin-page";

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

        String url = LOAD_ADMIN_PAGE_SERVLET;

        try {
            String cmbSubject = request.getParameter("cmbQuestionSubject");
            String cmbNumberOfQuestionInQuiz = request.getParameter("cmbNumberOfQuestionInQuiz");
            String cmbTimeTakeQuiz = request.getParameter("cmbTimeTakeQuiz");

            int numberOfQuestionInQuiz = Integer.parseInt(cmbNumberOfQuestionInQuiz);
            int timeTakeQuiz = Integer.parseInt(cmbTimeTakeQuiz);

            TblSubjectDAO dao = new TblSubjectDAO();
            dao.updateTimeAndNumOfSubjectForTakeQuiz(cmbSubject, timeTakeQuiz, numberOfQuestionInQuiz);
        }  catch (SQLException e) {
            log("SetupQuizServlet _ SQLException: " + e.getMessage());
        } catch (NamingException e) {
            log("SetupQuizServlet _ NamingException: " + e.getMessage());
        } finally {
            ServletContext context = request.getServletContext();
            Map<String, String> functionMap = (Map<String, String>) context.getAttribute("FUNCTION_MAP");
            RequestDispatcher rd = request.getRequestDispatcher(functionMap.get(url));
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
