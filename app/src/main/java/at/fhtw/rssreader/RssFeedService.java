package at.fhtw.rssreader;

/**
 * Created by Marco on 17.10.2014.
 */

        import java.io.IOException;
        import java.io.InputStream;
        import java.io.Serializable;
        import java.net.URL;
        import java.util.List;

        import org.xmlpull.v1.XmlPullParserException;

        import android.app.IntentService;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.ResultReceiver;
        import android.provider.SyncStateContract;
        import android.util.Log;

        import at.fhtw.rssreader.dataobjects.RssFeed;
        import at.fhtw.rssreader.dataobjects.RssItem;

public class RssFeedService extends IntentService {

    public static final String ITEMS = "items";
    public static final String RECEIVER = "receiver";

    public RssFeedService() {
        super("RssFeedService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
    }

}