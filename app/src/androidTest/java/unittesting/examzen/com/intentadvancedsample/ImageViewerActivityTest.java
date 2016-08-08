package unittesting.examzen.com.intentadvancedsample;

import android.app.Activity;
import android.app.Instrumentation.*;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.not;
import static unittesting.examzen.com.intentadvancedsample.ImageViewHasDrawableMatcher.hasDrawable;
import static android.support.test.espresso.action.ViewActions.click;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Admin on 8/3/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ImageViewerActivityTest {


    @Rule
    public IntentsTestRule<ImageViewerActivity> intentsTestRule = new IntentsTestRule<>(ImageViewerActivity.class);


    @Before
    public void stubCameraIntent() {
        ActivityResult activityResult = createImageCaptureActivityResultStub();
        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(activityResult);
    }


    @Test
    public void takePhoto_drawableIsApplied() {

        onView(withId(R.id.imageView)).check(matches(not(hasDrawable())));

        waiting();

        onView(withId(R.id.button_take_photo)).perform(click());

        waiting();

        onView(withId(R.id.imageView)).check(matches(hasDrawable()));

        waiting();

    }

    private void waiting() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ActivityResult createImageCaptureActivityResultStub() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ImageViewerActivity.KEY_IMAGE_DATA, BitmapFactory.decodeResource(intentsTestRule.getActivity().getResources(), R.mipmap.ic_launcher));
        Intent resultData = new Intent();
        resultData.putExtras(bundle);
        return new ActivityResult(Activity.RESULT_OK, resultData);
    }
}
