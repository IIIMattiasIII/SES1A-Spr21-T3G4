import java.util.ArrayList;

public class Book {
 public String isbn;
 public String title;
 public String author;
 public boolean checkedIn;
 public String genre;

     public Book(String id, String bookName, String bookAuthor, boolean check, String bookGenre) {
         this.isbn = id;
         this.title = bookName;
         this.author = bookAuthor;
         this.checkedIn = check;
         this.genre = bookGenre;

     }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCheckedIn(boolean checked) {
        this.checkedIn = checked;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    //Getters
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public String getGenre() {
        return genre;
    }
    

    public void printTableHeader(){
        System.out.println(String.format("%1s %15s %1s %20s %1s %20s %1s %20s %1s %20s %1s", "|",
                "ISBN", "|", "Book Title", "|", "Author", "|", "Checked In", "|",
                "Genre",  "|"));
        System.out.println(String.format("%s", "|---------------------------------------------------------" +
                "--------------------------------" +
                "--------------------|"));
    }

    public void printTable() {
        System.out.println(String.format("%1s %15s %1s %20s %1s %20s %1s %20s %1s %20s %1s", "|",
                this.getIsbn(), "|", this.getTitle(), "|", this.getAuthor(), "|", this.isCheckedIn(), "|",
                this.getGenre(), "|"));
    }

    public String toString(){
         return "Book.java ISBN: " + isbn + '\n' + "Book.java Name: " + title 
                 + '\n'
          + "Book.java Author: " + author + '\n' + "Book.java Checked Out: " 
                 + checkedIn
          + '\n' + "Book.java Genre: " + genre + "\n\n";
 }


}



