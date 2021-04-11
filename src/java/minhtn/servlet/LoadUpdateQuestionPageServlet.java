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
import minhtn.tblQuestionBank.QuestionBankError;
import minhtn.tblQuestionBank.TblQuestionBankDAO;
import minhtn.tblQuestionBank.TblQuestionBankDTO;
import minhtn.tblQuizAnswer.TblQuizAnswerDAO;
import minhtn.tblSubject.TblSubjectDAO;
import minhtn.tblSubject.TblSubjectDTO;

/**
 *
 * @author minhv
 */
@WebServlet(name = "LoadUpdateQuestionPageServlet", urlPatterns = {"/LoadUpdateQuestionPageServlet"})
public class LoadUpdateQuestionPageServlet extends HttpServlet {

    private final String UPDATE_QUESTION_PAGE = "update-question-page";
    private final String SEARCH_QUESTION_SERVLET = "search-question";

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

        String url = SEARCH_QUESTION_SERVLET;
        QuestionBankError error = new QuestionBankError();

        try {
            String questionId = request.getParameter("questionId");

            TblQuizAnswerDAO quizDAO = new TblQuizAnswerDAO();
            boolean isExisted = quizDAO.checkQuestion(questionId);

            if (!isExisted) {
                //load question
                TblQuestionBankDAO questionDAO = new TblQuestionBankDAO();
                TblQuestionBankDTO dto = questionDAO.getQuestion(questionId);
                request.setAttribute("QUESTION", dto);

                //load subject list
                TblSubjectDAO dao = new TblSubjectDAO();
                dao.loadListSubject();
                List<TblSubjectDTO> listSubject = dao.getListSubject();
                request.setAttribute("SUBJECT_LIST", listSubject);
                
                url = UPDATE_QUESTION_PAGE;
            } else {
                error.setUpdateQuestionError("Question " + questionId + " is already existed in quiz. You can't update!");
                request.setAttribute("UPDATE_QUESTION_ERRORS", error);
            }
        } catch (SQLException e) {
            log("LoadUpdateQuestionPageServlet _ SQLException: " + e.getMessage());
        } catch (NamingException e) {
            log("LoadUpdateQuestionPageServlet _ NamingException: " + e.getMessage());
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
