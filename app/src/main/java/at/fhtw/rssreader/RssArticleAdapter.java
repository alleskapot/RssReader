package at.fhtw.rssreader;

/**
 * Created by Marco on 17.10.2014.
 */

import android.widget.BaseAdapter;

    import java.util.List;
    import android.content.Context;
    import android.view.View;
    import android.view.ViewGroup;
import android.widget.TextView;

import at.fhtw.rssreader.dataobjects.RssItem;

public class RssArticleAdapter extends BaseAdapter {

        private final List<RssItem> items;
        private final Context context;

        public RssArticleAdapter(Context context, List<RssItem> items) {
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
                convertView = View.inflate(context, R.layout.fragment_rssitemlist, null);
                holder = new ViewHolder();
                holder.itemTitle = (TextView) convertView.findViewById(R.id.itemTitle);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.itemTitle.setText(items.get(position).getTitle());


            return convertView;
        }

        static class ViewHolder {
            TextView itemTitle;
        }
    }

