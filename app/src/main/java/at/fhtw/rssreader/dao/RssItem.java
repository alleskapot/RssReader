package at.fhtw.rssreader.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Entity mapped to table RSS_ITEM.
 */
public class RssItem implements Comparable<RssItem>, Parcelable {

    private Long id;
    private String title;
    private String description;
    private String link;
    private Date pubDate;
    private String content;
    private Boolean read;
    private Boolean highlighted;

    public RssItem() {
    }

    public RssItem(Parcel source) {

        Bundle data = source.readBundle();
        title = data.getString("title");
        link = data.getString("link");
        pubDate = (Date) data.getSerializable("pubDate");
        description = data.getString("description");
        content = data.getString("content");

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        Bundle data = new Bundle();
        data.putString("title", title);
        data.putString("link", link);
        data.putSerializable("pubDate", pubDate);
        data.putString("description", description);
        data.putString("content", content);
        dest.writeBundle(data);
    }

    public static final Parcelable.Creator<RssItem> CREATOR = new Parcelable.Creator<RssItem>() {
        public RssItem createFromParcel(Parcel data) {
            return new RssItem(data);
        }
        public RssItem[] newArray(int size) {
            return new RssItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public RssItem(Long id) {
        this.id = id;
    }

    public RssItem(Long id, String title, String description, String link, java.util.Date pubDate, String content, Boolean read, Boolean highlighted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
        this.content = content;
        this.read = read;
        this.highlighted = highlighted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public java.util.Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(java.util.Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getHighlighted() {
        return highlighted;
    }

    public void setHighlighted(Boolean highlighted) {
        this.highlighted = highlighted;
    }

    @Override
    public int compareTo(RssItem another) {
        if(getPubDate() != null && another.getPubDate() != null) {
            return getPubDate().compareTo(another.getPubDate());
        } else {
            return 0;
        }
    }

}