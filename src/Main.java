public class Main {
    public static void main(String[] args) {
        // Create a new library
        Library library = new Library();

        // Add some books to the library
        Book book1 = new Book("Atomic Habits", "James Clear", 2018, 320, "Self-help");
        library.addBook(book1);

        Book book2 = new Book("Harry Potter", "J.K. Rowling", 1997, 310, "Fantasy");
        library.addBook(book2);

        Book book3 = new Book("Another Random Book", "Random Author", 2023, 250, "Mystery");
        library.addBook(book3);

        // print Alphabetically
        library.printBookTitlesSortedAlphabetically();
        System.out.println("");

        String titleToRemove = "Harry Potter";
        library.removeBook(titleToRemove);
        library.printBookTitlesSortedAlphabetically();
        System.out.println("");


        //find book with the most pages
        Book bookWithMostPages = library.findBookWithMostPages();
        System.out.println("Book with the most pages: " + bookWithMostPages.getTitle());

        //loan and return books
        library.loanBook("Atomic habits");

        library.returnBook("Atomic Habits");

        User user = new User("Mary", "12345");
        library.registerUser(user.getName(), user.getLibraryCardNumber());

        // Loan a book to the user
        String bookToLoan = "Another random book";
        library.loanBook(bookToLoan);


    }
}