package iuh.fit.se.library.decorator;

public class SpecialEditionLoan extends LoanDecorator {
    private String editionType; // ví dụ: "Braille", "Large Print", "Translated"

    public SpecialEditionLoan(Loan loan, String editionType) {
        super(loan);
        this.editionType = editionType;
    }

    @Override
    public double calculateFee() {
        // Phí thêm cho phiên bản đặc biệt
        return decoratedLoan.calculateFee() + 20000;
    }

    @Override
    public String getDescription() {
        return decoratedLoan.getDescription() + " with special " + editionType + " edition";
    }
}
