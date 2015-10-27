package com.sinnus.bible.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sinnus.bible.R;
import com.sinnus.bible.bean.Chapter;

/**
 * Created by sinnus on 2015/8/30.
 */
public class NoteListViewAdapter extends BaseAdapter{
    private Chapter chapter;
    private LayoutInflater inflater;
    public NoteListViewAdapter(Context context, Chapter chapter){
        inflater = LayoutInflater.from(context);
        this.chapter = chapter;
    }
    @Override
    public int getCount(){
        return chapter.getNoteNum();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.note_list_view_item,null,false);
            holder.title = (TextView)convertView.findViewById(R.id.note_title);
            holder.content = (TextView)convertView.findViewById(R.id.note_content);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.title.setText(chapter.getNote(position).getTitle());
        holder.content.setText(chapter.getNote(position).getContent());
        return convertView;
    }

    class ViewHolder{
        public TextView title;
        public TextView content;
    }

}
