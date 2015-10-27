package com.sinnus.bible.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinnus.bible.bean.Book;
import com.sinnus.bible.bean.Chapter;
import com.sinnus.bible.bean.Note;
import com.sinnus.bible.bean.Section;

/**
 * Created by sinnus on 2015/9/6.
 */
public class DatabaseUtil {
    private DataBaseHelper helper;
    private SQLiteDatabase database;
    private Context context;

    public DatabaseUtil(Context context) {
        this.context = context;
        helper = new DataBaseHelper(context);
        database = helper.openDatabase();
    }

    public void queryBook(Book book) {
        int bookId = book.getId();
        for (int i = 1; i <= book.getChapterNum(); i++) {
            Chapter chapter = queryChapter(bookId, i);
            book.addChapter(chapter);
        }
    }

    public Chapter queryChapter(int bookId, int chapterId) {
        String sql = "select Content,SectionId from bible_book where BookId=? and ChapterId=?";
        String sql2 = "select Title,Content,NoteId from bible_lx where BookId=? and ChapterId=?";
        Chapter chapter = new Chapter(chapterId, bookId);

        Cursor cursor = database.rawQuery(sql, new String[]{"" + bookId, "" + chapterId});
        while (cursor.moveToNext()) {
            String content = cursor.getString(0);
            int sectionId = cursor.getInt(1);
            Section section = new Section(sectionId + 1, content, chapterId, bookId);
            chapter.addSection(section);
        }
        cursor = database.rawQuery(sql2, new String[]{"" + bookId, "" + chapterId});
        while (cursor.moveToNext()) {
            String title = cursor.getString(0);
            String content = cursor.getString(1);
            int noteId = cursor.getInt(2);
            Note note = new Note(title, content, bookId, chapterId, noteId);
            chapter.addNote(note);
        }
        return chapter;
    }

    public Section querySection(int bookId, int chapterId, int sectionId) {
        String sql = "select Content from bible_book where BookId=? and ChapterId=? and SectionId=?";
        Cursor cursor = database.rawQuery(sql, new String[]{"" + bookId, "" + chapterId, "" + sectionId});
        return null;
    }

    public Note queryNote(int bookId, int chapterId) {
        String sql = "select Title,Content,NoteId from bible_lx where BookId=? and ChapterId=?";

        return null;
    }


}
