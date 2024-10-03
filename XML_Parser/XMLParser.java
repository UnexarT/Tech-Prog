import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    public static void main(String[] args) {
        String filePath = "C:/Users/NosovYu/Downloads/data/data/random_structure_20.xml";
        List<Book> books = parseXML(filePath);

        /*for (Book book : books) {
            System.out.println(book);
        }*/
    }

    public static List<Book> parseXML(String filePath) {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            Book book = null;
            Review review = null;
            Publisher publisher = null;
            Address address = null;
            while ((line = br.readLine()) != null) {
                String[] lines = (line.replaceAll("><", ">\n<")).split("\n");
                for (String lines_item : lines) {
                    if (lines_item.startsWith("<book ")) {
                        book = new Book();
                        book.id = Integer.parseInt(lines_item.replaceAll("[^0-9]", ""));
                    } else if (lines_item.startsWith("<title>")) {
                        book.title = extractValue(lines_item, "title");
                    } else if (lines_item.startsWith("<author>")) {
                        book.author = extractValue(lines_item, "author");
                    } else if (lines_item.startsWith("<year>")) {
                        book.year = Integer.parseInt(extractValue(lines_item, "year"));
                    } else if (lines_item.startsWith("<genre>")) {
                        book.genre = extractValue(lines_item, "genre");
                    } else if (lines_item.startsWith("<price")) {
                        book.price = new Price();
                        book.price.currency = extractAttribute(lines_item, "currency");
                        book.price.amount = Double.parseDouble(extractValue(lines_item, "price"));
                    } else if (lines_item.startsWith("<isbn>")) {
                        book.isbn = extractValue(lines_item, "isbn");
                    } else if (lines_item.startsWith("<translator>")) {
                        book.translator = extractValue(lines_item, "translator");
                    } else if (lines_item.startsWith("<award>")) {
                        book.awards.add(extractValue(lines_item, "award"));
                    } else if (lines_item.startsWith("<review>")) {
                        review = new Review();
                    } else if (lines_item.startsWith("<user>")) {
                        review.user = extractValue(lines_item, "user");
                    } else if (lines_item.startsWith("<rating>")) {
                        review.rating = Integer.parseInt(extractValue(lines_item, "rating"));
                    } else if (lines_item.startsWith("<comment>")) {
                        review.comment = extractValue(lines_item, "comment");
                    } else if (lines_item.startsWith("<language>")) {
                        book.language = extractValue(lines_item, "language");
                    } else if (lines_item.startsWith("<publisher>")) {
                        publisher = new Publisher();
                    } else if (lines_item.startsWith("<name>")) {
                        publisher.name = extractValue(lines_item, "name");
                    } else if (lines_item.startsWith("<address>")) {
                        address = new Address();
                    }else if (lines_item.startsWith("<city>")) {
                        address.city = extractValue(lines_item, "city");
                    } else if (lines_item.startsWith("<country>")) {
                        address.country = extractValue(lines_item, "country");
                    } else if (lines_item.startsWith("<format>")) {
                        book.format = extractValue(line, "format");
                    } else if (lines_item.startsWith("</address>")) {
                        publisher.address = address;
                    } else if (lines_item.startsWith("</publisher>")) {
                        book.publisher = publisher;
                    } else if (lines_item.startsWith("</book>")) {
                        books.add(book);
                        System.out.println("Книга добавлена: " + book);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    private static String extractValue(String line, String tag) {
        return line.substring(line.indexOf(">") + 1, line.lastIndexOf("<"));
    }

    private static String extractAttribute(String line, String attribute) {
        int start = line.indexOf(attribute + "=\"") + attribute.length() + 2;
        int end = line.indexOf("\"", start);
        return line.substring(start, end);
    }
}

class Book {
    int id;
    String title;
    String author;
    int year;
    String genre;
    Price price;
    String isbn;
    String translator;
    List<String> awards = new ArrayList<>();
    List<Review> reviews = new ArrayList<>();
    String language;
    Publisher publisher;
    String format;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", isbn='" + isbn + '\'' +
                ", translator='" + translator + '\'' +
                ", awards=" + awards +
                ", reviews=" + reviews +
                ", language='" + language + '\'' +
                ", publisher=" + publisher +
                ", format='" + format + '\'' +
                '}';
    }
}

class Price {
    String currency;
    double amount;

    @Override
    public String toString() {
        return "Price{" +
                "currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }
}

class Review {
    String user;
    int rating;
    String comment;

    @Override
    public String toString() {
        return "Review{" +
                "user='" + user + '\'' +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}

class Publisher {
    String name;
    Address address;

    @Override
    public String toString() {
        return "Publisher{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

class Address {
    String city;
    String country;

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
