package iuh.fit.se.library.decorator;

public class ExtendedLoan extends LoanDecorator{
    private int extraDays;

    public ExtendedLoan(Loan loan, int extraDays) {
        super(loan);
        this.extraDays = extraDays;

        // Extend the due date
        if (decoratedLoan instanceof BasicLoan) {
            BasicLoan basicLoan = (BasicLoan) decoratedLoan;
            basicLoan.setDueDate(basicLoan.getDueDate().plusDays(extraDays));
        }
    }

    @Override
    public double calculateFee() {
        // Thêm phí gia hạn: 1,000 đồng mỗi ngày
        return decoratedLoan.calculateFee() + (extraDays * 1000);
    }

    @Override
    public String getDescription() {
        return decoratedLoan.getDescription() + " with " + extraDays + " days extension";
    }
}
