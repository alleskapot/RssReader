package at.fhtw.rssreader;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
                    .add(R.id.subscribe_fragment, new SubscribeFragment())
                    .add(R.id.container_fragment, new FeedListFragment())
                    .commit();

            helper = new DaoMaster.DevOpenHelper(this, "rssdb", null);
        }
    }

    protected static DaoSession getDaoSession(){
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
