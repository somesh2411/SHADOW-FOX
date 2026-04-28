import java.util.ArrayList;
import java.util.Scanner;

class Book {
    int id;
    String title;
    String author;
    int quantity;

    Book(int id, String title, String author, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }
}

public class LibraryManagement1 {

    static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1: addBook(sc); break;
                case 2: viewBooks(); break;
                case 3: searchBook(sc); break;
                case 4: deleteBook(sc); break;
                case 5: System.exit(0);
                default: System.out.println("Invalid Choice");
            }
        }
    }

    static void addBook(Scanner sc) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        books.add(new Book(id, title, author, qty));
        System.out.println("Book Added.");
    }

    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No Books Available.");
            return;
        }

        for (Book b : books) {
            System.out.println("------------------");
            System.out.println("ID: " + b.id);
            System.out.println("Title: " + b.title);
            System.out.println("Author: " + b.author);
            System.out.println("Qty: " + b.quantity);
        }
    }

    static void searchBook(Scanner sc) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        for (Book b : books) {
            if (b.id == id) {
                System.out.println("Title: " + b.title);
                System.out.println("Author: " + b.author);
                System.out.println("Qty: " + b.quantity);
                return;
            }
        }

        System.out.println("Book Not Found.");
    }

    static void deleteBook(Scanner sc) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).id == id) {
                books.remove(i);
                System.out.println("Book Deleted.");
                return;
            }
        }

        System.out.println("Book Not Found.");
    }
}