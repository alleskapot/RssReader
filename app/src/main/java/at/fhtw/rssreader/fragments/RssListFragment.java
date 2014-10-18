package at.fhtw.rssreader.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import at.fhtw.rssreader.R;
import at.fhtw.rssreader.RssArticleAdapter;
import at.fhtw.rssreader.RssItemService;
import at.fhtw.rssreader.dataobjects.RssItem;

public class RssListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView listView;
    private View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_rssitemlist, container, false);
        listView = (ListView) rootView.findViewById(R.id.rssListView);
        listView.setOnItemClickListener(this);




        return rootView;
    }

    private final ResultReceiver resultReceiver = new ResultReceiver(new Handler()) {
        @SuppressWarnings("unchecked")
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            List<RssItem> items = (List<RssItem>) resultData.getSerializable(RssItemService.ITEMS);
            if (items != null) {
                RssArticleAdapter adapter = new RssArticleAdapter(getActivity(), items);
                listView.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "An error occured while downloading the rss feed.",
                        Toast.LENGTH_LONG).show();
            }
        };
    };

    private void startService() {
        Intent intent = new Intent(getActivity(), RssItemService.class);
        intent.putExtra(RssItemService.RECEIVER, resultReceiver);
        getActivity().startService(intent);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RssArticleAdapter adapter = (RssArticleAdapter) parent.getAdapter();
        RssItem item = (RssItem) adapter.getItem(position);
        Uri uri = Uri.parse(item.getLink());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


}