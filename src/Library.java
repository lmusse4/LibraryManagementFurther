import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    //Adds book
    public void addBook(Book book) {
        books.add(book);
    }

    //removes book by title
    public void removeBook(String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

    //find books by publication year
    public List<Book> findBooksByPublicationYear(int year) {
        return books.stream()
                .filter(book -> book.getPublicationYear() == year)
                .collect(Collectors.toList());
    }

    //find book by author
    public List<Book> findBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    //finds book with most pages
    public Book findBookWithMostPages() {
        return books.stream()
                .max(Comparator.comparingInt(Book::getPages))
                .orElse(null);
    }
    //find books with more than n pages
    public List<Book> findBooksWithMoreThanNPages(int n) {
        return books.stream()
                .filter(book -> book.getPages() > n)
                .collect(Collectors.toList());
    }

    //prints book titles sorted alphabetically
    public void printBookTitlesSortedAlphabetically() {
        books.stream()
                .map(Book::getTitle)
                .sorted()
                .forEach(System.out::println);
    }

    // Update the Book class to include a category
    public List<Book> findBooksByCategory(String category) {
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    //Allows to loan out a book
    public void loanBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null && !book.isOnLoan()) {
            book.setOnLoan(true);
            System.out.println("Book '" + book.getTitle() + "' has been loaned out.");
        } else {
            System.out.println("Book '" + title + "' is either not available or already on loan.");
        }
    }

    //Allows you to return a book
    public void returnBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null && book.isOnLoan()) {
            book.setOnLoan(false);
            System.out.println("Book '" + book.getTitle() + "' has been returned.");
        } else {
            System.out.println("Book '" + title + "' is not on loan or does not exist in the library.");
        }
    }

    //Easier to search for book by title as well
    private Book findBookByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public void registerUser(String name, String libraryCardNumber) {
        User user = new User(name, libraryCardNumber);
        users.add(user);
    }

    //implement in functional interface predicate
    private Book findBookByPredicate(Predicate<Book> bookPredicate) {
        return books.stream()
                .filter(bookPredicate)
                .findFirst()
                .orElse(null);
    }

    //calculate late fees
    void calculateLateFees(Book book, User user) {
        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = currentDate.plusWeeks(2);
        if (currentDate.isAfter(dueDate)) {
            long daysLate = currentDate.toEpochDay() - dueDate.toEpochDay();
            double lateFee = daysLate * 0.5; // late fee of $0.50 per day
            System.out.println("User '" + user.getName() + "' has incurred a late fee of $" + lateFee + " for book '"
                    + book.getTitle() + "'.");
        }
    }
}

