package io.netty.example.mytomcat.stream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class DefaultOutputStream extends ServletOutputStream {

    protected OutputBuffer ob;

    public DefaultOutputStream(OutputBuffer ob) {
        this.ob = ob;
    }

    @Override
    public void write(int b) {

    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
    }

    @Override
    public boolean isReady() {
        return false;
    }
}
