/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author yuno
 */
public class IfEmptyMarkTag extends SimpleTagSupport {
    
    private String field;
    private String color = "red";
    
    public void setField(String field) {
        this.field = field;
    }
    
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void doTag() throws JspException {
        
        JspWriter out = getJspContext().getOut();
        
        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");
            
            if(field == null || field.length()==0) {
                out.print("<font color=" + color + ">*</font>");
            }

            //JspFragment f = getJspBody();
            //if (f != null) {
            //    f.invoke(out);
            //}

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");

        } catch (java.io.IOException ex) {
            throw new JspException("Error in IfEmptyMarkTag tag", ex);
        }
    }
}
