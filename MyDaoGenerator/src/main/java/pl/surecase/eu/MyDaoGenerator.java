package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class MyDaoGenerator {

    Schema schema = new Schema(3, "at.fhtw.rssreader");

    private void generateSchema(String outputDirectory){


        Entity feedtbl = schema.addEntity("RssFeed");
        feedtbl.addIdProperty().autoincrement();
        feedtbl.addStringProperty("title");
        feedtbl.addStringProperty("link");
        feedtbl.addStringProperty("description");
        feedtbl.addStringProperty("language");

        Entity articletbl = this.schema.addEntity("RssItem");
        Property articleId = articletbl.addIdProperty().autoincrement().getProperty();
        articletbl.addStringProperty("title");
        articletbl.addStringProperty("description");
        articletbl.addStringProperty("link");
        articletbl.addDateProperty("pubDate");
        articletbl.addStringProperty("content");
        articletbl.addBooleanProperty("read");
        articletbl.addBooleanProperty("favorite");

        // Item belongs to ONE feed
        articletbl.addToOne(feedtbl, articleId);

        // Feed has multiple items
        ToMany feedToItems = feedtbl.addToMany(articletbl, articleId);
        feedToItems.setName("items");

        // Autogenerate content provider
        feedtbl.addContentProvider();
        articletbl.addContentProvider();

        try {
            new DaoGenerator().generateAll(schema, outputDirectory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) throws Exception {
        MyDaoGenerator generator = new MyDaoGenerator();
        generator.generateSchema(args[0]);
    }
}
