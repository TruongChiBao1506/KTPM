package iuh.fit.se.library;

import iuh.fit.se.library.decorator.*;
import iuh.fit.se.library.factory.AudioBookFactory;
import iuh.fit.se.library.factory.BookFactory;
import iuh.fit.se.library.factory.EBookFactory;
import iuh.fit.se.library.factory.PhysicalBookFactory;
import iuh.fit.se.library.model.Book;
import iuh.fit.se.library.observer.LibraryStaff;
import iuh.fit.se.library.observer.User;
import iuh.fit.se.library.singleton.Library;
import iuh.fit.se.library.strategy.AuthorSearchStrategy;
import iuh.fit.se.library.strategy.TitleSearchStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // (Singleton)
        Library library = Library.getInstance();

        // Tạo một số người dùng và nhân viên
        User user1 = new User("U001", "Nguyễn Văn An", "an@example.com");
        User user2 = new User("U002", "Trần Thị Bình", "binh@example.com");
        LibraryStaff staff1 = new LibraryStaff("S001", "Lê Văn Chung", "Librarian");

        // Đăng ký người dùng và nhân viên làm Observer
        library.addObserver(user1);
        library.addObserver(user2);
        library.addObserver(staff1);

        // Tạo các Factory để tạo sách
        BookFactory physicalBookFactory = new PhysicalBookFactory();
        BookFactory eBookFactory = new EBookFactory();
        BookFactory audioBookFactory = new AudioBookFactory();

        // Tạo và thêm sách mới
        Map<String, Object> physicalBookInfo = new HashMap<>();
        physicalBookInfo.put("shelfLocation", "A12-B5");
        Book book1 = physicalBookFactory.createBook("B001", "Đắc Nhân Tâm", "Dale Carnegie", "Self-help", physicalBookInfo);
        library.addBook(book1);

        Map<String, Object> eBookInfo = new HashMap<>();
        eBookInfo.put("fileFormat", "PDF");
        eBookInfo.put("fileSizeMB", 5);
        Book book2 = eBookFactory.createBook("B002", "Nhà Giả Kim", "Paulo Coelho", "Fiction", eBookInfo);
        library.addBook(book2);

        Map<String, Object> audioBookInfo = new HashMap<>();
        audioBookInfo.put("durationMinutes", 320);
        audioBookInfo.put("narrator", "Nguyễn Văn Diễn");
        Book book3 = audioBookFactory.createBook("B003", "Người Giàu Có Nhất Thành Babylon", "George S. Clason", "Finance", audioBookInfo);
        library.addBook(book3);

        // Tìm kiếm sách với các chiến lược khác nhau
        System.out.println("\n--- Tìm kiếm sách theo tên ---");
        List<Book> titleResults = library.searchBooks(new TitleSearchStrategy(), "nhân");
        for (Book book : titleResults) {
            System.out.println(book);
        }

        System.out.println("\n--- Tìm kiếm sách theo tác giả ---");
        List<Book> authorResults = library.searchBooks(new AuthorSearchStrategy(), "Paulo");
        for (Book book : authorResults) {
            System.out.println(book);
        }

        // Mượn sách với các loại Decorator khác nhau
        System.out.println("\n--- Mượn sách thông thường ---");
        Book borrowedBook = library.borrowBook("B001", user1);
        Loan basicLoan = new BasicLoan("B001", user1);
        System.out.println("Mượn sách: " + borrowedBook.getTitle());
        System.out.println("Mô tả: " + basicLoan.getDescription());
        System.out.println("Phí: " + basicLoan.calculateFee() + " đồng");
        System.out.println("Ngày hẹn trả: " + basicLoan.getDueDate());

        System.out.println("\n--- Mượn sách với gia hạn ---");
        Loan extendedLoan = new ExtendedLoan(new BasicLoan("B002", user2), 10);
        System.out.println("Mô tả: " + extendedLoan.getDescription());
        System.out.println("Phí: " + extendedLoan.calculateFee() + " đồng");
        System.out.println("Ngày hẹn trả: " + extendedLoan.getDueDate());

        System.out.println("\n--- Mượn sách phiên bản đặc biệt với độ ưu tiên cao ---");
        Loan specialLoan = new SpecialEditionLoan(
                new PriorityLoan(
                        new BasicLoan("B003", user1), "High"), "Large Print");
        System.out.println("Mô tả: " + specialLoan.getDescription());
        System.out.println("Phí: " + specialLoan.calculateFee() + " đồng");

        // Trả sách
        library.returnBook("B001");
        System.out.println("\nSách " + borrowedBook.getTitle() + " đã được trả");
    }
}
