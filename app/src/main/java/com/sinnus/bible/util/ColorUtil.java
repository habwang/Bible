package com.sinnus.bible.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by sinnus on 2015/8/31.
 */
public class ColorUtil {
    public void getPixColor(Context context,int pictureId){
        Bitmap src =  BitmapFactory.decodeResource(context.getResources(), pictureId);
        int A, R, G, B;
        int pixelColor;
        int height = src.getHeight();
        int width = src.getWidth();

        pixelColor = src.getPixel(width/2, height/2);
        A = Color.alpha(pixelColor);
        R = Color.red(pixelColor);
        G = Color.green(pixelColor);
        B = Color.blue(pixelColor);

        Log.e("A:", A + "");
        Log.e("R:", R+"");
        Log.e("G:", G+"");
        Log.e("B:", B+"");
        Log.e("hahahaha:",Integer.toHexString(A)+Integer.toHexString(R)+
                Integer.toHexString(G)+Integer.toHexString(B));

    }
}
