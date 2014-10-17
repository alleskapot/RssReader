package at.fhtw.rssreader.dataobjects;

/**
 * Created by Daniel on 16.10.2014.
 */
public class RssFeed {

    public RssFeed(String title, String url, String description) {
        this.title = title;
        this.url = url;
        this.description = description;
    }

    public String title;
    public String url;
    public String description;

    public RssFeed(){}

    public RssFeed(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
}
