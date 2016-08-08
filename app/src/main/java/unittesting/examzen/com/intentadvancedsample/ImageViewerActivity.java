package unittesting.examzen.com.intentadvancedsample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageViewerActivity extends AppCompatActivity {

    @VisibleForTesting
    protected static final String KEY_IMAGE_DATA = "data";

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    /**
     *
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (null != takePictureIntent.resolveActivity(getPackageManager())) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * @param view
     */
    public void onOpenCamera(View view) {
        dispatchTakePictureIntent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (null == extras || !extras.containsKey(KEY_IMAGE_DATA)) {
                return;
            }
            Bitmap image = (Bitmap) extras.get(KEY_IMAGE_DATA);
            imageView.setImageBitmap(image);
        }
    }
}
