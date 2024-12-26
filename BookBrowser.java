import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class BookBrowser extends JFrame {

    private Book[] books;
    private int currentIndex = 0;
    private JTextField isbnField;
    private JTextField authorsField;
    private JTextField yearField;
    private JTextField originalTitleField;
    private JTextField titleField;
    private JTextField ratingField;
    private JComboBox<String> orderByComboBox;
    private static TreeMap<String, Book> isbnTree= new TreeMap<>();
    private static TreeMap<String, Book> authorsTree= new TreeMap<>();
    private static TreeMap<Integer, Book> yearTree= new TreeMap<>();
    private static TreeMap<String, Book> originalTitleTree= new TreeMap<>();
    private static TreeMap<String, Book> titleTree= new TreeMap<>();
    private static TreeMap<Double, Book> ratingTree= new TreeMap<>();
    private static HashMap<String, TreeMap<?, Book>> orderedMaps = new HashMap<>();

    public static void main (String[]args){
        try {
            Book[] books = loadBooksFromFile("BooksDataFile.txt");
            new BookBrowser(books);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public BookBrowser(Book[] books) {
        this.books = books;

        setupUserInterface();
        displayBookDetails(books[currentIndex]);
    }
    public static Book[] loadBooksFromFile (String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        int bookCount = 0;
        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNext()) {
                scanner.nextLine();
            }
            while (scanner.hasNext()) {
                scanner.nextLine();
                bookCount++;
            }
        }
        Book[] books = new Book[bookCount];

        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNext()) {
                scanner.nextLine();
            }
            int index = 0;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split("~");
                String isbn = parts[2];
                String authors = parts[3];
                int publicationYear = Integer.parseInt(parts[4]);
                String originalTitle = parts[5];
                String title = parts[6];
                double averageRating = Double.parseDouble(parts[7]);

                Book book = new Book(isbn, authors, publicationYear, originalTitle, title, averageRating);

                isbnTree.put(isbn, book);
                authorsTree.put(authors, book);
                yearTree.put(publicationYear, book);
                originalTitleTree.put(originalTitle, book);
                titleTree.put(title, book);
                ratingTree.put(averageRating, book);

                orderedMaps = new HashMap<>();
                orderedMaps.put("ISBN", isbnTree);
                orderedMaps.put("Authors", authorsTree);
                orderedMaps.put("Publication Year", yearTree);
                orderedMaps.put("Original Title", originalTitleTree);
                orderedMaps.put("Title", titleTree);
                orderedMaps.put("Average Rating", ratingTree);

            }
        }
        return isbnTree.toValueArray(new Book[isbnTree.size()]);
    }
    private void setupUserInterface() {
        setTitle("Book Browser");
        setSize(600, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setUndecorated(false);
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

        int labelStartY = 65;
        int labelStartOrderY = 20;
        int labelStartX = 10;
        int labelIncrementY = 45;

        JLabel orderFixedLabel = new JLabel("Order:");
        orderFixedLabel.setBounds(10, labelStartOrderY, 100, 25);
        add(orderFixedLabel);

        orderByComboBox = new JComboBox<>(new String[]{"ISBN", "Authors", "Publication Year", "Original Title", "Title", "Average Rating"});
        orderByComboBox.setBounds(130, labelStartOrderY, 400, 25);
        add(orderByComboBox);

        JLabel isbnFixedLabel = new JLabel("ISBN:");
        isbnFixedLabel.setBounds(labelStartX, labelStartY, 100, 25);
        add(isbnFixedLabel);

        JLabel authorsFixedLabel = new JLabel("Authors:");
        authorsFixedLabel.setBounds(labelStartX, labelStartY + labelIncrementY, 100, 25);
        add(authorsFixedLabel);

        JLabel yearFixedLabel = new JLabel("Year:");
        yearFixedLabel.setBounds(labelStartX, labelStartY + 2 * labelIncrementY, 120, 25);
        add(yearFixedLabel);

        JLabel originalTitleFixedLabel = new JLabel("Orig.Title:");
        originalTitleFixedLabel.setBounds(labelStartX, labelStartY + 3 * labelIncrementY, 120, 25);
        add(originalTitleFixedLabel);

        JLabel titleFixedLabel = new JLabel("Title:");
        titleFixedLabel.setBounds(labelStartX, labelStartY + 4 * labelIncrementY, 100, 25);
        add(titleFixedLabel);

        JLabel ratingFixedLabel = new JLabel("Avg. Rating:");
        ratingFixedLabel.setBounds(labelStartX, labelStartY + 5 * labelIncrementY, 120, 25);
        add(ratingFixedLabel);

        int dataStartX = 130;

        isbnField = new JTextField();
        isbnField.setBounds(dataStartX, labelStartY, 400, 25);
        isbnField.setEditable(false);
        add(isbnField);

        authorsField = new JTextField();
        authorsField.setBounds(dataStartX, labelStartY + labelIncrementY, 400, 25);
        authorsField.setEditable(false);
        add(authorsField);

        yearField =  new JTextField();
        yearField.setBounds(dataStartX, labelStartY + 2 * labelIncrementY, 400, 25);
        yearField.setEditable(false);
        add(yearField);

        originalTitleField =  new JTextField();
        originalTitleField.setBounds(dataStartX, labelStartY + 3 * labelIncrementY, 400, 25);
        originalTitleField.setEditable(false);
        add(originalTitleField);

        titleField =  new JTextField();
        titleField.setBounds(dataStartX, labelStartY + 4 * labelIncrementY, 400, 25);
        titleField.setEditable(false);
        add(titleField);

        ratingField =  new JTextField();
        ratingField.setBounds(dataStartX, labelStartY + 5 * labelIncrementY, 400, 25);
        ratingField.setEditable(false);
        add(ratingField);

        JButton prevButton = new JButton("< Prev");
        prevButton.setBounds(290, labelStartY + 6 * labelIncrementY, 80, 20);
        add(prevButton);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex > 0) {
                    currentIndex--;
                    displayBookDetails(books[currentIndex]);
                }
            }
        });

        JButton nextButton = new JButton("Next >");
        nextButton.setBounds(405, labelStartY + 6 * labelIncrementY, 80, 20);
        add(nextButton);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < books.length - 1) {
                    currentIndex++;
                    displayBookDetails(books[currentIndex]);
                }
            }
        });

        setVisible(true);

        JButton beforePreviousButton = new JButton("|<-");
        beforePreviousButton.setBounds(250, labelStartY + 6 * labelIncrementY, 40, 20);
        add(beforePreviousButton);
        beforePreviousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = 0;
                displayBookDetails(books[currentIndex]);
            }
        });

        JButton afterNextButton = new JButton("->|");
        afterNextButton.setBounds(485, labelStartY + 6 * labelIncrementY, 40, 20);
        add(afterNextButton);
        afterNextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = books.length - 1;
                displayBookDetails(books[currentIndex]);
            }
        });
        orderByComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOrder = (String) orderByComboBox.getSelectedItem();
                TreeMap<?, Book> orderedTree = orderedMaps.get(selectedOrder);
                updateBooksUsingOrder(orderedTree);
                displayBookDetails(books[currentIndex]);
            }
        });
    }
    private void displayBookDetails(Book book) {
        isbnField.setText(book.ISBN());
        authorsField.setText(book.authors());
        yearField.setText(String.valueOf(book.publicationYear()));
        originalTitleField.setText(book.originalTitle());
        titleField.setText(book.title());
        ratingField.setText(String.valueOf(book.averageRating()));
    }
    private void updateBooksUsingOrder(TreeMap<?, Book> orderedTree) {
        books = orderedTree.toValueArray(new Book[orderedTree.size()]);
        currentIndex = 0;
        displayBookDetails(books[currentIndex]);
    }
}