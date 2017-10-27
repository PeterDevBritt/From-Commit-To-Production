package ch.hsr.mge.gadgeothek;

import android.app.Application;
import android.content.SharedPreferences;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.service.LibraryService;
import okhttp3.OkHttpClient;

import static ch.hsr.mge.gadgeothek.ui.AbstractAuthenticationActivity.PREFERENCES;

/**
 * Created by Peter Britt on 25.10.2017.
 */

public class GadgeothekApplication extends Application {

    public static LibraryService libraryService;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences preferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        String url = preferences.getString(
                getString(R.string.settings_server_address),
                getString(R.string.settings_default_server));

        libraryService = createLibraryService(url);
    }

    protected LibraryService createLibraryService(String url) {
        return new LibraryService(url, new OkHttpClient());
    }
}
