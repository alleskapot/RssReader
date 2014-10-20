package at.fhtw.rssreader;

/**
 * Created by Marco on 17.10.2014.
 */

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import at.fhtw.rssreader.dataobjects.RssFeed;
import at.fhtw.rssreader.dataobjects.RssItem;


public class RssFeedService extends IntentService {



    public RssFeedService() {
        super("RssFeedService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v("myApp", "Handling Service");
        List<RssFeed> myfeed = new ArrayList();

        //String link = intent.getStringExtra("url");
String link = "http://derstandard.at/?page=rss&ressort=seite1";
      /*onefeed.url = intent.getStringExtra("url");

        onefeed.description = "dies ist ein test";
        onefeed.title ="wut";
        myfeed.add(onefeed);
        Bundle bundle = new Bundle();
        bundle.putSerializable("feeds", (Serializable)myfeed);
        ResultReceiver feedinforeceiver = intent.getParcelableExtra("feedreiceiver");
        feedinforeceiver.send(0, bundle);*/



        try {
            URL url = new URL(link);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(getInputStream(url), "UTF_8");


            boolean isChannel = false;
            int count = 0;
            RssFeed onefeed = new RssFeed();
            int eventT = xpp.getEventType();
            while (eventT != XmlPullParser.END_DOCUMENT) {
                count++;
                if (eventT == XmlPullParser.START_TAG) {
                    /*if(xpp.getName().equalsIgnoreCase("item"))
                        break;*/

                    if (xpp.getName().equalsIgnoreCase("channel")) {
                        isChannel = true;
                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (isChannel)
                            onefeed.title = xpp.nextText(); //extract the headline
                    } else if (xpp.getName().equalsIgnoreCase("link")) {
                        if (isChannel)
                            onefeed.url = xpp.nextText();
                    } else if (xpp.getName().equalsIgnoreCase("description")) {
                        if (isChannel)
                            onefeed.description = xpp.nextText();
                    }
                }else if(eventT==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("channel")){
                    isChannel=false;

                }
                }
                Log.v("myApp", "Durchäufe: " + count);
                Log.v("myApp", "Titel: " + onefeed.title);

                Log.v("myApp", "Link: " + onefeed.url);

                Log.v("myApp", "Description: " + onefeed.description);

                eventT = xpp.next(); //move to next element
            } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }




            /*boolean insideItem = false;

            // Returns the type of current event: START_TAG, END_TAG, etc..
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {

                    if (xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = true;
                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (insideItem)
                            headlines.add(xpp.nextText()); //extract the headline
                    } else if (xpp.getName().equalsIgnoreCase("link")) {
                        if (insideItem)
                            links.add(xpp.nextText()); //extract the link of article
                    }
                }else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
                    insideItem=false;
                }

                eventType = xpp.next(); //move to next element
            }*/


        //End Try

       /*Binding data
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, headlines);

        setListAdapter(adapter);*/


    }
    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

   }

