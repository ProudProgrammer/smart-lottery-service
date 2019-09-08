package org.gaborbalazs.smartplatform.lotteryservice.web.io;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

import org.springframework.util.Assert;

public class CustomServletOutputStream extends ServletOutputStream {

    private final OutputStream outputStream;
    private final StringBuilder stringBuilder;

    public CustomServletOutputStream(OutputStream outputStream, StringBuilder stringBuilder) {
        Assert.notNull(outputStream, "OutputStream must not be null");
        Assert.notNull(outputStream, "StringBuilder must not be null");

        this.outputStream = outputStream;
        this.stringBuilder = stringBuilder;
    }

    @Override
    public void write(int c) throws IOException {
        stringBuilder.append((char) c);
        outputStream.write(c);
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        throw new UnsupportedOperationException();
    }
}
