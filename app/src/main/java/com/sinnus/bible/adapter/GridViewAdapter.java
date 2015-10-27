package com.sinnus.bible.adapter;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sinnus.bible.R;
import com.sinnus.bible.bean.Bible;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private String[] content;

    private GridViewAdapter(Context context, String[] content) {
        this.context = context;
        this.content = content;
    }

    private static GridViewAdapter oldAdapter;
    private static GridViewAdapter newAdapter;

    public static GridViewAdapter getOldAdapter(Context context) {//返回旧约书名的适配器
        if (oldAdapter == null) {
            oldAdapter = new GridViewAdapter(context, Bible.BOOK_SIMPLE_NAMES_OLD);
        }
        return oldAdapter;
    }

    public static GridViewAdapter getNewAdapter(Context context) {//返回旧约书名的适配器
        if (newAdapter == null) {
            newAdapter = new GridViewAdapter(context, Bible.BOOK_SIMPLE_NAMES_NEW);
        }
        return newAdapter;
    }
    public static GridViewAdapter getChapterAdapter(Context context,int bookid){//返回显示章节网格的适配器
        String s = "";
        int n = Bible.getChapterNum(bookid);
        for(int i=1;i <= n; i++){
            if(i<n)
                s += (i+",");
            else
                s += i;
        }
        return new GridViewAdapter(context,s.split(","));
    }

    @Override
    public int getCount() {
        return content.length;
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
            textView = new TextView(context);
        } else {
            textView = (TextView) convertView;
        }
        textView.setText(content[position]);
        textView.setGravity(Gravity.CENTER);
        textView.setHeight(150);
        textView.setPadding(0, 0, 0, 0);
        textView.setTextSize(20);
//        textView.setTextColor(context.getResources().getColor(R.color.gray));
        return textView;
    }
}
