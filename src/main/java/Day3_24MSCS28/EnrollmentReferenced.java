package Day3_24MSCS28;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
public class EnrollmentReferenced {
    public static void enrollReferenced(MongoDatabase db, ObjectId studentId, ObjectId courseId) {
        MongoCollection<Document> collection = db.getCollection("enrollments");
        Document enrollment = new Document("type", "referenced")
                .append("student_id", studentId)
                .append("course_id", courseId)
                .append("status", "enrolled");
        collection.insertOne(enrollment);
    }
}
