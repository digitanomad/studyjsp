package ch16.customtag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SimpleWelcomeTag extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		try {
			getJspContext().getOut().print("SimpleTag : Welcome to My CustomTag");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
