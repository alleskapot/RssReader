package at.fhtw.rssreader;

/**
 * Created by Marco on 17.10.2014.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import at.fhtw.rssreader.dao.RssFeed;

public class RssFeedAdapter extends BaseAdapter {

        private final List<RssFeed> items;
        private final Context context;

        public RssFeedAdapter(Context context, List<RssFeed> items) {
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
                convertView = View.inflate(context, R.layout.rssitem, null);
                holder = new ViewHolder();
                holder.itemTitle = (TextView) convertView.findViewById(R.id.itemTitle);
                holder.itemDescription = (TextView) convertView.findViewById(R.id.itemDescription);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.itemTitle.setText(items.get(position).getTitle());
           holder.itemDescription.setText(items.get(position).getDescription());


            return convertView;
        }

        static class ViewHolder {
            TextView itemTitle;
            TextView itemDescription;
        }
    }

