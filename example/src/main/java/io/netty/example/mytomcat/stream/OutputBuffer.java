package io.netty.example.mytomcat.stream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.IOException;
import java.io.Writer;

public class OutputBuffer extends Writer {

    private byte[] buff;
    private char[] cbuf;
    private ByteBuf byteBuf;
    private String strBuf;

    public OutputBuffer(int size) {
        this.buff = new byte[size];
        this.cbuf = new char[size];
        this.byteBuf = Unpooled.buffer();
    }

    public String getStrBuf() {
        return strBuf;
    }

    @Override
    public void write(int c) throws IOException {
        super.write(c);
    }

    @Override
    public void write(char[] cbuf) throws IOException {
        super.write(cbuf);
    }

    @Override
    public void write(String str) throws IOException {
        super.write(str);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        //str.getChars(off,off+len,cbuf,end);
        //cb.append(s, off, len);
        this.strBuf = str;
    }

    @Override
    public Writer append(CharSequence csq) throws IOException {
        return super.append(csq);
    }

    @Override
    public Writer append(CharSequence csq, int start, int end) throws IOException {
        return super.append(csq, start, end);
    }

    @Override
    public Writer append(char c) throws IOException {
        return super.append(c);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

    }

    @Override
    public void flush() throws IOException {

    }

    @Override
    public void close() throws IOException {

    }
}
