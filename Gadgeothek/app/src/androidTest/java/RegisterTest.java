import android.test.suitebuilder.annotation.LargeTest;

import ch.hsr.mge.gadgeothek.R;
import ch.hsr.mge.gadgeothek.ui.RegisterActivity;

/**
 * Created by peter on 21.10.17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterTest {

    @Rule
    public ActivityTestRule<RegisterActivity> activityTestRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void showsRegisterActivity() {
        onView(withId(R.id.registerButton)).check(matches.isDisplayed()));
    }

}
