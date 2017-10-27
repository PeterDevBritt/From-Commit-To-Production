package ch.hsr.mge.gadgeothek;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ch.hsr.mge.gadgeothek.ui.RegisterActivity;
import ch.hsr.mge.gadgeothek.http.HttpProxy;
import ch.hsr.mge.gadgeothek.http.MockedBackend;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Created by Peter Britt on 23.10.2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterTest {

    private String sName = "pb";
    private String sMail = "pb@hsr.ch";
    private String sMaNr = "2468";
    private String sPswd = "1234";

    @Rule
    public ActivityTestRule<RegisterActivity> activityTestRule = new ActivityTestRule<>(RegisterActivity.class);

    private MockedBackend backend;

    @Before
    public void setUp() {
        HttpProxy httpProxy = TestApplication.httpProxy;
        httpProxy.clear();
        backend = new MockedBackend(TestApplication.httpProxy);
    }

    /*
    @Test
    public void testInputErrorRegisterActivity() {
        // Fill in text fields except email
        onView(withId(R.id.name)).perform(typeText(sName));
        onView(withId(R.id.email)).perform(typeText(""));
        onView(withId(R.id.matrikelnr)).perform(typeText(sMaNr));
        onView(withId(R.id.password)).perform(typeText(sPswd));
        // Perform register action
        onView(withId(R.id.registerButton)).perform(click());
        // Check email is empty
        onView(withId(R.id.email)).check(matches(hasErrorText("This field is required")));
    }
    */

    @Test
    public void testErrorRegisterActivity() {
        // Setup backend
        backend.givenRegisterUnsuccessful();

        // Fill in text fields
        onView(withId(R.id.name)).perform(typeText(sName));
        closeSoftKeyboard();
        onView(withId(R.id.email)).perform(typeText(sMail));
        closeSoftKeyboard();
        onView(withId(R.id.matrikelnr)).perform(typeText(sMaNr));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText(sPswd));
        closeSoftKeyboard();

        // Perform register action
        onView(withId(R.id.registerButton)).perform(click());

        // Check dismiss snackbar
        //onView(allOf(withId(R.string.dismiss_snackbar), withText(startsWith("Login failed")))).check(matches(isDisplayed()));
        //onView(allOf(withId(R.string.dismiss_snackbar), withText(startsWith("Login failed")))).check(doesNotExist());
        onView(withId(android.support.design.R.id.snackbar_text)).check(matches(isDisplayed()));
    }

    @Test
    public void testOkRegisterActivity() {
        backend.givenRegisterSuccessful();
        backend.givenLoginSuccessful("1234", "token");
        backend.givenEmptyLoans();
        backend.givenEmptyReservations();

        // Fill in text fields
        onView(withId(R.id.name)).perform(typeText(sName));
        closeSoftKeyboard();
        onView(withId(R.id.email)).perform(typeText(sMail));
        closeSoftKeyboard();
        onView(withId(R.id.matrikelnr)).perform(typeText(sMaNr));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText(sPswd));
        closeSoftKeyboard();

        // Perform register action
        onView(withId(R.id.registerButton)).perform(click());

        // Check gadgeothek page
        onView(withId(R.id.viewpager)).check(matches(isDisplayed()));
    }

}
