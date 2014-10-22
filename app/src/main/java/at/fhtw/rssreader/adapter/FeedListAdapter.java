package at.fhtw.rssreader.adapter;

/**
 * Created by Marco on 22.10.2014.
 */

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import at.fhtw.rssreader.R;
import at.fhtw.rssreader.dao.RssFeedDao;


public class FeedListAdapter extends CursorAdapter {
    public FeedListAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);

        return inflater.inflate(R.layout.rssitem, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewFeedTitle = (TextView) view.findViewById(R.id.itemTitle);
        textViewFeedTitle.setText(
                cursor.getString(
                        cursor.getColumnIndex(
                                RssFeedDao.Properties.Title.columnName)));


    }
}

