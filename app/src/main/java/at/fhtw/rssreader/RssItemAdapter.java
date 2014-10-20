package at.fhtw.rssreader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nl.matshofman.saxrssreader.RssItem;

/**
 * Created by Daniel on 20.10.2014.
 */
public class RssItemAdapter extends BaseAdapter {

    private final List<RssItem> items;
    private final Context context;

    public RssItemAdapter(Context context, List<RssItem> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.detaileditem, null);
            holder = new ViewHolder();
            holder.detailedItemTitle = (TextView) convertView.findViewById(R.id.itemTitle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.detailedItemTitle.setText(items.get(position).getTitle());


        return convertView;
    }

    static class ViewHolder {
        TextView detailedItemTitle;
    }
}
