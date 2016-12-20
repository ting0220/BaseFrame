package com.example.zhaoting.baseframe.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zhaoting on 16/4/20.
 */
public class Utils {
    public static String TAG;
    public static boolean DEBUG = false;
    private static Context mContext;


    private int mScreenWidth = 0;
    private int mScreenHeight = 0;


    /**
     * 使用内部类的方式实现单例模式
     */
    private static class SingletonHolder {
        private static Utils instance = new Utils();
    }

    private Utils() {
    }

    public static Utils getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 初始化
     */
    public void init(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 设置是否显示Log
     *
     * @param isDebug
     * @param TAG
     */
    public void setDebug(boolean isDebug, String TAG) {
        Utils.TAG = TAG;
        Utils.DEBUG = isDebug;
    }


    public void Log(String text) {
        if (DEBUG) {
            Log.i(TAG, text);
        }
    }

    /**
     * Toast显示
     *
     * @param text
     */
    public void ToastShort(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    public void ToastLong(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
    }

    /**
     * 对键盘进行开启、关闭操作
     *
     * @param view
     * @param open true表示开启、false表示关闭
     */
    public void changeInputMethod(View view, boolean open) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (open) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        } else {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    /**
     * 获取app版本号
     */
    public int getAppVersionCode() {

        try {
            PackageManager mPackageManager = mContext.getPackageManager();
            PackageInfo mPackageInfo = mPackageManager.getPackageInfo(mContext.getPackageName(), 0);
            return mPackageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }

    }

    /**
     * 获取app版本名
     */
    public String getAppVersionName() {
        try {
            PackageManager mPackageManager = mContext.getPackageManager();
            PackageInfo mPackageInfo = mPackageManager.getPackageInfo(mContext.getPackageName(), 0);
            return mPackageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * url转换成bitmap
     */
    public Bitmap urlToBitmap(String s) {
        try {
            URL url = new URL(s);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream baos = new BufferedInputStream(is);
            return BitmapFactory.decodeStream(baos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * dp转换成px
     *
     * @param dp
     * @return
     */
    public int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * px转换为dp
     *
     * @param px
     * @return
     */
    public int px2dp(float px) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


    public boolean getTheme() {
        int currentNightMode = mContext.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO: {
                return true;
            }
            case Configuration.UI_MODE_NIGHT_YES: {
                return false;
            }
            case Configuration.UI_MODE_NIGHT_UNDEFINED: {
                return true;
            }
            default:
                return true;
        }
    }

    /**
     * 截屏
     *
     * @param activity
     * @return
     */
    public Bitmap getScreenShot(AppCompatActivity activity) {
        View targetView = activity.getWindow().getDecorView().getRootView();
        targetView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        targetView.setDrawingCacheEnabled(true);
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        Bitmap bmp = Bitmap.createBitmap(targetView.getDrawingCache(), 0, statusBarHeight, width, height - statusBarHeight);
        return bmp;
    }

    /**
     * 截屏后放置到指定视图
     *
     * @param activity
     * @param resId
     * @param imgId
     * @return
     */
    public View setMode(AppCompatActivity activity, int resId, int imgId) {
        Bitmap bmp = getScreenShot(activity);
        LayoutInflater inflater = LayoutInflater.from(activity);
        final View screenShot = inflater.inflate(resId, null);
        ImageView img = (ImageView) screenShot.findViewById(imgId);
        img.setImageBitmap(bmp);
        return screenShot;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public int getScreenWidth() {
        if (mScreenWidth == 0) {
            DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
            mScreenWidth = dm.widthPixels;
        }
        return mScreenWidth;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public int getScreenHeight() {
        if (mScreenHeight == 0) {
            DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
            mScreenHeight = dm.heightPixels;
        }
        return mScreenHeight;
    }


    /**
     * 截图
     *
     * @param view
     * @return
     */
    public Bitmap convertViewToBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();  //启用DrawingCache并创建位图
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache()); //创建一个DrawingCache的拷贝，因为DrawingCache得到的位图在禁用后会被回收
        view.setDrawingCacheEnabled(false);  //禁用DrawingCahce否则会影响性能
        view.destroyDrawingCache();
        return bitmap;
    }


    /**
     * 截取长图
     *
     * @param view
     * @return
     */
    public static Bitmap getScrollViewBitmap(ViewGroup view) {
        int h = 0;
        for (int i = 0; i < view.getChildCount(); i++) {
            h += view.getChildAt(i).getHeight();
        }
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), h, Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

}
