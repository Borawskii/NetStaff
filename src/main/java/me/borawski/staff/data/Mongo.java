package me.borawski.staff.data;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;

public class Mongo {

    private Morphia morphia;
    private MongoClient mongoClient;
    private Datastore datastore;

    private final String DATABASE_NAME = "netcore";

    public Mongo() {
        morphia = new Morphia();
        mongoClient = new MongoClient(new ServerAddress("192.99.15.171", 27017), new ArrayList<MongoCredential>() {
            {
                this.add(MongoCredential.createMongoCRCredential("netcore", DATABASE_NAME, "B1VSGVCb".toCharArray()));
            }
        });
        datastore = morphia.createDatastore(mongoClient, DATABASE_NAME);
    }

    public Morphia getMorphia() {
        return morphia;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public Datastore getDatastore() {
        return datastore;
    }

}
