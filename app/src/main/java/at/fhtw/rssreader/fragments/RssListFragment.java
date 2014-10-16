package at.fhtw.rssreader.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import at.fhtw.rssreader.R;

public class RssListFragment extends Fragment {

    public RssListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rsslist, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        Button test = (Button)getActivity().findViewById(R.id.btn_test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container_fragment, new FeedListFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

    }
}