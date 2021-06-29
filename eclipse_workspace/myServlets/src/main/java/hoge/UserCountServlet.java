package hoge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserCountServlet")
public class UserCountServlet extends HttpServlet {
 
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        StringBuffer buffer = new StringBuffer();
 
        buffer.append("<html><head><title>Servlet 8章実習課題 1");
        buffer.append("</title></head><body>");
        CountBean countBean = (CountBean) request.getSession().getAttribute("userCount");
        if (countBean != null) {
            buffer.append("現在の利用者は").append(countBean.getCount()).append("人です");
        } else {
            buffer.append("Tomcatリロード前のSessionでアクセスしています。");
        }
        buffer.append("</body></html>");
 
        response.setContentType("text/html; charset=Shift_JIS");
        PrintWriter writer = response.getWriter();
        writer.println(buffer.toString());
    }
}