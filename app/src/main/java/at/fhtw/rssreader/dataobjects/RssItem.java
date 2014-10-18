package at.fhtw.rssreader.dataobjects;

import java.util.Date;

/**
 * Created by Daniel on 16.10.2014.
 */
public class RssItem {
public long itemid;
    public String title;
    public String date;
    public String link;
    public String description;

    public RssItem(String title, String link, String description) {
        this.title = title;
        this.date = date;
        this.link = link;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return itemid;
    }

    public String getDate() {
        return date;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

}
