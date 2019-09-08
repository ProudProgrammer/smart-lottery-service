package org.gaborbalazs.smartplatform.lotteryservice.web.wrapper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.gaborbalazs.smartplatform.lotteryservice.web.io.CustomPrintWriter;
import org.gaborbalazs.smartplatform.lotteryservice.web.io.CustomServletOutputStream;

public class BufferedResponseWrapper extends HttpServletResponseWrapper {

    private final HttpServletResponse httpServletResponse;
    private final StringBuilder stringBuilder;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response The response to be wrapped
     *
     * @throws IllegalArgumentException
     *             if the response is null
     */
    public BufferedResponseWrapper(HttpServletResponse response) {
        super(response);
        httpServletResponse = response;
        stringBuilder = new StringBuilder();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new CustomPrintWriter(httpServletResponse.getWriter(), stringBuilder);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new CustomServletOutputStream(httpServletResponse.getOutputStream(), stringBuilder);
    }

    public String getBody() {
        return stringBuilder.toString();
    }
}
