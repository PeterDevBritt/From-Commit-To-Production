package ch.hsr.mge.gadgeothek.http;

import ch.hsr.mge.gadgeothek.http.MockedCall;
import okhttp3.Request;
import org.hamcrest.Matcher;

public class MockedCallMatching {
    private final Matcher<Request> requestMatcher;
    private final MockedCall response;

    MockedCallMatching(Matcher<Request> requestMatcher, MockedCall response) {
        this.requestMatcher = requestMatcher;
        this.response = response;
    }

    public MockedCall getResponse() {
        return response;
    }

    public boolean matches(Request request) {
        return requestMatcher.matches(request);
    }
}