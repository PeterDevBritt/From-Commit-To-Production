package ch.hsr.mge.gadgeothek.http;

import ch.hsr.mge.gadgeothek.http.Endpoint;
import okhttp3.Request;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class EndpointRequestMatcher extends TypeSafeMatcher<Request> {

    private final Endpoint endpoint;

    public EndpointRequestMatcher(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    protected boolean matchesSafely(Request request) {
        return endpoint.matchesRequest(request);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Request with endpoint ").appendValue(endpoint);
    }
}
