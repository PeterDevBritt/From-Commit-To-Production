package ch.hsr.mge.gadgeothek.http;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.util.ArrayList;
import java.util.List;

public class HttpProxy extends OkHttpClient {

    private final List<MockedCallMatching> mockedCalls = new ArrayList<>();

    public void mockCall(Endpoint endpoint, String response) {
        EndpointRequestMatcher matcher = new EndpointRequestMatcher(endpoint);
        MockedCall mockedCall = new MockedCall(response);
        mockedCalls.add(new MockedCallMatching(matcher, mockedCall));
    }

    public void mockCall(Endpoint endpoint, int statusCode) {
        EndpointRequestMatcher matcher = new EndpointRequestMatcher(endpoint);
        MockedCall mockedCall = new MockedCall(statusCode);
        mockedCalls.add(new MockedCallMatching(matcher, mockedCall));
    }

    public void clear() {
        mockedCalls.clear();
    }

    @Override
    public Call newCall(Request request) {
        MockedCall mockedCall = findMockedCall(request);
        if (mockedCall != null) {
            return mockedCall;
        }

        return super.newCall(request);
    }

    private MockedCall findMockedCall(Request request) {
        for (MockedCallMatching mockedCallMatching : mockedCalls) {
            if (mockedCallMatching.matches(request)) {
                return mockedCallMatching.getResponse();
            }
        }

        return null;
    }
}
