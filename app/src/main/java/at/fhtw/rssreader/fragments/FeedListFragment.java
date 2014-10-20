package at.fhtw.rssreader.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.fhtw.rssreader.R;
import at.fhtw.rssreader.RssFeedAdapter;
import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;

//import at.fhtw.rssreader.RssItemService;

public class FeedListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private View rootView;

    private List<RssFeed> rssFeeds;
    private  RssFeedAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_rsslist, container, false);
        listView = (ListView) rootView.findViewById(R.id.rssListView);
        listView.setOnItemClickListener(this);

        rssFeeds = new ArrayList<RssFeed>();
        adapter = new RssFeedAdapter(getActivity(), rssFeeds);
        listView.setAdapter(adapter);

        return rootView;
    }

    private final ResultReceiver resultReceiver = new ResultReceiver(new Handler()) {
        @SuppressWarnings("unchecked")
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            /*List<RssItem> items = (List<RssItem>) resultData.getParcelable("items");
            if (items != null) {
                RssFeedAdapter adapter = new RssFeedAdapter(getActivity(), items);
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "An error occured while downloading the rss feed.",
                        Toast.LENGTH_LONG).show();
            }*/
        };
    };

    public void addToList(RssFeed rssFeed){
        //Log.v("Rss Reader","FEED RECIEVED in RSSListFragment");
        rssFeeds.add(rssFeed);
        adapter.notifyDataSetChanged();
    }
    /*private void startService() {
        Intent intent = new Intent(getActivity(), RssItemService.class);
        intent.putExtra("itemreceiver", resultReceiver);
        getActivity().startService(intent);
    }*/



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RssFeed feed = (RssFeed) adapter.getItem(position);
        Log.v("Rss Reader", "Feed click gecaptured!");
        Log.v("Rss Reader", feed.getTitle().toString());
        RssItem test = feed.getRssItems().get(0);

        ItemListFragment listFragment = new ItemListFragment();
        getFragmentManager().beginTransaction().replace(R.id.container_fragment, listFragment).addToBackStack(null).commit();

        Log.v("Rss Reader", "Changed Fragment");

        //listFragment.setArguments(resultData);
        listFragment.addItems(feed);
        /*Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);*/
    }


}