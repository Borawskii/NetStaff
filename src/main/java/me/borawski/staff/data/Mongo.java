package me.borawski.staff.data;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class Mongo {

    private Morphia morphia;
    private MongoClient mongoClient;
    private Datastore datastore;

    private final String DATABASE_NAME = "netcore";

    public Mongo() {
        morphia = new Morphia();
        mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
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
