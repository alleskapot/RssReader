package at.fhtw.rssreader.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import at.fhtw.rssreader.R;
import at.fhtw.rssreader.RssFeedService;
import saxrssreader.RssFeedModel;

public class SubscribeFragment extends Fragment  implements View.OnClickListener{

    Button Sub;
    EditText link;
    ListView list;

    View rootView;
    public SubscribeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_subscribe, container, false);
        //Set OnClick for subscribebutton
        Sub = (Button) rootView.findViewById(R.id.subscribebutton);
        Sub.setOnClickListener(this);

        link = (EditText) rootView.findViewById(R.id.linkInput);

        list = (ListView) rootView.findViewById(R.id.rssListView);

        return rootView;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.subscribebutton:
                String probe;
                //Call RssFeedService
                Sub.setText("Service starting");
                startmyService(link.getText().toString());

                break;
        }
    }

    private void startmyService(String link) {
        Log.v("myApp", "Starting Service");
        Intent intent = new Intent(getActivity(), RssFeedService.class);

        intent.putExtra("feedreiceiver", feedinfoReceiver);
        intent.putExtra("url", link);

        Sub.setText("Started");
        getActivity().startService(intent);
    }

    private final ResultReceiver feedinfoReceiver = new ResultReceiver(new Handler()) {
        @SuppressWarnings("unchecked")
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            Log.v("myApp", "Receiving Result");
            RssFeedModel feed = (RssFeedModel) resultData.getParcelable("feed");

           if (feed != null) {
               FeedListFragment listFragment = new FeedListFragment();
               getFragmentManager().beginTransaction().replace(R.id.container, listFragment).addToBackStack(null).commit();

               //listFragment.setArguments(resultData);
               //listFragment.addToList(feed);
               /*RssFeedAdapter adapter = new RssFeedAdapter(getActivity(), feed.getRssItems());
               list.setAdapter(adapter);*/
           } else {
               Toast.makeText(getActivity(), "An error occured while downloading the rss feed.",
                       Toast.LENGTH_LONG).show();
           }
        };
    };
}