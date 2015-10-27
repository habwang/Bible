package com.sinnus.bible.bean;

import android.content.Context;

import com.sinnus.bible.util.DatabaseUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Book {
    private int id;
    private int chapterNum;
    private ArrayList<Chapter> content;
    private String temp;

    public Book(int id, Context context) {
        this.id = id;
        this.chapterNum = Bible.getChapterNum(this.id);
        this.content = new ArrayList<Chapter>();
        this.loadBookFromFile(context);
        this.loadNotesFromFile(context);
    }

    private void load(Context context) {
        DatabaseUtil dbUtil = new DatabaseUtil(context);
        dbUtil.queryBook(this);
    }
    private void loadBookFromFile(Context context) {
        String fileName = id > 9 ? "" + id : "0" + id;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName), "utf-8"));

            bufferedReader.readLine();
            bufferedReader.readLine();

            int chapter_id = 1;
            int section_id = 0;
            Chapter chapter = new Chapter(chapter_id, this.id);

            while (true) {
                String temp;
                temp = bufferedReader.readLine();
                if (temp == null) break;
                if (temp.length() == 3 && (temp.charAt(0) == '0' || temp.charAt(0) == '1')) {
                    this.content.add(chapter);
                    ++chapter_id;
                    chapter = new Chapter(chapter_id, this.id);
                    section_id = 1;
                } else {
                    section_id++;
                    chapter.addSection(new Section(section_id, temp, chapter_id, this.id));
                }
            }
            this.content.add(chapter);
            System.out.println("loading book "+id+"done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadNotesFromFile(Context context) {
        String fileName = id > 9 ? "lx" + id : "lx0" + id;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName), "utf-8"));
            int count = 0;
            Chapter chapter = this.getChapter(count);
            int note_id = 0;
            while (true) {
                String temp = bufferedReader.readLine();
                //System.out.println(temp);
                if (temp.equals("#####")) {
                    count++;
                    if(count == chapterNum)break;
                    chapter = getChapter(count);
                    note_id = 1;
                } else {
                    note_id++;
                    //System.out.println(temp);
                    String [] strs = temp.split("###");
                    if(strs.length == 2){
                        chapter.addNote(new Note(strs[0], strs[1], this.id, count+1, note_id));
                    }
                    else if(strs.length == 1) {
                        chapter.addNote(new Note(strs[0], "", this.id, count+1, note_id));
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Chapter> getContent() {
        return content;
    }

    public Chapter getChapter(int id) {
        return getContent().get(id);
    }

    public void addChapter(Chapter chapter) {
        this.content.add(chapter);
    }

    public int getId() {
        return this.id;
    }

    public int getChapterNum() {
        return chapterNum;
    }

    public String getName() {
        return Bible.getBookName(id);
    }

    public String getSimpleName() {
        return Bible.getBookSimpleName(id);
    }
}