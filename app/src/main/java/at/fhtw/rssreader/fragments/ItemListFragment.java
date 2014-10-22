package at.fhtw.rssreader.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.fhtw.rssreader.R;
import at.fhtw.rssreader.RssItemAdapter;
import at.fhtw.rssreader.dao.RssFeed;
import at.fhtw.rssreader.dao.RssItem;


/**
 * Created by Daniel on 16.10.2014.
 */
public class ItemListFragment extends Fragment {

    private View rootView;
    private ListView itemList;

    private List<RssItem> allItems = new ArrayList<RssItem>();
    private RssItemAdapter adapter;
    public ItemListFragment() {
        adapter = new RssItemAdapter(getActivity(), allItems);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //rootView = inflater.inflate(R.layout.fragment_itemlist, container, false);
        rootView = inflater.inflate(R.layout.fragment_itemlist, container, false);
        itemList = (ListView) rootView.findViewById(R.id.itemList);

        adapter = new RssItemAdapter(getActivity(), allItems);
        itemList.setAdapter(adapter);

        // rssItems.add(testItem);
        //adapter.notifyDataSetChanged();
        return rootView;
    }

    public void addItems(RssFeed feed){
        Log.v("Rss Reader", "Adding Items to List");

       // allItems.addAll((ArrayList<RssItem>)feed.getRssItems());

        adapter.notifyDataSetChanged();
    }
}
