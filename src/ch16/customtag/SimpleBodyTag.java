package ch16.customtag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SimpleBodyTag extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {
		try {
			JspFragment fragment = getJspBody();
			fragment.invoke(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
