package au.edu.jcu.cp3406.guesstheceleb;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ImageManager {
    private String assetPath;
    private AssetManager assetManager;
//    private String[] imageNames;

    ImageManager(AssetManager assetManager, String assetPath) {
        this.assetManager = assetManager;
        this.assetPath = assetPath;
        try {
            String[] imageNames = assetManager.list(assetPath);
            System.out.println("\n\n\n" + Arrays.toString(imageNames));
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("ImageManager constructor failure");
        }

    }

    Bitmap get(int i) {
        try {
            String[] imageNames = assetManager.list(assetPath);
            InputStream stream = assetManager.open(assetPath + "/" + imageNames[i]);
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
