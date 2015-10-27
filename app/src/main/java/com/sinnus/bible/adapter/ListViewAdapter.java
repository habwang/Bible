package com.sinnus.bible.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sinnus.bible.R;
import com.sinnus.bible.bean.Chapter;

public class ListViewAdapter extends BaseAdapter {
    private Chapter chapter;
    private LayoutInflater inflater;

    public ListViewAdapter(Context context, Chapter chapter) {
        inflater = LayoutInflater.from(context);
        this.chapter = chapter;
    }

    @Override
    public int getCount() {
        return chapter.getSectionNum();
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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_view_item, null, false);
            holder.id = (TextView) convertView.findViewById(R.id.section_id);
            holder.content = (TextView) convertView.findViewById(R.id.section_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.id.setText("" + (position + 1));
        holder.content.setText(chapter.getSection(position).toString());
        return convertView;
    }

    class ViewHolder {
        public TextView id;
        public TextView content;
    }
}