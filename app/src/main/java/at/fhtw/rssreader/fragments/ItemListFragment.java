package at.fhtw.rssreader.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.fhtw.rssreader.R;
import at.fhtw.rssreader.RssItemAdapter;
import nl.matshofman.saxrssreader.RssItem;

/**
 * Created by Daniel on 16.10.2014.
 */
public class ItemListFragment extends Fragment {

    private View rootView;
    private ListView itemList;

    private List<RssItem> rssItems;
    private RssItemAdapter adapter;

    public ItemListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_itemlist, container, false);
        itemList = (ListView) rootView.findViewById(R.id.itemList);

        rssItems = new ArrayList<RssItem>();

        adapter = new RssItemAdapter(getActivity(), rssItems);
        itemList.setAdapter(adapter);

        return rootView;
    }

    public void addItems(ArrayList<RssItem> items){
        rssItems.clear();
        rssItems.addAll(items);

        adapter.notifyDataSetChanged();
    }
}
