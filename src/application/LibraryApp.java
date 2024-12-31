package application;

import java.io.IOException;
import java.util.Scanner;

public class LibraryApp {

	public static void main(String[] args) throws InterruptedException {
		Library library = new Library();
		Scanner scanner = new Scanner(System.in);
		try {
			library.loadData();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("No previous data Found");
		}
		while (true) {
			System.out.println("Library Menu:");
			System.out.println("1. Add Book");
			System.out.println("2. Remove Book");
			System.out.println("3. Search Book");
			System.out.println("4. Display Books");
			System.out.println("5. Add Member");
			System.out.println("6. Remove Member");
			System.out.println("7. Search Member");
			System.out.println("8. Display Members");
			System.out.println("9. Borrow Book");
			System.out.println("10. Return Book");
			System.out.println("11. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				System.out.print("Enter book title: ");
				String title = scanner.nextLine();
				System.out.print("Enter book author: ");
				String author = scanner.nextLine();
				System.out.print("Enter book ISBN: ");
				String isbn = scanner.nextLine();
				library.addBook(new Book(title, author, isbn));
				break;
			case 2:
				System.out.print("Enter book ISBN to remove: ");
				isbn = scanner.nextLine();
				library.removeBook(isbn);
				break;
			case 3:
				System.out.print("Enter book title to search: ");
				title = scanner.nextLine();
				Book book = library.searchBook(title);
				if (book != null) {
					System.out.println("Book found: " + book);
				} else {
					System.out.println("Book not found.");
				}
				break;
			case 4:
				library.displayBooks();
				break;
			case 5:
				System.out.print("Enter member name: ");
				String name = scanner.nextLine();
				System.out.print("Enter member ID: ");
				String memberId = scanner.nextLine();
				library.addMember(new Member(name, memberId));
				break;
			case 6:
				System.out.print("Enter member ID to remove: ");
				memberId = scanner.nextLine();
				library.removeMember(memberId);
				break;
			case 7:
				System.out.print("Enter member name to search: ");
				name = scanner.nextLine();
				Member member = library.searchMember(name);
				if (member != null) {
					System.out.println("Member found: " + member);
				} else {
					System.out.println("Member not found.");
				}
				break;
			case 8:
				library.displayMembers();
				break;
			case 9:
				System.out.print("Enter book ISBN to borrow: ");
				isbn = scanner.nextLine();
				System.out.print("Enter member ID: ");
				memberId = scanner.nextLine();
				library.borrowBook(isbn, memberId);
				break;
			case 10:
				System.out.print("Enter book ISBN to return: ");
				isbn = scanner.nextLine();
				System.out.print("Enter member ID: ");
				memberId = scanner.nextLine();
				library.returnBook(isbn, memberId);
				break;
			case 11:
				try {
					library.saveData();
				} catch (IOException e) {
					System.out.println("Error saving data.");
				}
				System.out.print("Exiting");
				for (int i = 0; i < 3; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.print(".");
				}
				System.out.println("Exit is completed");
				return;
			default:
				System.out.println("Invalid choice.");
			}
		}
	}
}
