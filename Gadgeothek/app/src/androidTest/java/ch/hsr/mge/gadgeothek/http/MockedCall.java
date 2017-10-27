package ch.hsr.mge.gadgeothek.http;

import okhttp3.*;

import java.io.IOException;

class MockedCall implements Call {

    private final String responseBody;
    private final int statusCode;

    public MockedCall(int statusCode) {
        this.statusCode = statusCode;
        this.responseBody = "";
    }

    public MockedCall(String responseBody) {
        this.responseBody = responseBody;
        this.statusCode = 200;
    }

    @Override
    public Request request() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Response execute() throws IOException {
        Request request = new Request.Builder().url("http://someurl").build();
        ResponseBody body = ResponseBody.create(MediaType.parse("application/json"), responseBody);
        return new Response.Builder()
                .request(request)
                .body(body)
                .protocol(Protocol.HTTP_1_1)
                .code(statusCode)
                .message("OK")
                .build();
    }

    @Override
    public void enqueue(Callback responseCallback) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public boolean isCanceled() {
        return false;
    }
}
