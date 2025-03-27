import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParse {

    // parse JSON file
    public static void parseAndUpdateJSON(String filePath) throws Exception {
        // read JSON file content
        String content = new String(Files.readAllBytes(Paths.get(filePath)));

        // 转换为 JSONObject
        JSONObject json = new JSONObject(content);

        // obtain all books
        JSONArray books = json.getJSONObject("BookShelf").getJSONArray("Book");

        // print original JSON content
        System.out.println("-------------------BookShelf (Json) ----------------");
        outputInfo(books);

        // add new book
        JSONObject newBook = new JSONObject();
        newBook.put("title", "New Book(Json)");
        newBook.put("publishedYear", 2025);
        newBook.put("numberOfPages", 555);
        newBook.put("authors", new JSONArray().put("Siyu Jin").put("Java"));
        books.put(newBook);

        // write back to file
        // Files.write(Paths.get(filePath), json.toString(4).getBytes());

        System.out.println("----------------------- BookShelf After Adding A New Book (Json)------------------------");
        outputInfo(books);
    }

    // print JSON content
    private static void outputInfo(JSONArray books) {
        for (int i = 0; i < books.length(); i++) {
            JSONObject book = books.getJSONObject(i);
            System.out.println("Title: " + book.getString("title"));
            System.out.println("Year: " + book.getInt("publishedYear"));
            System.out.println("Pages: " + book.getInt("numberOfPages"));

            System.out.print("Authors: ");
            JSONArray authors = book.getJSONArray("authors");
            for (int j = 0; j < authors.length(); j++) {
                System.out.print(authors.getString(j));
                if (j < authors.length() - 1) System.out.print(", ");
            }
            System.out.println("\n");
        }
    }
}
