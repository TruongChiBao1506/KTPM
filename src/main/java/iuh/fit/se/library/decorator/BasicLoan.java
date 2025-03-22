package iuh.fit.se.library.decorator;

import iuh.fit.se.library.observer.User;

import java.time.LocalDate;

public class BasicLoan implements Loan {

    private String bookId;
    private User borrower;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public BasicLoan(String bookId, User borrower) {
        this.bookId = bookId;
        this.borrower = borrower;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(14); // 2 tuần mặc định
    }

    @Override
    public String getBookId() { return bookId; }

    @Override
    public User getBorrower() { return borrower; }

    @Override
    public LocalDate getDueDate() { return dueDate; }

    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    @Override
    public double calculateFee() {
        // Phí cơ bản là 0
        return 0.0;
    }

    @Override
    public String getDescription() {
        return "Basic loan";
    }
}
