package com.electronic.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.electronic.beans.User;
import com.electronic.dao.UserDbModel;
import com.electronic.util.FileUpload;

/**
 *
 * @author Dhannu
 */
@MultipartConfig
@WebServlet(name = "Profile", urlPatterns = {"/Profile"})
public class Profile extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            //get id from session
            User user = (User) request.getSession().getAttribute("LoginUser");

            User newUser = (User) user.clone();
            String path = request.getServletContext().getRealPath("");
            //get request paramater & update object user
            newUser.setUserName(request.getParameter("username"));
            newUser.setEmail(request.getParameter("email"));
            newUser.setPassword(request.getParameter("password"));
            newUser.setAddress(request.getParameter("address"));
            newUser.setJob(request.getParameter("job"));
            

            //-------------- upload photo ------------------
            Part filePart = request.getPart("image");
            if (filePart.getSize() != 0) {      //if photo uploaded
                
                
                try{
                    String uploadedpath = FileUpload.uploadImage(filePart, path);
                    newUser.setPhoto(uploadedpath);
                }catch(Exception ex){
                    ex.printStackTrace();
                    request.setAttribute("message", "please choose image only");
                    request.getRequestDispatcher("/Failed.jsp").forward(request, response);
                    return ;
                }
                
            }

            if (new UserDbModel().updateUser(newUser,path)) {
                //update user successfully
                newUser.setPassword("");    //remove password from object
                request.getSession().setAttribute("LoginUser", newUser); //update session user
                //redirect to profile
                request.setAttribute("messageInfo", "update user info Successfully");
                request.getRequestDispatcher("/profile.jsp").forward(request, response);
            } else {
                //can't update user
                request.setAttribute("message", "can't update user now .. :(<br/>"
                        + "email or credit card used before");
                request.getRequestDispatcher("/Failed.jsp").forward(request, response);
            }
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
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
