package com.sinnus.bible.bean;

/**
 * Created by sinnus on 2015/8/28.
 */
public class Note {
    private int bookId;
    private int chapterId;
    private int id;
    private String title;
    private String content;

    public Note(String title,String content,int bookId,int chapterId,int id){
        this.bookId = bookId;
        this.chapterId = chapterId;
        this.id = id;
        this.title = title;
        this.content = content;
    }
    public int getBookId(){
        return bookId;
    }
    public int getChapterId(){
        return chapterId;
    }
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getContent(){
        return content;
    }

}
