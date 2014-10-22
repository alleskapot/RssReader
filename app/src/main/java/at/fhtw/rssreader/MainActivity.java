package at.fhtw.rssreader;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.database.ContentObserver;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import at.fhtw.rssreader.dao.DaoMaster;
import at.fhtw.rssreader.dao.DaoSession;
import at.fhtw.rssreader.fragments.FeedListFragment;
import at.fhtw.rssreader.fragments.SubscribeFragment;

public class MainActivity extends Activity {

    private static DaoSession _daoSession;
    private static DaoMaster daoMaster;
    private static DaoMaster.DevOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new SubscribeFragment())
                    .commit();

            helper = new DaoMaster.DevOpenHelper(this, "rssdb", null);
        }
    }

    public static DaoSession getDaoSession(){
        try {
            if (_daoSession == null) {

                SQLiteDatabase db = helper.getWritableDatabase();
                daoMaster = new DaoMaster(db);
                _daoSession = daoMaster.newSession();

                Log.i("Rss Reader","DaoSession created.");
            }
            return _daoSession;
        }catch (Exception e){
            Log.e("Rss Reader","Database error: "+e.getMessage());
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            // app icon in action bar clicked; goto parent activity.
            case android.R.id.home:
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    transaction.replace(R.id.container, new FeedListFragment());

                    transaction.add(R.id.container, new FeedListFragment());

                transaction.commit();
                ActionBar actionBar = getActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                return true;
            case R.id.add:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new SubscribeFragment())
                        .addToBackStack(null)
                        .commit();
                actionBar = getActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private class RssFeedContentObserver extends ContentObserver {
        public RssFeedContentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.container, new FeedListFragment());

                transaction.add(R.id.container, new FeedListFragment());

            transaction.commit();
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    private class RssItemContentObserver extends ContentObserver {
        public RssItemContentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {


            //loadRssItemListFragment(lastSelectedFeedId);
        }
    }



}
