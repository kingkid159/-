package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

//p540
//요청 URI를 명령어로 사용하기위한 클래스이다
/*web.xml문서에서 
   <servlet>
		<servlet-name>ControllerUsingURI</servlet-name>
		<servlet-class>mvc.controller.ControllerUsingURI</servlet-class>
		<init-param>
			<param-name>configFile</param-name>
			<param-value>
                /WEB-INF/commandHandlerURI.properties
            </param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
*/
public class ControllerUsingURI extends HttpServlet {
	//필드
    // <커맨드, 핸들러인스턴스> 매핑 정보 저장
    private Map<String, CommandHandler> commandHandlerMap = 
    		new HashMap<>();

    //메서드
	//init()는 서블릿을 처음 메모리에 올릴때 실행되어, 서블릿을 초기화하며 처음에 한번만 실행. 
    public void init() throws ServletException {
		// web.xml문서 설정부분에서   /WEB-INF/commandHandlerURI.properties를 가져와
		// String타입  configFile변수에 저장
        String configFile = getInitParameter("configFile");
        Properties prop = new Properties();//Properties객체
        String configFilePath = getServletContext().getRealPath(configFile);
        
        //실행동작할 수 있는 파일로 만든다
        try (FileReader fis = new FileReader(configFilePath)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new ServletException(e);
        }
        Iterator keyIter = prop.keySet().iterator();
        while (keyIter.hasNext()) {
            String command = (String) keyIter.next();
            String handlerClassName = prop.getProperty(command);
            try {
                Class<?> handlerClass = Class.forName(handlerClassName);
                CommandHandler handlerInstance = 
                        (CommandHandler) handlerClass.newInstance();
                commandHandlerMap.put(command, handlerInstance);
            } catch (ClassNotFoundException | InstantiationException 
            		| IllegalAccessException e) {
                throw new ServletException(e);
            }
        }
    }

	//doGet()는 get방식으로 요청시 호출되는 메서드
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

	//doPost()는 post방식으로 요청시 호출되는 메서드
    protected void doPost(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

	/*URI를 명령어로 사용하려먼 컨트롤러 서블릿의 process()에서 인식할 수 있어야 한다.*/
    private void process(HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException {
		
		//요청URI에서 request.getContextPath()부분을 제거하여 요청URI만 사용하기
		String command = request.getRequestURI();// /컨텍스트패스/member/joinFrm.do
		if (command.indexOf(request.getContextPath()) == 0) {//요청URI에서 request.getContextPath()부분찾기
			command = command.substring(request.getContextPath().length());
		}
		
		//담당컨트롤러를 가져오기
        CommandHandler handler = commandHandlerMap.get(command);
        if (handler == null) {
            handler = new NullHandler();
        }
        String viewPage = null;
        try {
        	//(가져온) 담당컨트롤러의 process()메서드를 호출해라
        	//(여기에서는)모든 컨트롤러는 반드시 CommandHandler인터페이스를 구현하고 있다.
        	//모든 컨트롤러의 process() 리턴하는 String타입의 view(jsp문서)를
        	//String타입 viewPage에 저장
            viewPage = handler.process(request, response);
        } catch (Throwable e) {
            throw new ServletException(e);
        }
        if (viewPage != null) {//viewPage가 존재하면
        	//해당  viewPage로 페이지를 이동해라-> 브라우저에 출력해라
	        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
	        System.out.println("viewPage="+viewPage);
	        System.out.println("dispatcher="+dispatcher);
	        dispatcher.forward(request, response);
        }
    }
}








