package ch.hsr.mge.gadgeothek.http;

import okhttp3.Request;

public class Endpoint {

    private final HttpMethod method;
    private final String path;

    public static Endpoint get(String path) {
        return new Endpoint(HttpMethod.GET, path);
    }

    public static Endpoint post(String path) {
        return new Endpoint(HttpMethod.POST, path);
    }

    private Endpoint(HttpMethod method, String path) {
        this.method = method;
        this.path = path;
    }

    public boolean matchesRequest(Request request) {
        return request.method().equals(method.name())
                && request.url().encodedPath().equals(path);
    }

    @Override
    public String toString() {
        return method.name() + " - " + path;
    }

    private enum HttpMethod {
        GET, POST
    }
}
