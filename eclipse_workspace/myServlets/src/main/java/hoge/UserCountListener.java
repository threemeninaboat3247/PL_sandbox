package hoge;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
 
public class UserCountListener implements HttpSessionListener {
 
    private CountBean count = new CountBean();
     
    public void sessionCreated(HttpSessionEvent event) {
        count.increase();
        event.getSession().setAttribute("userCount", count);
    }   
 
    public void sessionDestroyed(HttpSessionEvent event) {
        count.decrease();
    }
 
}

