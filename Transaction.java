public class Transaction {
    private double amount;
    private String  originatingAccountId;
    private String  resultingAccountId;
    private String transactionReason;
    private TransactionType transactionType;
    private double feeAmount; 

    public Transaction(double amount,String  originatingAccountId,String  resultingAccountId, String transactionReason,TransactionType transactionType,double feeAmount){
        this.amount=amount;
        this.originatingAccountId=originatingAccountId;
        this.resultingAccountId=resultingAccountId;
        this.transactionReason=transactionReason;
        this.transactionType = transactionType;
        this.feeAmount = feeAmount;

    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount){
        this.amount=amount;
    }
    public String  getOriginatingAccountId() {
        return originatingAccountId;
    }
    public void setOriginatingAccountId(String originatingAccountId){
        this.originatingAccountId=originatingAccountId;
    }
    public String  getResultingAccountId() {
        return resultingAccountId;
    }
    public void setResultingAccountId(String resultingAccountId){
        this.resultingAccountId=resultingAccountId;
    }
    public String getTransactionReason() {
        return transactionReason;
    }
    public void setTransactionReason(String transactionReason) {
        this.transactionReason = transactionReason;
    }
    public TransactionType getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
    public double getFeeAmount() {
        return feeAmount;
    }
    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }
    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", originatingAccountId='" + originatingAccountId + '\'' +
                ", resultingAccountId='" + resultingAccountId + '\'' +
                ", transactionReason='" + transactionReason + '\'' +
                ", transactionType=" + transactionType +
                '}';
    }
    

}
 enum TransactionType {
    TRANSFER,
    WITHDRAWAL,
    DEPOSIT
}
