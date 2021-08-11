package hoge;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class FormReceiver
 */
@WebServlet("/FormReceiver")
@MultipartConfig(location="C:\\Users\\Yuki\\Desktop\\PL_sandbox\\eclipse_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\myServlets", maxFileSize=1048576)
public class FormReceiver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormReceiver() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  PrintWriter out = response.getWriter();
	  out.append("test: " + request.getParameter("test"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  // application/x-www-form-urlencodedの場合
	  doGet(request, response);
	  
	  // multipart/form-dataの場合
//	  Collection<Part> parts = request.getParts();
//
//    parts.stream().forEach(part -> {
//        log("name:" + part.getName());
//
//        String contentType = part.getContentType();
//        log("contentType:" + contentType);
//        if ( contentType == null) {
//            try(InputStream inputStream = part.getInputStream()) {
//                BufferedReader bufReader = new BufferedReader(new InputStreamReader(inputStream));
//                log( bufReader.lines().collect(Collectors.joining()));
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    });
	}

}
