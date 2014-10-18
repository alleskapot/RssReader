package at.fhtw.rssreader.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import at.fhtw.rssreader.R;

/**
 * Created by Daniel on 16.10.2014.
 */
public class ArticleListFragment extends Fragment {

    public ArticleListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_articlelist, container, false);
        return rootView;
    }
}
