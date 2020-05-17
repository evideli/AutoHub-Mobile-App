package gr.teicm.msc.autohub.classes;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.io.File;

public class FileCache {
    private static final String EXTERNAL_CACHE_DIR_NAME = "LazyList";

    private File cacheDir;
    private Context mContext;

    private File getAppExternalStorageDirectory() {
        File appExtDir = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            File[] extDirs = mContext.getExternalFilesDirs(null);
            if (extDirs != null && extDirs.length > 0) {
                appExtDir = extDirs[0];
                if (extDirs.length > 1) {
                    for (File d : extDirs) {
                        if (android.os.Environment.isExternalStorageEmulated(d)) {
                            appExtDir = d;
                            break;
                        }
                    }
                }
            } else {
                appExtDir = mContext.getCacheDir();
            }
        } else {
            appExtDir = new File(android.os.Environment.getExternalStorageDirectory(), EXTERNAL_CACHE_DIR_NAME);
        }

        return appExtDir;
    }


    public FileCache(Context context) {
        mContext = context;

        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            cacheDir = getAppExternalStorageDirectory();
        } else {
            cacheDir = mContext.getCacheDir();
        }

        if (!cacheDir.exists()) {
            Boolean res = cacheDir.mkdirs();

            Log.d("FileCache", "OK: " + res.toString());
        }
    }

    public File getFile(String url) {
        //I identify images by hashcode. Not a perfect solution, good for the demo.
        String filename = String.valueOf(url.hashCode());
        //Another possible solution (thanks to grantland)
        //String filename = URLEncoder.encode(url);
        File f = new File(cacheDir, filename);
        return f;

    }

    public void clear() {
        File[] files = cacheDir.listFiles();
        if (files == null)
            return;
        for (File f : files)
            f.delete();
    }
}
