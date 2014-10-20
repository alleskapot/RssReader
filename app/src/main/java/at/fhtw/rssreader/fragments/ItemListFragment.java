package at.fhtw.rssreader.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import at.fhtw.rssreader.R;
import at.fhtw.rssreader.RssItemAdapter;
import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;

/**
 * Created by Daniel on 16.10.2014.
 */
public class ItemListFragment extends Fragment {

    private View rootView;
    private ListView itemList;

    private List<RssItem> allItems = new ArrayList<RssItem>();
    private RssItemAdapter adapter;
    public ItemListFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //rootView = inflater.inflate(R.layout.fragment_itemlist, container, false);
        rootView = inflater.inflate(R.layout.fragment_itemlist, container, false);
        itemList = (ListView) rootView.findViewById(R.id.itemList);
        adapter = new RssItemAdapter(getActivity(), allItems);



        adapter = new RssItemAdapter(getActivity(), allItems);
        itemList.setAdapter(adapter);

     // rssItems.add(testItem);
        adapter.notifyDataSetChanged();
        return rootView;
    }

    public void addItems(RssFeed feed){
        Log.v("Rss Reader", "Adding Items to List");
        List<RssItem> myitems = new ArrayList<RssItem>();
        myitems.addAll((ArrayList<RssItem>)feed.getRssItems());

        /*RssItem testItem = new RssItem();

        testItem.setTitle("TESTTITEL");
        testItem.setDescription("ENDLICH!!");
        allItems.add(testItem);*/
        //adapter.notifyDataSetChanged();
        Iterator<RssItem> it = myitems.iterator();
        while(it.hasNext())
        {
            RssItem rs = it.next();
            //Do something with obj
            Log.v("Rss Reader", rs.getTitle().toString());


            allItems.add(rs);
        }
       //rssItems.clear();
       //rssItems.addAll(items.getRssItems());

        //adapter.notifyDataSetChanged();
    }
}
