package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Book> books = new ArrayList<>();
	private List<Member> members = new ArrayList<>();
	private Map<String, Book> borrowedBooks = new HashMap<>();

	public void saveData() throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("library.data"))) {
			oos.writeObject(books);
			oos.writeObject(members);
			oos.writeObject(borrowedBooks);
		} catch (IOException e) {
			System.err.println("Error saving data: " + e.getMessage());
			throw e;
		}
	}

	public void loadData() throws IOException, ClassNotFoundException {
		File file = new File("library.data");
		if (!file.exists()) {
			System.out.println("No data file found. Starting with an empty library.");
			return;
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			books = (List<Book>) ois.readObject();
			members = (List<Member>) ois.readObject();
			borrowedBooks = (Map<String, Book>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error loading data: " + e.getMessage());
			throw e;
		}
	}

	public void addBook(Book book) {
		if (!books.contains(book)) {
			books.add(book);
			System.out.println("Book added successfully.");
		} else {
			System.out.println("Duplicate book is not allowed.");
		}
	}

	public void removeBook(String isbn) {
		boolean bookExists = books.stream().anyMatch(book -> book.getIsbn().equals(isbn));
		if (bookExists) {
			books.removeIf(book -> book.getIsbn().equals(isbn));
			System.out.println("Book removed successfully.");
		} else {
			System.out.println("Book not found.");
		}
	}

	public Book searchBook(String title) {
		if (books.isEmpty()) {
			System.out.println("Books list is empty.");
			return null;
		}
		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				return book;
			}
		}
		System.out.println("Book not found.");
		return null;
	}

	public void displayBooks() {
		if (books.isEmpty()) {
			System.out.println("No books found.");
		} else {
			for (Book book : books) {
				System.out.println(book);
			}
		}
	}

	public void addMember(Member member) {
		if (members.stream().anyMatch(m -> m.getMemberId().equals(member.getMemberId()))) {
			System.out.println("Duplicate member ID is not allowed.");
		} else {
			members.add(member);
			System.out.println("Member added successfully.");
		}
	}

	public void removeMember(String memberId) {
		if (members.stream().anyMatch(member -> member.getMemberId().equals(memberId))) {
			members.removeIf(member -> member.getMemberId().equals(memberId));
			System.out.println("Member removed successfully.");
		} else {
			System.out.println("Member not found.");
		}
	}

	public Member searchMember(String name) {
		if (members.isEmpty()) {
			System.out.println("Members list is empty.");
			return null;
		}
		for (Member member : members) {
			if (member.getName().equalsIgnoreCase(name)) {
				return member;
			}
		}
		System.out.println("Member not found.");
		return null;
	}

	public void displayMembers() {
		if (members.isEmpty()) {
			System.out.println("No members found.");
		} else {
			for (Member member : members) {
				System.out.println(member);
			}
		}
	}

	public void borrowBook(String isbn, String memberId) {
		if (borrowedBooks.containsKey(memberId)) {
			System.out.println("This member already has a borrowed book.");
			return;
		}
		Book book = books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElse(null);
		if (book == null) {
			System.out.println("Book not found.");
			return;
		}
		Member member = members.stream().filter(m -> m.getMemberId().equals(memberId)).findFirst().orElse(null);
		if (member == null) {
			System.out.println("Member not found.");
			return;
		}
		borrowedBooks.put(memberId, book);
		books.remove(book);
		System.out.println("Book borrowed successfully.");
	}

	public void returnBook(String isbn, String memberId) {
		Book borrowedBook = borrowedBooks.get(memberId);
		if (borrowedBook == null || !borrowedBook.getIsbn().equals(isbn)) {
			System.out.println("Borrow record not found for this member and book.");
			return;
		}
		borrowedBooks.remove(memberId);
		books.add(borrowedBook);
		System.out.println("Book returned successfully.");
	}
}
