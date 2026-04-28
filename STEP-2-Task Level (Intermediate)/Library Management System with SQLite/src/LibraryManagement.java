import java.sql.*;
import java.util.Scanner;

public class LibraryManagement {

    static final String URL = "jdbc:sqlite:./library.db";

    public static void main(String[] args) {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            System.out.println(e);
        }

        createTable();

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
                case 1:
                    addBook(sc);
                    break;

                case 2:
                    viewBooks();
                    break;

                case 3:
                    searchBook(sc);
                    break;

                case 4:
                    deleteBook(sc);
                    break;

                case 5:
                    System.out.println("Thank You");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    static void createTable() {

        try (Connection con = DriverManager.getConnection(URL)) {

            String sql = "CREATE TABLE IF NOT EXISTS books ("
                    + "id INTEGER PRIMARY KEY, "
                    + "title TEXT, "
                    + "author TEXT, "
                    + "quantity INTEGER)";

            Statement st = con.createStatement();
            st.execute(sql);

        } catch (Exception e) {
            System.out.println("Create Table Error: " + e);
        }
    }

    static void addBook(Scanner sc) {

        try (Connection con = DriverManager.getConnection(URL)) {

            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Title: ");
            String title = sc.nextLine();

            System.out.print("Enter Author: ");
            String author = sc.nextLine();

            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            String sql = "INSERT INTO books VALUES(?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setInt(4, qty);

            ps.executeUpdate();

            System.out.println("Book Added Successfully.");

        } catch (Exception e) {
            System.out.println("Add Error: " + e);
        }
    }

    static void viewBooks() {

        try (Connection con = DriverManager.getConnection(URL)) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM books");

            System.out.println("\n----- Book List -----");

            while (rs.next()) {

                System.out.println("----------------------");
                System.out.println("ID      : " + rs.getInt("id"));
                System.out.println("Title   : " + rs.getString("title"));
                System.out.println("Author  : " + rs.getString("author"));
                System.out.println("Quantity: " + rs.getInt("quantity"));
            }

        } catch (Exception e) {
            System.out.println("View Error: " + e);
        }
    }

    static void searchBook(Scanner sc) {

        try (Connection con = DriverManager.getConnection(URL)) {

            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();

            String sql = "SELECT * FROM books WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\nBook Found");
                System.out.println("ID      : " + rs.getInt("id"));
                System.out.println("Title   : " + rs.getString("title"));
                System.out.println("Author  : " + rs.getString("author"));
                System.out.println("Quantity: " + rs.getInt("quantity"));

            } else {
                System.out.println("Book Not Found");
            }

        } catch (Exception e) {
            System.out.println("Search Error: " + e);
        }
    }

    static void deleteBook(Scanner sc) {

        try (Connection con = DriverManager.getConnection(URL)) {

            System.out.print("Enter Book ID: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM books WHERE id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("Book Deleted Successfully.");
            else
                System.out.println("Book Not Found.");

        } catch (Exception e) {
            System.out.println("Delete Error: " + e);
        }
    }
}