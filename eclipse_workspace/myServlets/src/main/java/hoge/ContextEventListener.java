package hoge;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
 
@WebListener()//[1]
public class ContextEventListener  implements ServletContextListener {//[2]
    public void contextInitialized(ServletContextEvent event) {//[3]
        ServletContext context = event.getServletContext();//[4]
        context.log("[5]コンテキスト：開始");
    }
    public void contextDestroyed(ServletContextEvent event) {//[6]
        ServletContext context = event.getServletContext();//[7]
        context.log("コンテキスト：終了");//[8]
    }
}