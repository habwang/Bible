package com.sinnus.bible.bean;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by sinnus on 2015/8/4.
 */

public class Bible {

    public static final int THE_OLD_TESTAMENT_SIZE = 39;
    public static final int THE_NEW_TESTAMENT_SIZE = 27;
    public static final int BOOK_NUM = 66;
    public static ArrayList<Book> books = new ArrayList<>();
    public static int start_book_id;
    public static int start_chapter_id;
    public static final int[] CHAPTER_NUMBERS_ARRAY = {
            50, 40, 27, 36, 34, 24, 21, 4, 31, 24,
            22, 25, 29, 36, 10, 13, 10, 42, 150, 31,
            12, 8, 66, 52, 5, 48, 12, 14, 3, 9,
            1, 4, 7, 3, 3, 3, 2, 14, 4,
            28, 16, 24, 21, 28, 16, 16, 13, 6, 6,
            4, 4, 5, 3, 6, 4, 3, 1,
            13, 5, 5, 3, 5, 1, 1, 1, 22};
    public static final int ALL_CHAPTER_NUM = 1189;
    public static final String BOOK_NAMES_STRING = "创世记,出埃及记,利未记,民数记,申命记,约书亚记," +
            "士师记,路得记,撒母耳记上,撒母耳记下,列王纪上,列王纪下,历代志上,历代志下,以斯拉记,尼希米记," +
            "以斯帖记,约伯记,诗篇,箴言,传道书,雅歌,以赛亚书,耶利米书,耶利米哀歌,以西结书,但以理书,何西阿书," +
            "约珥书,阿摩司书,俄巴底亚书,约拿书,弥迦书,那鸿书,哈巴谷书,西番雅书,哈该书,撒迦利亚书,玛拉基书," +
            "马太福音,马可福音,路加福音,约翰福音,使徒行传,罗马书,哥林多前书,哥林多后书,加拉太书,以弗所书," +
            "腓立比书,歌罗西书,帖撒罗尼迦前书,帖撒罗尼迦后书,提摩太前书,提摩太后书,提多书,腓利门书,希伯来书," +
            "雅各书,彼得前书,彼得后书,约翰一书,约翰二书,约翰三书,犹大书,启示录";
    public static final String BOOK_SIMPLE_NAMES_STRING = "创,出,利,民,申,书,士,得,撒上,撒下," +
            "王上,王下,代上,代下,拉,尼,斯,伯,诗,箴,传,歌,赛,耶,哀,结,但,何,珥,摩,俄,拿,弥,鸿,哈,番" +
            ",该,亚,玛,太,可,路,约,徒,罗,林前,林后,加,弗,腓,西,帖前,帖后,提前,提后,多,门,来,雅,彼前," +
            "彼后,约一,约二,约三,犹,启";
    public static String[] BOOK_NAMES = BOOK_NAMES_STRING.split(",");
    public static String[] BOOK_SIMPLE_NAMES = BOOK_SIMPLE_NAMES_STRING.split(",");

    public static String[] BOOK_SIMPLE_NAMES_OLD = "创,出,利,民,申,书,士,得,撒上,撒下,王上,王下,代上,代下,拉,尼,斯,伯,诗,箴,传,歌,赛,耶,哀,结,但,何,珥,摩,俄,拿,弥,鸿,哈,番,该,亚,玛".split(",");

    public static String[] BOOK_SIMPLE_NAMES_NEW = "太,可,路,约,徒,罗,林前,林后,加,弗,腓,西,帖前,帖后,提前,提后,多,门,来,雅,彼前,彼后,约一,约二,约三,犹,启".split(",");

    public static String getBookName(int id) {
        return BOOK_NAMES[id - 1];
    }

    public static String getBookSimpleName(int id) {
        return BOOK_SIMPLE_NAMES[id - 1];
    }

    public static int getChapterNum(int id) {
        return CHAPTER_NUMBERS_ARRAY[id - 1];
    }

    public static ArrayList<Book> load(Context context) {
        for (int i = 1; i <= 66; i++) {
            books.add(new Book(i, context));
        }
        return books;
    }

    public static int getBookIdBySimpleName(String name) {
        for (int i = 0; i < 66; i++) {
            if (BOOK_SIMPLE_NAMES[i].equals(name))
                return i + 1;
        }
        return 0;
    }

    public static int getBookIdByName(String name) {
        for (int i = 0; i < 66; i++) {
            if (BOOK_NAMES[i].equals(name))
                return i + 1;
        }
        return -1;
    }

    public static void setStart(int start_book_id, int start_chapter_id) {
        Bible.start_book_id = start_book_id;
        Bible.start_chapter_id = start_chapter_id;
    }

    public static int[] getRelativeInfoById(int mainFragmentId) {
        int sum = mainFragmentId + 1;
        int i = 0;
        for (; i < Bible.BOOK_NUM; i++) {
            if (sum <= CHAPTER_NUMBERS_ARRAY[i]) {
                break;
            }
            sum -= CHAPTER_NUMBERS_ARRAY[i];
        }
        return new int[]{i + 1, sum};
    }

    public static int getMainFragmentIdByInfo(int bookId, int chapterId) {
        int sum = 0;
        for (int i = 0; i < bookId-1; i++) {
            sum += CHAPTER_NUMBERS_ARRAY[i];
        }
        sum += chapterId;
        return sum - 1;
    }
}