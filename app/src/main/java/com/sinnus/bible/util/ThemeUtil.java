package com.sinnus.bible.util;

/**
 * Created by sinnus on 2015/8/31.
 */

import android.app.Activity;
import android.content.Context;

import com.sinnus.bible.R;
import com.sinnus.bible.fragment.MainFragment;

public class ThemeUtil {

    public static Integer[] themeColors = {R.color.green, R.color.blue, R.color.red, R.color.pink, R.color.black};
    private static int[] styles = {R.style.GreenTheme, R.style.BlueTheme, R.style.RedTheme, R.style.PinkTheme,
            R.style.BlackTheme};

    public static Integer[] backGroundColors = {
            R.color.main_background, R.color.black
    };
    public static void setTheme(Activity activity, int theme) {
        if (activity == null)
            return;
        activity.setTheme(styles[theme]);
    }
//    public static void setBackGroundColor(MainFragment mainFragment,int position){
//        mainFragment.viewPager.setBackgroundColor(
//                mainFragment.getActivity().getResources().getColor(backGroundColors[position])
//        );
//        mainFragment.slidingDrawerContent.setBackgroundColor(
//                mainFragment.getActivity().getResources().getColor(backGroundColors[position]));
//    }

    public static int getCurrentTheme(Context context) {
        int value = PreferenceUtil.getInstance(context)
                .getIntParam(context.getString(R.string.change_theme_key), 0);
        return value;
    }
    public static int getCurrentBackgroundColor(Context context){
        int value = PreferenceUtil.getInstance(context)
                .getIntParam(context.getString(R.string.change_background_color_key), 0);
        return value;
    }
    public static int getCurrentBackgroundColorResourceId(Context context,int position){
        return backGroundColors[position];
    }

}
