package iuh.fit.se.library.decorator;

import iuh.fit.se.library.observer.User;

import java.time.LocalDate;

public interface Loan {
    String getBookId();
    User getBorrower();
    LocalDate getDueDate();
    double calculateFee();
    String getDescription();
}
