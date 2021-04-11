/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import minhtn.tblQuiz.QuizObject;
import minhtn.tblQuiz.TblQuizDAO;
import minhtn.tblQuizAnswer.QuizAnswerObject;
import minhtn.tblQuizAnswer.TblQuizAnswerDAO;
import minhtn.tblUsers.TblUsersDTO;
import minhtn.utilities.GenerateCode;

/**
 *
 * @author minhv
 */
@WebServlet(name = "SubmitQuizServlet", urlPatterns = {"/SubmitQuizServlet"})
public class SubmitQuizServlet extends HttpServlet {

    private final String SUBMIT_ERROR_PAGE = "submit-error-page";
    private final String LOAD_QUIZ_RESULT_PAGE_SERVLET = "load-quiz-result-page-servlet";

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

        String url = SUBMIT_ERROR_PAGE;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                if (session.getAttribute("QUIZ") != null) {
                    //create list for store answer of student
                    List<QuizAnswerObject> quizAnswerList = new ArrayList<>();
                    //get number of question in quiz
                    int numberOfQuestion = (int) session.getAttribute("NUMBER_OF_QUESTION");
                    for (int i = 1; i <= numberOfQuestion; i++) {
                        String questionId = request.getParameter("questionId" + i);
                        String answerChoose = request.getParameter("txtAnswer" + i);
                        if (answerChoose == null) {
                            answerChoose = "";
                        }
                        QuizAnswerObject obj = new QuizAnswerObject(questionId, answerChoose);
                        quizAnswerList.add(obj);
                    }

                    //check answer of student and correct answer
                    int totalCorrectAnswer = 0;
                    double totalScore = 10;
                    double scoreOfEachQuestion = 10 / numberOfQuestion;
                    //get map correct answer
                    Map<String, String> mapCorrectAnswer = (Map<String, String>) session.getAttribute("MAP_CORRECT_ANSWER_QUESTION");
                    //calculate score
                    for (QuizAnswerObject quizAnswer : quizAnswerList) {
                        if (quizAnswer.getAnswerChoose().equals(mapCorrectAnswer.get(quizAnswer.getQuestionId()))) {
                            totalCorrectAnswer++;
                        } else {
                            totalScore -= scoreOfEachQuestion;
                        }

                    }

                    TblUsersDTO user = (TblUsersDTO) session.getAttribute("ACCOUNT");
                    QuizObject obj = (QuizObject) session.getAttribute("QUIZ");

                    String quizId = GenerateCode.generateQuizID(obj.getSubId());
                    TblQuizDAO dao = new TblQuizDAO();
                    boolean isSuccess = dao.insertQuiz(quizId, totalScore, totalCorrectAnswer, obj.getTimeDoQuiz(), numberOfQuestion, obj.getSubId(), user.getEmail());
                    if (isSuccess) {
                        TblQuizAnswerDAO quizAnswerDAO = new TblQuizAnswerDAO();
                        isSuccess = quizAnswerDAO.insertQuizAnswer(quizId, quizAnswerList);
                        if (isSuccess) {
                            url = LOAD_QUIZ_RESULT_PAGE_SERVLET;
                            request.setAttribute("QUIZ_ID", quizId);
                            session.removeAttribute("QUIZ");
                            session.removeAttribute("MAP_CORRECT_ANSWER_QUESTION");
                            session.removeAttribute("NUMBER_OF_QUESTION");
                            session.removeAttribute("SUBJECT_NAME");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            log("SubmitQuizServlet _ SQLException: " + e.getMessage());
        } catch (NamingException e) {
            log("SubmitQuizServlet _ NamingException: " + e.getMessage());
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
