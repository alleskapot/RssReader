package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(3, "at.fhtw.rssreader");

        Entity feedtbl = schema.addEntity("RssFeed");
        feedtbl.addIdProperty().autoincrement();
        feedtbl.addStringProperty("title");
        feedtbl.addStringProperty("link");
        feedtbl.addStringProperty("description");
        feedtbl.addStringProperty("language");

        Entity articletbl = schema.addEntity("RssItem");
        Property articleId = articletbl.addIdProperty().autoincrement().getProperty();
        articletbl.addStringProperty("title");
        articletbl.addStringProperty("description");
        articletbl.addStringProperty("link");
        articletbl.addDateProperty("pubDate");
        articletbl.addStringProperty("content");
        articletbl.addBooleanProperty("read");
        articletbl.addBooleanProperty("highlighted");

        feedtbl.addToMany(articletbl, articleId, "rssItems");

        new DaoGenerator().generateAll(schema, args[0]);
    }
}
