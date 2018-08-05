
package com.electronic.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.electronic.beans.Cart;
import com.electronic.beans.User;
import com.electronic.dao.CartModel;
/**
 *
 * @author Dhannu
 */

@WebServlet("/addCart")
public class AddCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        CartModel cartModel = new CartModel();
       
        int pID = Integer.parseInt(request.getParameter("productID"));
        int qaunty = Integer.parseInt(request.getParameter("qaunty"));
        //get login user id
        User user = (User) request.getSession().getAttribute("LoginUser");
        
        Cart car = new Cart();
        car.setProductId(pID);
        car.setQuantity(qaunty);
        car.setUserId(user.getUserId());
        
        if(cartModel.addCart(car)){
            response.getWriter().print(cartModel.getNubmberOfCartsForUser(user.getUserId()));
        }
    }

    

}
