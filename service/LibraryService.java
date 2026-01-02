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
public void issueBook(int id) {
    String checkSql = "SELECT issued FROM books WHERE id = ?";
    String issueSql = "UPDATE books SET issued = 1 WHERE id = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement checkPs = con.prepareStatement(checkSql)) {

        checkPs.setInt(1, id);
        ResultSet rs = checkPs.executeQuery();

        if (rs.next()) {
            if (rs.getBoolean("issued")) {
                System.out.println("❌ Book already issued");
            } else {
                PreparedStatement issuePs = con.prepareStatement(issueSql);
                issuePs.setInt(1, id);
                issuePs.executeUpdate();
                System.out.println("✅ Book issued successfully");
            }
        } else {
            System.out.println("❌ Book not found");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}


public void returnBook(int bookId) {

    String sql = "UPDATE issued_books SET return_date = CURDATE() " +
                 "WHERE book_id = ? AND return_date IS NULL";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, bookId);
        int rows = ps.executeUpdate();

        if (rows > 0)
            System.out.println("✅ Book returned successfully");
        else
            System.out.println("❌ Book not issued or already returned");

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

