package io.netty.example.mytomcat;


import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.example.mytomcat.stream.OutputBuffer;
import io.netty.handler.codec.http.HttpRequest;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import java.util.Map;

public class TomcatHandler extends ChannelInboundHandlerAdapter {

    private Map<String, Servlet> servletMapping;

    public TomcatHandler(Map<String, Servlet> servletMapping){
        this.servletMapping = servletMapping;
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                if(msg instanceof HttpRequest) {
                    System.out.println("------------");
                    HttpRequest httpRequest = (HttpRequest) msg;
                    //输出缓冲区
                    OutputBuffer outputBuffer = new OutputBuffer(1024);
                    // 转交给tomcat的Request和Response
                    Request request = new Request(ctx,httpRequest);
                    Response response = new Response(ctx,httpRequest,outputBuffer);
                    request.getParameterNames();
                    String url = request.getUrl();
                    try {
                        if(servletMapping.containsKey(url)) {
                            Servlet servlet = servletMapping.get(url);
                            servlet.init((HttpServlet) servlet);
                            servlet.service(request,response);
                            response.write(outputBuffer.getStrBuf());
                        }else {
                            response.write("404-not found");
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
