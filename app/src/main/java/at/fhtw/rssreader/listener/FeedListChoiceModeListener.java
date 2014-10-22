package at.fhtw.rssreader.listener;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;

import at.fhtw.rssreader.R;
import at.fhtw.rssreader.dao.RssFeedContentProvider;
import at.fhtw.rssreader.dao.RssFeedDao;

/**
 * Created by Marco on 22.10.2014.
 */
public class FeedListChoiceModeListener implements AbsListView.MultiChoiceModeListener {
    private Activity activity;
    private ListView listView;
    private Resources resources;

    public FeedListChoiceModeListener(Activity activity, ListView listView, Resources resources) {
        this.activity = activity;
        this.listView = listView;
        this.resources = resources;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
        // Set the action mode title.
        int numberOfCheckedItems = listView.getCheckedItemCount();
        String actionModeTitle = resources.getQuantityString(R.plurals.feeds,
                numberOfCheckedItems,
                numberOfCheckedItems);
        actionMode.setTitle(actionModeTitle);

        // Set background color on selected item.
        if (listView.isItemChecked(position)) {
            listView.getChildAt(position).setBackgroundColor(Color.LTGRAY);
        } else {
            listView.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        actionMode.getMenuInflater().inflate(R.menu.rssfeed_actionmode, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.delete:
                // Get a list of selected item ids.
                long[] selectedFeedIds = listView.getCheckedItemIds();
                ContentResolver cr = activity.getContentResolver();

                // Delete all selected items.
                for (long selectedFeedId : selectedFeedIds) {
                    // Execute delete statement.
                    cr.delete(RssFeedContentProvider.CONTENT_URI,
                            RssFeedDao.Properties.Id.columnName + " = ?",
                            new String[]{String.valueOf(selectedFeedId)});
                }

                actionMode.finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        listView.clearChoices();
        listView.requestLayout();
    }
}
