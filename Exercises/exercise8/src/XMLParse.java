import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XMLParse {
    public static void parseAndUpdateXML(String filePath) throws Exception {
        // parse XML file
        File file = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();

        // print original XML content
        System.out.println("-------------------BookShelf (XML) ----------------");
        outputInfo(doc);

        // add new book
        Element book = doc.createElement("Book");

        Element title = doc.createElement("title");
        title.appendChild(doc.createTextNode("New Book(XML)"));
        book.appendChild(title);

        Element year = doc.createElement("publishedYear");
        year.appendChild(doc.createTextNode("2025"));
        book.appendChild(year);

        Element pages = doc.createElement("numberOfPages");
        pages.appendChild(doc.createTextNode("555"));
        book.appendChild(pages);

        Element authors = doc.createElement("authors");
        Element author = doc.createElement("author");
        Element author1 = doc.createElement("author");
        author.appendChild(doc.createTextNode("Siyu Jin"));
        author1.appendChild(doc.createTextNode("Java"));
        authors.appendChild(author);
        authors.appendChild(author1);
        book.appendChild(authors);

        doc.getDocumentElement().appendChild(book);

        // write back to file
        // Transformer transformer = TransformerFactory.newInstance().newTransformer();
        // transformer.transform(new DOMSource(doc), new StreamResult(file));

        System.out.println("----------------------- BookShelf After Adding A New Book (XML)------------------------");
        outputInfo(doc);
    }

    // print XML content
    public static void outputInfo(Document doc) {
        // obtain all books
        NodeList books = doc.getElementsByTagName("Book");

        for (int i = 0; i < books.getLength(); i++) {
            Element book = (Element) books.item(i);
            System.out.println("Title: " + book.getElementsByTagName("title").item(0).getTextContent());
            System.out.println("Year: " + book.getElementsByTagName("publishedYear").item(0).getTextContent());
            System.out.println("Pages: " + book.getElementsByTagName("numberOfPages").item(0).getTextContent());

            NodeList authors = book.getElementsByTagName("author");
            System.out.print("Authors: ");
            for (int j = 0; j < authors.getLength(); j++) {
                System.out.print(authors.item(j).getTextContent());
                if (j < authors.getLength() - 1) System.out.print(", ");
            }
            System.out.println("\n");
        }
    }
}
