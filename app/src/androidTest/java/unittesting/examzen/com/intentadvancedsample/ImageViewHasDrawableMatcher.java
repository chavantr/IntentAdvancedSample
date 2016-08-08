package unittesting.examzen.com.intentadvancedsample;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;

/**
 * Created by Tatyabhau Chavan on 8/3/2016.
 */
public class ImageViewHasDrawableMatcher {


    public static BoundedMatcher<View, ImageView> hasDrawable() {

        return new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override
            protected boolean matchesSafely(ImageView item) {
                return null != item.getDrawable();
            }

            @Override
            public void describeTo(Description description) {

                description.appendText("has drawable");

            }
        };
    }
}
