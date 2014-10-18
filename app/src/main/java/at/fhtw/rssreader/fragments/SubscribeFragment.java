package at.fhtw.rssreader.fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import at.fhtw.rssreader.R;

public class SubscribeFragment extends Fragment implements View.OnClickListener {
    Button b;
    public SubscribeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        b = (Button) rootView.findViewById(R.id.subscribebutton);
        b.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.subscribebutton:

               //Call RssArticleParser
               b.setText("Switch");

                break;
        }
    }
}