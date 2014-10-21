package at.fhtw.rssreader;

/**
 * Created by Marco on 17.10.2014.
 */

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import at.fhtw.rssreader.dao.DaoSession;
import at.fhtw.rssreader.dao.RssFeed;
import at.fhtw.rssreader.dao.RssReader;


public class RssFeedService extends IntentService {


    public RssFeedService() {
        super("RssFeedService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v("myApp", "Handling Service");
        RssFeed feed = null;

        String link = intent.getStringExtra("url");
        //String link = "http://derstandard.at/?page=rss&ressort=seite1";
        try {
            URL url = new URL(link);
            feed = RssReader.read(url);
            //Bugfix
            feed.setLink(link);

            DaoSession daoSession = MainActivity.getDaoSession();

            //daoSession.getRssFeedDao().insert(feed);

            ArrayList<RssFeed> list = (ArrayList<RssFeed>)daoSession.getRssFeedDao().loadAll();

            //Logging the Feed
            Log.v("Rss Reader", "---------------------------------------");
            Log.v("Rss Reader","Link: "+feed.getLink());
            Log.v("Rss Reader","Title: "+feed.getTitle());
            Log.v("Rss Reader","Description: "+feed.getDescription());
            Log.v("Rss Reader","---------------------------------------");

            if (list.contains(feed)) {
                Log.v("Rss Reader", "Element already exists");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    Log.i("Rss Reader", "IN DB: " + list.get(i).getTitle());
                }

                Bundle bundle = new Bundle();
                bundle.putParcelable("feed", (Parcelable) feed);
                ResultReceiver feedinforeceiver = intent.getParcelableExtra("feedreiceiver");
                feedinforeceiver.send(0, bundle);
            }



            /*ArrayList<RssItem> rssItems = feed.getRssItems();
            for (RssItem rssItem : rssItems) {
                Log.v("RSS Reader", rssItem.getTitle());
            }*/
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

