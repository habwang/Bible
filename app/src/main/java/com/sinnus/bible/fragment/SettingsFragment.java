package com.sinnus.bible.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.jenzz.materialpreference.Preference;
import com.jenzz.materialpreference.SwitchPreference;
import com.sinnus.bible.R;
import com.sinnus.bible.util.PreferenceUtil;
import com.sinnus.bible.util.ThemeUtil;

public class SettingsFragment extends PreferenceFragment {
    public static final String SETTING_PREFERENCE_FILE_NAME = "SETTING_PREFERENCE";
    public PreferenceUtil preferenceUtil = null;
    public Preference feedbackPreference;
    public Preference giveFavorPreference;
    public Preference changeThemePreference;
    public SwitchPreference nightThemePreference;
    public boolean nightMode;

    public static boolean THEME_COLOR_CHANGED = false;
    public static boolean BACKGROUND_COLOR_CHANGED = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
        getPreferenceManager().setSharedPreferencesName(SETTING_PREFERENCE_FILE_NAME);

        nightThemePreference = (SwitchPreference) findPreference(getString(R.string.night_mode_key));
        nightMode = preferenceUtil.getBooleanParam(getString(R.string.night_mode_key));
        nightThemePreference.setChecked(nightMode);


        changeThemePreference = (Preference) findPreference(getString(R.string.change_theme_key));


        feedbackPreference = (Preference) findPreference(getString(R.string.advice_feedback_key));

        giveFavorPreference = (Preference) findPreference(getString(R.string.give_favor_key));

//        initFeedbackPreference();
//        initEverAccount();
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, android.preference.Preference preference) {
        if (!isResumed() || preference == null) {
            return super.onPreferenceTreeClick(preferenceScreen, preference);
        }
        String key = preference.getKey();
        if (TextUtils.equals(key, getString(R.string.night_mode_key))) {
            nightMode = !nightMode;
            preferenceUtil.saveBooleanParam(getString(R.string.night_mode_key), nightMode);
        }
        if (TextUtils.equals(key, getString(R.string.change_background_color_key))) {
            Dialog dialog = new ChangeBackgroundDialog(getActivity(), "选择背景", ThemeUtil.backGroundColors);
            dialog.show();
        }

        if (TextUtils.equals(key, getString(R.string.change_theme_key))) {
            Dialog dialog = new ChangeThemeDialog(getActivity(), "选择颜色", ThemeUtil.themeColors);
            dialog.show();
        }

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBackground(ThemeUtil.getCurrentBackgroundColor(getActivity()));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        preferenceUtil = PreferenceUtil.getInstance(getActivity());
    }


    class ChangeThemeDialog extends Dialog {
        private String title;
        private Integer[] colorList;

        public ChangeThemeDialog(Context context, String title, Integer[] colorList) {
            super(context);
            this.title = title;
            this.colorList = colorList;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_change_theme);
            setTitle(title);
            GridView gridView = (GridView) findViewById(R.id.change_theme_dialog);
            gridView.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return colorList.length;
                }

                @Override
                public Object getItem(int position) {
                    return position;
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    TextView textView;
                    if (convertView == null) {
                        textView = new TextView(getActivity());
                        textView.setHeight(150);
                        textView.setPadding(0, 0, 0, 0);
                        textView.setBackgroundColor(getActivity().getResources().getColor(colorList[position]));
                        return textView;
                    } else {
                        convertView.setBackgroundColor(getActivity().getResources().getColor(colorList[position]));
                    }
                    return convertView;
                }
            });
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ChangeThemeDialog.this.dismiss();
                    int value = ThemeUtil.getCurrentTheme(getActivity());
                    if (value != position) {
                        preferenceUtil.saveIntParam(getString(R.string.change_theme_key), position);
                        THEME_COLOR_CHANGED = true;

                        Intent intent = new Intent(getActivity(), getActivity().getClass());
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
            });

        }
    }

    class ChangeBackgroundDialog extends Dialog {
        private String title;
        private Integer[] colorList;

        public ChangeBackgroundDialog(Context context, String title, Integer[] colorList) {
            super(context);
            this.title = title;
            this.colorList = colorList;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dialog_change_theme);
            setTitle(title);
            GridView gridView = (GridView) findViewById(R.id.change_theme_dialog);
            gridView.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return colorList.length;
                }

                @Override
                public Object getItem(int position) {
                    return position;
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    TextView textView;
                    if (convertView == null) {
                        textView = new TextView(getActivity());
                        textView.setHeight(150);
                        textView.setPadding(0, 0, 0, 0);
                        textView.setBackgroundColor(getActivity().getResources().getColor(colorList[position]));
                        return textView;
                    } else {
                        convertView.setBackgroundColor(getActivity().getResources().getColor(colorList[position]));
                    }
                    return convertView;
                }
            });
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ChangeBackgroundDialog.this.dismiss();
                    int value = ThemeUtil.getCurrentBackgroundColor(getActivity());
                    preferenceUtil.saveIntParam(getString(R.string.change_background_color_key), position);
                    System.out.println("背景颜色:" + ThemeUtil.getCurrentBackgroundColor(getActivity()));
                    BACKGROUND_COLOR_CHANGED = true;
                    initBackground(position);
                }
            });

        }
    }

    public void initBackground(int position){
        getView().setBackgroundColor(getActivity().getResources().getColor(
                ThemeUtil.getCurrentBackgroundColorResourceId(getActivity(), position)
        ));
    }
}
