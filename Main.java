import java.util.Scanner;
import model.Book;
import service.LibraryService;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LibraryService service = new LibraryService();
        int choice;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Delete Book");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
    System.out.print("Enter Book ID: ");
    int id = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter Title: ");
    String title = sc.nextLine();

    System.out.print("Enter Author: ");
    String author = sc.nextLine();

    Book book = new Book(id, title, author);
    service.addBook(book);   // 👈 THIS MUST CALL DB METHOD
    break;

case 2:
    service.viewBooks();
    break;

case 5:
    System.out.print("Enter Book ID to delete: ");
    int delId = sc.nextInt();
    service.deleteBook(delId);
    break;

            }

        } while (choice != 4);

        sc.close();
    }
}
