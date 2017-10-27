package ch.hsr.mge.gadgeothek.http;

import static ch.hsr.mge.gadgeothek.http.Endpoint.get;
import static ch.hsr.mge.gadgeothek.http.Endpoint.post;

/**
 * Created by Peter Britt on 25.10.2017.
 */

public class MockedBackend {
    private final HttpProxy httpProxy;

    public MockedBackend(HttpProxy httpProxy) {
        this.httpProxy = httpProxy;
    }

    public void givenRegisterSuccessful() {
        httpProxy.mockCall(post("/public/register"), "true");
    }

    public void givenRegisterUnsuccessful() {
        httpProxy.mockCall(post("/public/register"), 500);
    }

    public void givenLoginSuccessful(String customerId, String securityToken) {
        String loginResponse = "{\"customerId\": \"" + customerId + "\", \"securityToken\": \"" + securityToken + "\"}";
        httpProxy.mockCall(post("/public/login"), loginResponse);
    }

    public void givenEmptyLoans() {
        httpProxy.mockCall(get("/public/loans"), "[]");
    }

    public void givenEmptyReservations() {
        httpProxy.mockCall(get("/public/reservations"), "[]");
    }

}
