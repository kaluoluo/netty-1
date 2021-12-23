package io.netty.example.mytomcat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;

import javax.servlet.ServletRequest;

public class Request extends ProxyRequest{

    private ChannelHandlerContext ctx;
    private HttpRequest httpRequest;

    public Request(ChannelHandlerContext ctx, HttpRequest httpRequest) {
        this.ctx = ctx;
        this.httpRequest = httpRequest;
    }

    public String getUrl() {
        return httpRequest.getUri();
    }

    @Override
    public String getMethod() {
        return httpRequest.getMethod().name();
    }






}
