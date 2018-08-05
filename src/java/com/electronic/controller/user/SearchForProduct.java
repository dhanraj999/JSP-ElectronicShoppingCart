package com.electronic.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.electronic.dao.ProductModel;
/**
 *
 * @author Dhannu
 */
public class SearchForProduct extends HttpServlet {

    ArrayList<com.electronic.beans.Product> product = new ArrayList<>();
    ProductModel model = new ProductModel();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("search") != null) {
            String productName = request.getParameter("search");

            System.out.println(productName);

            product = model.getProductByName(productName);

            System.out.println(product.size());

        } else {
            System.out.println("else");
            int min = Integer.parseInt(request.getParameter("down"));
            System.out.println(min);
            int max = Integer.parseInt(request.getParameter("up"));
            System.out.println(max);

            product = model.getAllProductByPrice(min, max);
            System.out.println("sizeof product" + product.size());

        }

        request.setAttribute("allProducts", product);
        String nextJSP = "/shop.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
