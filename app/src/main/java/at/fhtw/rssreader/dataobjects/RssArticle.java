package at.fhtw.rssreader.dataobjects;

/**
 * Created by Daniel on 16.10.2014.
 */
public class RssArticle {
    public long articleid;
    public String title;
    public String link;
    public String description;

    public RssArticle(String title, String url, String description) {
        this.title = title;
        this.link = url;
        this.description = description;
    }



    public RssArticle(){}

    public RssArticle(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return articleid;
    }

    public String getUrl() {
        return link;
    }

    public String getDescription() {
        return description;
    }
}
