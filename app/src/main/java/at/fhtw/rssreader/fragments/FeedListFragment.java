package at.fhtw.rssreader.fragments;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;

import at.fhtw.rssreader.R;
import at.fhtw.rssreader.adapter.FeedListAdapter;
import at.fhtw.rssreader.dao.RssFeedContentProvider;
import at.fhtw.rssreader.dao.RssFeedDao;
import at.fhtw.rssreader.listener.FeedListChoiceModeListener;


//import at.fhtw.rssreader.RssItemService;

public class FeedListFragment extends ListFragment {

    private CursorAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Fetch rss feeds.
        Cursor cursor = getActivity()
                .getContentResolver()
                .query(RssFeedContentProvider.CONTENT_URI,
                        new String[]{RssFeedDao.Properties.Id.columnName,
                                RssFeedDao.Properties.Title.columnName},
                        null, null, null);
        // Set up the cursor adapter.
        adapter = new FeedListAdapter(getActivity(), cursor, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_rsslist, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.rssListView);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new FeedListChoiceModeListener(getActivity(), listView, getResources()));


        return rootView;
    }
/*
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RssFeed feed = (RssFeed) adapter.getItem(position);
        Log.v("Rss Reader", "Feed click gecaptured!");
        Log.v("Rss Reader", feed.getTitle().toString());
        //RssItem test = feed.getRssItems().get(0);

        ItemListFragment listFragment = new ItemListFragment();
        //getFragmentManager().beginTransaction().replace(R.id.container_fragment, listFragment).addToBackStack(null).commit();

        Log.v("Rss Reader", "Changed Fragment");

        //listFragment.setArguments(resultData);
        //listFragment.addItems(feed);
        /*Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }*/




}