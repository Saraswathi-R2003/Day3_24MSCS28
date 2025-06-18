package Day3_24MSCS28;

import org.bson.Document;

public class Course {
    public static Document createCourse(String title, int credits) {
        return new Document("title", title)
                .append("credits", credits);
    }
}

