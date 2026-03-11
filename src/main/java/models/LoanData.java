package models;

public class LoanData {
    public String loanType;
    public int amount;
    public int tenure;

    // Constructor: Ye zaroori hai taaki aap Test class mein data bhej saken
    public LoanData(String loanType, int amount, int tenure) {
        this.loanType = loanType;
        this.amount = amount;
        this.tenure = tenure;
    }
}