package at.fhtw.rssreader;

/**
 * Created by Marco on 17.10.2014.
 */

import android.app.IntentService;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.fhtw.rssreader.dao.DaoSession;
import at.fhtw.rssreader.dao.RssFeed;
import at.fhtw.rssreader.dao.RssFeedContentProvider;
import at.fhtw.rssreader.dao.RssFeedDao;
import at.fhtw.rssreader.dao.RssItemContentProvider;
import at.fhtw.rssreader.dao.RssItemDao;
import saxrssreader.RssFeedModel;
import saxrssreader.RssItemModel;
import saxrssreader.RssReader;


public class RssFeedService extends IntentService {


    public RssFeedService() {
        super("RssFeedService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v("myApp", "Handling Service");
        RssFeedModel feed = new RssFeedModel();

        String link = intent.getStringExtra("url");
        //String link = "http://derstandard.at/?page=rss&ressort=seite1";
        try {
            URL url = new URL(link);
            feed = RssReader.read(url);
            //Bugfix
            feed.setLink(link);



            DaoSession daoSession = MainActivity.getDaoSession();

            long feedId = createRssFeedEntry(feed, intent.getStringExtra(feed.getTitle()));
            createRssItemEntries(feedId, feed.getRssItems());

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

    // Convert RssFeedModel to ContentValues object and insert it in the database.
    private long createRssFeedEntry(RssFeedModel rssFeedModel, String title) {
        title = TextUtils.isEmpty(title) ? rssFeedModel.getTitle() : title;

        ContentValues values = new ContentValues();
        values.put(RssFeedDao.Properties.Title.columnName, title);
        values.put(RssFeedDao.Properties.Link.columnName, rssFeedModel.getLink());

        Log.v("Rss Reader", "URI: "+RssFeedContentProvider.CONTENT_URI);

        // Insert rss feed.
        Uri uri = getContentResolver().insert(RssFeedContentProvider.CONTENT_URI, values);

        Log.v("Rss Reader","Feed inserted");

        // Return new record id.
        return ContentUris.parseId(uri);
    }

    private int createRssItemEntries(long feedId, ArrayList<RssItemModel> rssItemModels) {
        List<ContentValues> list = convertRssItems(feedId, rssItemModels);

        // Convert list to array of ContentValues.
        ContentValues[] values = new ContentValues[list.size()];
        values = list.toArray(values);

        // Bulk insert all feed items.
        return getContentResolver().bulkInsert(RssItemContentProvider.CONTENT_URI, values);
    }

    private List<ContentValues> convertRssItems(long feedId, List<RssItemModel> rssItemModels) {
        List<ContentValues> list = new ArrayList<ContentValues>(rssItemModels.size());

        // Map RssItemModels to RssItems
        for (RssItemModel rssItemModel : rssItemModels) {
            ContentValues values = new ContentValues();
            values.put(RssItemDao.Properties.Title.columnName, rssItemModel.getTitle());
            values.put(RssItemDao.Properties.Link.columnName, rssItemModel.getLink());
            values.put(RssItemDao.Properties.Description.columnName, rssItemModel.getDescription());
            values.put(RssItemDao.Properties.PubDate.columnName, parseDate(rssItemModel.getPubDate()));
            values.put(RssItemDao.Properties.Id.columnName, feedId);

            list.add(values);
        }

        return list;
    }

    // Convert Date to String
    private String parseDate(Date date) {
        if (date == null) {
            date = new Date();
        }

        return new SimpleDateFormat().format(date);
    }


}

