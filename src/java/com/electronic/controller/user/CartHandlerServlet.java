package com.electronic.controller.user;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import com.electronic.dao.CartModel;
import com.electronic.beans.User;
import com.electronic.beans.CartProduct;
/**
 *
 * @author Dhannu
 */
@WebServlet("/CartHandlerServlet")
public class CartHandlerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<CartProduct> carts = new ArrayList<CartProduct>();
        User usr = (User) request.getSession().getAttribute("LoginUser");
        int usrId = usr.getUserId();
        CartModel cartModel = new CartModel();
        carts = cartModel.getProductFromCart(usrId);

        request.setAttribute("carts", carts);

        String nextJSP = "/checkout.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
    }
}
