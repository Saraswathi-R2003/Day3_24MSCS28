package Day3_24MSCS28;

import org.bson.Document;
public class Student {
    public static Document createStudent(String name, int age) {
        return new Document("name", name)
                .append("age", age);
    }
}
