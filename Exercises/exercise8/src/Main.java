public class Main {
    public static void main(String[] args) {
        try {
            // parse XML file
            XMLParse.parseAndUpdateXML("books.xml");

            System.out.println("\n********************************************************************************************\n");

            // parse JSON file
            JsonParse.parseAndUpdateJSON("books.json");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
