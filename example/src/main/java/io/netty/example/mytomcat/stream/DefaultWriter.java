package io.netty.example.mytomcat.stream;

import java.io.*;
import java.util.Locale;

public class DefaultWriter extends PrintWriter {
    protected OutputBuffer ob;

    public DefaultWriter(OutputBuffer ob) {
        super(ob);
        this.ob = ob;
    }

    @Override
    public void flush() {
        super.flush();
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public boolean checkError() {
        return super.checkError();
    }

    @Override
    protected void setError() {
        super.setError();
    }

    @Override
    protected void clearError() {
        super.clearError();
    }

    @Override
    public void write(int c) {
        super.write(c);
    }

    @Override
    public void write(char[] buf, int off, int len) {
        super.write(buf, off, len);
    }

    @Override
    public void write(char[] buf) {
        super.write(buf);
    }

    @Override
    public void write(String s, int off, int len) {
        try {
            ob.write(s, off, len);
        } catch (IOException e) {
            // todo
            //error = true;
        }
    }

    @Override
    public void write(String s) {
        write(s, 0, s.length());
    }

    @Override
    public void print(boolean b) {
        super.print(b);
    }

    @Override
    public void print(char c) {
        super.print(c);
    }

    @Override
    public void print(int i) {
        super.print(i);
    }

    @Override
    public void print(long l) {
        super.print(l);
    }

    @Override
    public void print(float f) {
        super.print(f);
    }

    @Override
    public void print(double d) {
        super.print(d);
    }

    @Override
    public void print(char[] s) {
        super.print(s);
    }

    @Override
    public void print(String s) {
        if (s == null) {
            s = "null";
        }
        write(s);
    }

    @Override
    public void print(Object obj) {
        super.print(obj);
    }

    @Override
    public void println() {
        super.println();
    }

    @Override
    public void println(boolean x) {
        super.println(x);
    }

    @Override
    public void println(char x) {
        super.println(x);
    }

    @Override
    public void println(int x) {
        super.println(x);
    }

    @Override
    public void println(long x) {
        super.println(x);
    }

    @Override
    public void println(float x) {
        super.println(x);
    }

    @Override
    public void println(double x) {
        super.println(x);
    }

    @Override
    public void println(char[] x) {
        super.println(x);
    }

    @Override
    public void println(String x) {
        super.println(x);
    }

    @Override
    public void println(Object x) {
        super.println(x);
    }

    @Override
    public PrintWriter printf(String format, Object... args) {
        return super.printf(format, args);
    }

    @Override
    public PrintWriter printf(Locale l, String format, Object... args) {
        return super.printf(l, format, args);
    }

    @Override
    public PrintWriter format(String format, Object... args) {
        return super.format(format, args);
    }

    @Override
    public PrintWriter format(Locale l, String format, Object... args) {
        return super.format(l, format, args);
    }

    @Override
    public PrintWriter append(CharSequence csq) {
        return super.append(csq);
    }

    @Override
    public PrintWriter append(CharSequence csq, int start, int end) {
        return super.append(csq, start, end);
    }

    @Override
    public PrintWriter append(char c) {
        return super.append(c);
    }
}
