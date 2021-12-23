package io.netty.example.mytomcat;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.example.mytomcat.stream.DefaultOutputStream;
import io.netty.example.mytomcat.stream.DefaultWriter;
import io.netty.example.mytomcat.stream.OutputBuffer;
import io.netty.handler.codec.http.*;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class Response extends ProxyResponse {

    private ChannelHandlerContext ctx;
    private HttpRequest httpRequest;
    private PrintWriter writer;
    private OutputBuffer outputBuffer;

    public Response(ChannelHandlerContext ctx, HttpRequest httpRequest, OutputBuffer outputBuffer) {
        this.ctx = ctx;
        this.httpRequest = httpRequest;
        this.outputBuffer = outputBuffer;
    }

    public void write(String out) throws IOException{
        if(out == null || out.equals("")){
            return ;
        }
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(out.getBytes("UTF-8")));
        response.headers().set("Content-Type","text/html;");

        // todo 注意必须在使用完之后，close channel
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new DefaultOutputStream(outputBuffer);
    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public PrintWriter getWriter() throws IOException {

        this.writer = new DefaultWriter(outputBuffer);
        return this.writer;
    }
}
