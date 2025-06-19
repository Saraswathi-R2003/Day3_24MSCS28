package Day3_24MSCS28;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MainApp {
    public static void main(String[] args) {
        MongoDatabase db = MongoConnection.getDatabase();

        Document student1 = Student.createStudent("Alice", 21);
        Document course1 = Course.createCourse("Math 101", 4);

        Document student2 = Student.createStudent("Kuttal", 23);
        Document course2 = Course.createCourse("English 104", 4);

        MongoCollection<Document> students = db.getCollection("students");
        MongoCollection<Document> courses = db.getCollection("courses");

        students.insertOne(student1);
        courses.insertOne(course1);

        students.insertOne(student2);
        courses.insertOne(course2);

        EnrollmentEmbedded.enrollEmbedded(db, student1, course1);
        EnrollmentReferenced.enrollReferenced(db, student1.getObjectId("_id"), course1.getObjectId("_id"));

        EnrollmentEmbedded.enrollEmbedded(db, student2, course2);
        EnrollmentReferenced.enrollReferenced(db, student2.getObjectId("_id"), course2.getObjectId("_id"));

        System.out.println("Both embedded and referenced enrollments have been inserted.\n");

        // ✅ Print Enrollments clearly
        MongoCollection<Document> enrollments = db.getCollection("enrollments");

        System.out.println("--- Enrollments Collection ---");
        try (MongoCursor<Document> cursor = enrollments.find().iterator()) {
            while (cursor.hasNext()) {
                Document enrollment = cursor.next();
                System.out.println("Enrollment ID: " + enrollment.getObjectId("_id"));

                if (enrollment.containsKey("student") && enrollment.containsKey("course")) {
                    // Embedded format
                    Document studentDoc = (Document) enrollment.get("student");
                    Document courseDoc = (Document) enrollment.get("course");

                    System.out.println("Student (Embedded): " + studentDoc.toJson());
                    System.out.println("Course (Embedded): " + courseDoc.toJson());
                } else if (enrollment.containsKey("student_id") && enrollment.containsKey("course_id")) {
                    // Referenced format
                    System.out.println("Student ID (Referenced): " + enrollment.getObjectId("student_id"));
                    System.out.println("Course ID (Referenced): " + enrollment.getObjectId("course_id"));
                }

                System.out.println(); // empty line between entries
            }
        }
    }
}
