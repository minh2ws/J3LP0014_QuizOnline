/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhtn.tblQuiz.TblQuizDAO;
import minhtn.tblQuiz.TblQuizDTO;
import minhtn.tblSubject.TblSubjectDAO;
import minhtn.tblSubject.TblSubjectDTO;
import minhtn.tblUsers.TblUsersDTO;

/**
 *
 * @author minhv
 */
@WebServlet(name = "LoadQuizHistoryServlet", urlPatterns = {"/LoadQuizHistoryServlet"})
public class LoadQuizHistoryServlet extends HttpServlet {

    private final String QUIZ_HISTORY_PAGE = "quiz-history";

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

        String url = QUIZ_HISTORY_PAGE;

        try {
            String email = "";
            
            HttpSession session = request.getSession(false);
            if (session != null) {
                TblUsersDTO user = (TblUsersDTO) session.getAttribute("ACCOUNT");
                TblQuizDAO quizDAO = new TblQuizDAO();
                if (user.getRole().equals("Student")) {
                    email = user.getEmail();
                }
                quizDAO.loadQuiz(email);
                List<TblQuizDTO> listQuiz = quizDAO.getListQuiz();
                request.setAttribute("QUIZ_LIST", listQuiz);
                
                //load subject list
                TblSubjectDAO dao = new TblSubjectDAO();
                dao.loadListSubject();
                List<TblSubjectDTO> listSubject = dao.getListSubject();
                request.setAttribute("SUBJECT_LIST", listSubject);
            }
        } catch (SQLException e) {
            log("LoadQuizHistoryServlet _ SQLException: " + e.getMessage());
        } catch (NamingException e) {
            log("LoadQuizHistoryServlet _ NamingException: " + e.getMessage());
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
