/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhtn.tblQuestionBank.TblQuestionBankDAO;
import minhtn.tblQuestionBank.TblQuestionBankDTO;
import minhtn.tblQuiz.QuizObject;

/**
 *
 * @author minhv
 */
@WebServlet(name = "AttemptQuizServlet", urlPatterns = {"/AttemptQuizServlet"})
public class AttemptQuizServlet extends HttpServlet {

    private final String QUIZ_PAGE = "quiz-page";

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

        String url = QUIZ_PAGE;

        try {
            //create quiz session
            HttpSession session = request.getSession(false);
            if (session.getAttribute("QUIZ") == null) {
                String subId = request.getParameter("subId");
                String subName = request.getParameter("subName");
                String timeDoQuiz = request.getParameter("timeDoQuiz");
                String numberQuestionOfQuiz = request.getParameter("numberQuestionOfQuiz");

                int numberQuestionOfQuizNum = Integer.parseInt(numberQuestionOfQuiz);
                int timeDoQuizNum = Integer.parseInt(timeDoQuiz);

                //load question
                TblQuestionBankDAO dao = new TblQuestionBankDAO();
                dao.loadRandomQuestion(subId, numberQuestionOfQuizNum);
                //get list of question for quiz
                List<TblQuestionBankDTO> listQuestion = dao.getListRandomQuestion();
                //get list of correct answer of question using map for faster compare
                Map<String, String> mapCorrectAnswer = dao.getMapQuestionVsAnswer();

                //create quiz object
                QuizObject quiz = new QuizObject();
                Calendar time = Calendar.getInstance();
                quiz.setTimeStartQuiz(time.getTime());
                time.add(Calendar.MINUTE, timeDoQuizNum);
                quiz.setTimeEndQuiz(time.getTime());
                quiz.setListQuestion(listQuestion);
                quiz.setTimeDoQuiz(timeDoQuizNum);
                quiz.setSubId(subId);

                if (session != null) {
                    session.setAttribute("QUIZ", quiz);
                    session.setAttribute("MAP_CORRECT_ANSWER_QUESTION", mapCorrectAnswer);
                    session.setAttribute("NUMBER_OF_QUESTION", numberQuestionOfQuizNum);
                    session.setAttribute("SUBJECT_NAME", subName);
                }
            }
        } catch (SQLException e) {
            log("UpdateQuestionServlet _ SQLException: " + e.getMessage());
        } catch (NamingException e) {
            log("UpdateQuestionServlet _ NamingException: " + e.getMessage());
        } finally {
            response.sendRedirect(url);
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
