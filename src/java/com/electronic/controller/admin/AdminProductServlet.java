package com.electronic.controller.admin;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import com.electronic.beans.Product;
import com.electronic.dao.ProductModel;
/**
 *
 * @author Dhannu
 */
@WebServlet("/admin/AdminProductServlet")
public class AdminProductServlet extends HttpServlet {

    List<Product> allProducts = new ArrayList<Product>();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductModel productModel = new ProductModel();
        allProducts = productModel.getAllProduct();
        request.setAttribute("allProductsAdmin", allProducts);

        String nextJSP = "/admin/products.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);

    }

}
