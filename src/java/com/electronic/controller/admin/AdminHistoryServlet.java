package com.electronic.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.electronic.beans.History;
import com.electronic.dao.UserHistory;
/**
 *
 * @author Dhannu
 */
@WebServlet("/admin/AdminHistoryServlet")
public class AdminHistoryServlet extends HttpServlet {

    ArrayList<History> allHistory = null;
    UserHistory h;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         h=new UserHistory();
        try {
            
            allHistory = h.getAllHistory();
            request.setAttribute("allHistoryAdmin",allHistory );

        String nextJSP = "/admin/history.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
}
