package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Book;

public class LibraryService {

    // ✅ ADD BOOK (DB)
    public void addBook(Book book) {
        String sql = "INSERT INTO books (id, title, author, issued) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, book.getId());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setBoolean(4, false);

            ps.executeUpdate();
            System.out.println("✅ Book added to database");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ VIEW BOOKS (DB)
    public void viewBooks() {
        String sql = "SELECT * FROM books";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean empty = true;
            while (rs.next()) {
                empty = false;
                System.out.println(
                    "ID: " + rs.getInt("id") +
                    ", Title: " + rs.getString("title") +
                    ", Author: " + rs.getString("author") +
                    ", Issued: " + rs.getBoolean("issued")
                );
            }

            if (empty) {
                System.out.println("No books found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ DELETE BOOK (DB)
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0)
                System.out.println("✅ Book deleted");
            else
                System.out.println("❌ Book not found");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
