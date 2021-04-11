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
import minhtn.tblQuestionBank.TblQuestionBankDAO;
import minhtn.tblQuestionBank.TblQuestionBankDTO;
import minhtn.tblQuiz.TblQuizDAO;
import minhtn.tblQuiz.TblQuizDTO;

/**
 *
 * @author minhv
 */
@WebServlet(name = "LoadQuizResultPageSerlvet", urlPatterns = {"/LoadQuizResultPageSerlvet"})
public class LoadQuizResultPageSerlvet extends HttpServlet {
    
    private final String RESULT_PAGE = "quiz-result-page";

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
        
        String url = RESULT_PAGE;
        
        try {
            String quizId = request.getParameter("quizId");
            if (quizId == null)
                quizId = (String) request.getAttribute("QUIZ_ID");
            
            TblQuestionBankDAO dao = new TblQuestionBankDAO();
            dao.loadAnswerQuestion(quizId);
            List<TblQuestionBankDTO> listQuestionBank = dao.getListQuestionBank();
            Map<String, String> mapQuestionVsAnswer = dao.getMapQuestionVsAnswer();
            request.setAttribute("QUESTION_LIST", listQuestionBank);
            request.setAttribute("MAP_QUESTION_VS_ANSWER", mapQuestionVsAnswer);
            
            TblQuizDAO quizDAO = new TblQuizDAO();
            TblQuizDTO dto = quizDAO.getQuizDetail(quizId);
            request.setAttribute("RESULT", dto);
        } catch (SQLException e) {
            log("LoadQuizResultPageSerlvet _ SQLException: " + e.getMessage());
        } catch (NamingException e) {
            log("LoadQuizResultPageSerlvet _ NamingException: " + e.getMessage());
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
