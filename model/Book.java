package model;

public class Book {
    private int id;
    private String title;
    private String author;
    private boolean issued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    public int getId() {
        return id;
    }

    // ✅ ADD THESE TWO METHODS
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return issued;
    }

    public void issueBook() {
        this.issued = true;
    }

    public void returnBook() {
        this.issued = false;
    }

    public String toString() {
        return "ID: " + id +
               ", Title: " + title +
               ", Author: " + author +
               ", Status: " + (issued ? "Issued" : "Available");
    }
}
