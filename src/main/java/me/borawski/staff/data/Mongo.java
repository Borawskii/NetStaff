package me.borawski.staff.data;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;
import java.util.List;

public class Mongo {

    private Morphia morphia;
    private MongoClient mongoClient;
    private Datastore datastore;

    private final String DATABASE_NAME = "netcore";

    public Mongo() {
        morphia = new Morphia();

        ServerAddress address = new ServerAddress("192.99.15.171", 27017);

        List<MongoCredential> credentialList = new ArrayList<>();
        credentialList.add(MongoCredential.createMongoCRCredential("THC-Perms", DATABASE_NAME, "q5mEM7EmVSUFTGvq".toCharArray()));

        mongoClient = new MongoClient(address);
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
