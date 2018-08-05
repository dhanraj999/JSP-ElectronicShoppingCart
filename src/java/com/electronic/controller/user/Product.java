
package com.electronic.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.electronic.dao.ProductModel;

/**
 *
 * @author Dhannu
 */
@WebServlet(name = "Product", urlPatterns = {"/Product"})
public class Product extends HttpServlet {

   

    
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     int productID=Integer.parseInt(request.getParameter("id"));
     ProductModel productModel=new ProductModel();
     
        com.electronic.beans.Product product=productModel.getProduct(productID);
        
        //no product with this id
        if(product==null){
            response.sendRedirect("4040.jsp");
            
        }else{
            //assign it on request
            request.setAttribute("product", product);
            
            //get recommended product
           ArrayList<com.electronic.beans.Product>recommendedItem=productModel.getRecommeendedItem(product.getCategory(), productID);
           request.setAttribute("recomed", recommendedItem);
           
           request.getRequestDispatcher("/product-details.jsp").forward(request, response);
        }
     
        
       
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
