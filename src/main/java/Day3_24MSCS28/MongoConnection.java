package Day3_24MSCS28;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
    public static MongoDatabase getDatabase() {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        return mongoClient.getDatabase("college");
    }
}
