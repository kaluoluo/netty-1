package io.netty.example.mytomcat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Tomcat {

    private int port = 8080;
    private final String WEB_INF = "/Users/jbkj/webapp/";
    private Map<String, Servlet> servletMapping = new HashMap<String, Servlet>();
    private Properties webProperties = new Properties();
    Logger logger = LoggerFactory.getLogger(Tomcat.class);

    /**
     * 加载文件，同时初始化servletMapping
     */
    private void init() {
        try {
            FileInputStream fileInputStream = new FileInputStream(WEB_INF+"web.properties");
            webProperties.load(fileInputStream);
            for (Object k : webProperties.keySet()) {
                String key = k.toString();
                if(key.endsWith(".url")){
                    String incompleteServletNameKey = key.replaceAll("\\.url$","");
                    String url = webProperties.getProperty(key);
                    String className = webProperties.getProperty(incompleteServletNameKey + ".className");
                    Servlet servlet = (HttpServlet) Class.forName(className).newInstance();
                    servletMapping.put(url,servlet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void start() {
        init();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new HttpResponseEncoder());
                            socketChannel.pipeline().addLast(new HttpRequestDecoder());
                            socketChannel.pipeline().addLast(new TomcatHandler(servletMapping));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            logger.info("Tomcat启动成功。。。");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new Tomcat().start();
    }
}
