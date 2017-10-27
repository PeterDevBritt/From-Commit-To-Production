package ch.hsr.mge.gadgeothek;

import ch.hsr.mge.gadgeothek.service.LibraryService;
import ch.hsr.mge.gadgeothek.http.HttpProxy;

/**
 * Created by Peter Britt on 25.10.2017.
 */

public class TestApplication extends GadgeothekApplication {

    public static HttpProxy httpProxy;

    @Override
    protected LibraryService createLibraryService(String url) {
        httpProxy = new HttpProxy();
        return new LibraryService(url, httpProxy);
    }
}
