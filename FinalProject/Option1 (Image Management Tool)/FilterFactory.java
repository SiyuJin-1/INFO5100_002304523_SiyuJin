public class FilterFactory {
    public static Filter getFilter(String type) {
        return switch (type) {
            case "bw" -> new BlackWhiteFilter();
            case "invert" -> new InvertFilter();
            default -> throw new IllegalArgumentException("Unknown filter type: " + type);
        };
    }
}
