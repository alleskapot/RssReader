package at.fhtw.rssreader;

/**
 * Created by Marco on 17.10.2014.
 */

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;




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
import java.util.List;

import at.fhtw.rssreader.dataobjects.RssFeed;
import at.fhtw.rssreader.dataobjects.RssItem;


public class RssFeedService extends IntentService {



    public RssFeedService() {
        super("RssFeedService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        RssFeed myfeed = new RssFeed();
      myfeed.url = intent.getStringExtra("url");
        myfeed.description = "dies ist ein test";
        myfeed.title ="wut";


        Bundle bundle = new Bundle();
        bundle.putSerializable("feed", (Serializable)myfeed);
        ResultReceiver feedinforeceiver = intent.getParcelableExtra("feedreiceiver");
        feedinforeceiver.send(0, bundle);
        /*
        try {
            URL url = new URL("http://www.pcworld.com/index.rss");


            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(getInputStream(url), "UTF_8");


            boolean isChannel = false;

            int eventT = xpp.getEventType();
            while (eventT != XmlPullParser.END_DOCUMENT) {
                if (eventT == XmlPullParser.START_TAG) {
                    if(xpp.getName().equalsIgnoreCase("item"))
                        break;

                    if (xpp.getName().equalsIgnoreCase("channel")) {
                        isChannel = true;
                    } else if (xpp.getName().equalsIgnoreCase("title")) {
                        if (isChannel)
                            myfeed.title = xpp.nextText(); //extract the headline
                    } else if (xpp.getName().equalsIgnoreCase("link")) {
                        if (isChannel)
                            myfeed.url = xpp.nextText();
                    } else if (xpp.getName().equalsIgnoreCase("description")) {
                        if (isChannel)
                            myfeed.description = xpp.nextText();
                    }
                }

                eventT = xpp.next(); //move to next element
            }

            boolean insideItem = false;

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
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        // Binding data
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, headlines);

        setListAdapter(adapter);


    }
    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }
    }*/

   }

}