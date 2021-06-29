package hoge;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent ;
import javax.servlet.http.HttpSessionListener;
 
@WebListener()//[1]
public class SessionEventListener  implements HttpSessionListener {//[2]
    public void sessionCreated(HttpSessionEvent  event) {//[3]
               HttpSession session = event.getSession();//[4]
        ServletContext context = session.getServletContext();//[5]
        context.log("[6] セッション開始： ID = " + session.getId());
    }
    public void sessionDestroyed(HttpSessionEvent event) {//[7]
               HttpSession session = event.getSession();//[8]
        ServletContext context = session.getServletContext();//[9]
        context.log("[10] セッション終了：ID = " + session.getId());
    }
}