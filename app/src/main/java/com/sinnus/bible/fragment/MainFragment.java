package com.sinnus.bible.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sinnus.bible.R;
import com.sinnus.bible.adapter.ListViewAdapter;
import com.sinnus.bible.adapter.NoteListViewAdapter;
import com.sinnus.bible.bean.Bible;
import com.sinnus.bible.bean.Chapter;


public class MainFragment extends Fragment {
    private int id;
    private OnFragmentInteractionListener mListener;
    public ListView mListView;
    public boolean isNote = false;
    public ListViewAdapter listViewAdapter;
    public NoteListViewAdapter noteListViewAdapter;

    private Chapter chapter;

    public static MainFragment newInstance(int id) {
        MainFragment fragment = new MainFragment();
        fragment.id = id;
        return fragment;
    }

    public MainFragment() {
        System.out.println("MainFragment");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate :" + id);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("onCreateView :" + id);

        if (mListView == null) {
            mListView = (ListView) inflater.inflate(R.layout.fragment_main, container, false);
        }
        setChapterById();
        listViewAdapter = new ListViewAdapter(getActivity(), this.chapter);
        noteListViewAdapter = new NoteListViewAdapter(getActivity(), this.chapter);
        mListView.setAdapter(listViewAdapter);
        return mListView;
    }

    public void changeMode() {
        isNote = !isNote;
        if (isNote) {
            this.mListView.setAdapter(this.noteListViewAdapter);
        }
        else {
            this.mListView.setAdapter(this.listViewAdapter);
        }
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        System.out.println("onAttach :" + id);

    }

    @Override
    public void onDetach() {
        System.out.println("onDetach :" + id);

        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public Chapter onFragmentInteraction(int bookId,int chapterId);
    }
    public void setChapterById() {
        int bookId = Bible.getRelativeInfoById(id)[0];
        int chapterId = Bible.getRelativeInfoById(id)[1];
        this.chapter = mListener.onFragmentInteraction(bookId, chapterId);
    }

}
