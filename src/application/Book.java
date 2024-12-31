package application;

import java.io.Serializable;

public class Book implements Serializable{
	
	private String title;
	private String author;
	private String isbn;
	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", isbn=" + isbn + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Book(String title, String author, String isbn) {
		super();
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}
	public Book() {
		// TODO Auto-generated constructor stub
	}
}
