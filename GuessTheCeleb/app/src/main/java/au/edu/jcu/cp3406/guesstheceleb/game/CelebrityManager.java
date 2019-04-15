package au.edu.jcu.cp3406.guesstheceleb.game;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class CelebrityManager {
    private String assetPath;
    private AssetManager assetManager;
//    private String[] imageNames;

    public CelebrityManager(AssetManager assetManager, String assetPath) {
        this.assetManager = assetManager;
        this.assetPath = assetPath;
        try {
            String[] imageNames = assetManager.list(assetPath);
            System.out.println("\n\n\n" + Arrays.toString(imageNames));
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("CelebrityManager constructor failure");
        }

    }

    public Bitmap get(int i) {
        try {
            String[] imageNames = assetManager.list(assetPath);
            InputStream stream = assetManager.open(assetPath + "/" + imageNames[i]);
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getName(int index) {
        String name;
        try {
            String[] imageNames = assetManager.list(assetPath);
            String filename = imageNames[index];
            name = filename.substring(0, filename.lastIndexOf("."));

        } catch (IOException e) {
            e.printStackTrace();
            name = "filename error";
        }
        return name;
    }

    public int count() {
        try {
            String[] imageNames = assetManager.list(assetPath);
            return imageNames.length;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
