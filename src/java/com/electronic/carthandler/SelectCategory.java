
package com.electronic.carthandler;

import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import com.electronic.dao.CategoryModel;
public class SelectCategory extends SimpleTagSupport {
/**
 *
 * @author Dhannu
 */
    private int selectID;

    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            out.print("<select name='category'>");
            String selected = ""; 
            //get all category
            ArrayList<com.electronic.beans.Category> categoriess = new CategoryModel().AllCategoriess();
            for (com.electronic.beans.Category categories : categoriess) {
                if(selectID == categories.getId())
                    selected = "selected";
                out.print("<option value='"+categories.getId()+"' "+selected+">"+categories.getName()+"</option>");
                
                selected ="";
            }
            out.print("</select>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Category tag", ex);
        }
    }

    public void setSelectID(int selectID) {
        this.selectID = selectID;
    }
    
}
