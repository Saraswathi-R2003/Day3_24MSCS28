package Day3_24MSCS28;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class EnrollmentEmbedded {
    public static void enrollEmbedded(MongoDatabase db, Document student, Document course) {
        MongoCollection<Document> collection = db.getCollection("enrollments");
        Document enrollment = new Document("type", "embedded")
                .append("student", student)
                .append("course", course)
                .append("status", "enrolled");
        collection.insertOne(enrollment);
    }
}
