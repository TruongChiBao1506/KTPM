package iuh.fit.se.library.decorator;

public class PriorityLoan extends LoanDecorator {
    private String priorityLevel; // "High", "Medium", "Low"

    public PriorityLoan(Loan loan, String priorityLevel) {
        super(loan);
        this.priorityLevel = priorityLevel;
    }

    @Override
    public double calculateFee() {
        double baseFee = decoratedLoan.calculateFee();

        // Phí ưu tiên dựa trên mức độ
        switch(priorityLevel) {
            case "High":
                return baseFee + 50000;
            case "Medium":
                return baseFee + 30000;
            case "Low":
                return baseFee + 10000;
            default:
                return baseFee;
        }
    }

    @Override
    public String getDescription() {
        return decoratedLoan.getDescription() + " with " + priorityLevel + " priority";
    }
}
