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
import minhtn.tblSubject.TblSubjectDAO;
import minhtn.tblSubject.TblSubjectDTO;
import minhtn.utilities.PageUtils;

/**
 *
 * @author minhv
 */
@WebServlet(name = "LoadAdminPageServlet", urlPatterns = {"/LoadAdminPageServlet"})
public class LoadAdminPageServlet extends HttpServlet {
    
    private final String ADMIN_PAGE = "admin-page";

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
        
        String url = ADMIN_PAGE;
        
        try {          
            //load question list
            loadAllQuestion(request);
            request.setAttribute("DEFAULT_PAGES", 1);
            
            //load subject list
            TblSubjectDAO dao = new TblSubjectDAO();
            dao.loadListSubject();
            List<TblSubjectDTO> listSubject = dao.getListSubject();
            request.setAttribute("SUBJECT_LIST", listSubject);
        } catch (SQLException e) {
            log("LoadAdminPageServlet _ SQLException: " + e.getMessage());
        } catch (NamingException e) {
            log("LoadAdminPageServlet _ NamingException: " + e.getMessage());
        }  finally {
            ServletContext context = request.getServletContext();
            Map<String, String> functionMap = (Map<String, String>) context.getAttribute("FUNCTION_MAP");
            RequestDispatcher rd = request.getRequestDispatcher(functionMap.get(url));
            rd.forward(request, response);
        }
    }
    
    private void loadAllQuestion(HttpServletRequest req) 
            throws SQLException, NamingException {
        TblQuestionBankDAO dao = new TblQuestionBankDAO();
        dao.loadQuestionBank();
        List<TblQuestionBankDTO> listQuestion = dao.getListQuestionBank();
        req.setAttribute("QUESTION_LIST", listQuestion);
        
        int totalQuestion = dao.getTotalQuestion();
        int totalPage = PageUtils.caculatePageRequired(totalQuestion, 20);
        req.setAttribute("TOTAL_PAGES", totalPage);
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
