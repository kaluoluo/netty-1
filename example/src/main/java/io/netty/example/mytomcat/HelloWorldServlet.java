package io.netty.example.mytomcat;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author jbkj
 */
public class HelloWorldServlet extends HttpServlet {
    private String message;

    @Override
    public void init() {
        // 执行必需的初始化
        message = "Hello World";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getParameterNames();
        // 实际的逻辑是在这里
        PrintWriter writer = response.getWriter();
        writer.print("<h1>"+message+"</h1>");

    }
}
