package com.air.aircovg.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.air.aircovg.MainActivity;
import com.air.aircovg.R;
import com.air.aircovg.adapters.NewsAdapter;
import com.air.aircovg.events.DatabaseEvents;
import com.air.aircovg.helpers.DatabaseHelper;
import com.air.aircovg.helpers.EventHelper;
import com.air.aircovg.model.News;

import java.util.ArrayList;

/**
 * Created by ayush AS on 25/12/16.
 */

public class PinnedNewsFragment extends Fragment implements DatabaseEvents {

    NewsAdapter newsAdapter;
    ArrayList<News> mNews;
    DatabaseHelper databaseHelper;

    TextView mTextView;
    ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pinned_news_layout, container, false);
        EventHelper.initDatabaseEvent(this);
        mListView = (ListView) rootView.findViewById(R.id.listView);
        mTextView = (TextView) rootView.findViewById(R.id.emptyMessage);
        mListView.setDividerHeight(0);
        return rootView;
    }

    public void loadAdapter(){
        mNews = new ArrayList<>();
        newsAdapter = new NewsAdapter(getContext(), mNews, true);
        mListView.setAdapter(newsAdapter);
        updateMessage();
    }

    private void updateMessage(){
        if(mNews.size() > 0)
            mTextView.setVisibility(View.GONE);
        else
            mTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        databaseHelper = DatabaseHelper.getDatabaseHelper(getContext());
        loadAdapter();
        for(News n : databaseHelper.getAllNews()){
            mNews.add(n);
        }
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)getActivity()).showNews(newsAdapter.getItem(position), true);
            }
        });
    }


    @Override
    public void addOrRemoveNews(News news, boolean isAdded) {
        if(!isAdded){
            for(int i = 0; i < mNews.size(); i++){
                if(mNews.get(i).getmUrl().equalsIgnoreCase(news.getmUrl())){
                    mNews.remove(i);
                    break;
                }
            }
        }else {
            mNews.add(news);
        }
        newsAdapter.notifyDataSetChanged();
        updateMessage();
    }
}
