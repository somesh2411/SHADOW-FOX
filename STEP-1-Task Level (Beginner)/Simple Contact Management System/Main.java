import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

// Contact Class
class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void display() {
        System.out.println("----------------------");
        System.out.println("Name  : " + name);
        System.out.println("Phone : " + phone);
        System.out.println("Email : " + email);
    }
}

// ContactManager Class
class ContactManager {
    private ArrayList<Contact> contacts = new ArrayList<>();

    public void addContact(String name, String phone, String email) {
        contacts.add(new Contact(name, phone, email));
        System.out.println("Contact Added Successfully.");
    }

    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No Contacts Available.");
            return;
        }

        for (Contact c : contacts) {
            c.display();
        }
    }

    public void searchContact(String name) {
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                c.display();
                return;
            }
        }
        System.out.println("Contact Not Found.");
    }

    public void updateContact(String name, String newPhone, String newEmail) {
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                c.setPhone(newPhone);
                c.setEmail(newEmail);
                System.out.println("Contact Updated Successfully.");
                return;
            }
        }
        System.out.println("Contact Not Found.");
    }

    public void deleteContact(String name) {
        Iterator<Contact> it = contacts.iterator();

        while (it.hasNext()) {
            Contact c = it.next();

            if (c.getName().equalsIgnoreCase(name)) {
                it.remove();
                System.out.println("Contact Deleted Successfully.");
                return;
            }
        }

        System.out.println("Contact Not Found.");
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ContactManager manager = new ContactManager();

        while (true) {
            System.out.println("\n===== Contact Management System =====");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Update Contact");
            System.out.println("5. Delete Contact");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    manager.addContact(name, phone, email);
                    break;

                case 2:
                    manager.viewContacts();
                    break;

                case 3:
                    System.out.print("Enter Name to Search: ");
                    String searchName = sc.nextLine();
                    manager.searchContact(searchName);
                    break;

                case 4:
                    System.out.print("Enter Name to Update: ");
                    String updateName = sc.nextLine();

                    System.out.print("Enter New Phone: ");
                    String newPhone = sc.nextLine();

                    System.out.print("Enter New Email: ");
                    String newEmail = sc.nextLine();

                    manager.updateContact(updateName, newPhone, newEmail);
                    break;

                case 5:
                    System.out.print("Enter Name to Delete: ");
                    String deleteName = sc.nextLine();

                    manager.deleteContact(deleteName);
                    break;

                case 6:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }
}