package iuh.fit.se.library.decorator;

import iuh.fit.se.library.observer.User;

import java.time.LocalDate;

public abstract class LoanDecorator implements Loan {
    protected Loan decoratedLoan;

    public LoanDecorator(Loan loan) {
        this.decoratedLoan = loan;
    }

    @Override
    public String getBookId() {
        return decoratedLoan.getBookId();
    }

    @Override
    public User getBorrower() {
        return decoratedLoan.getBorrower();
    }

    @Override
    public LocalDate getDueDate() {
        return decoratedLoan.getDueDate();
    }

    @Override
    public double calculateFee() {
        return decoratedLoan.calculateFee();
    }

    @Override
    public String getDescription() {
        return decoratedLoan.getDescription();
    }
}
