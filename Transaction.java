public class Transaction {
    private String transactionId;
    private String bookingId;
    private String type;
    private double amount;
    private String date;
    private String description;

    public Transaction(String transactionId, String bookingId, String type,
                       double amount, String date, String description) {
        this.transactionId = transactionId;
        this.bookingId = bookingId;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public String getTransactionId() { return transactionId; }
    public String getBookingId() { return bookingId; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
    public String getDescription() { return description; }

    public String toFileString() {
        return FileManager.clean(transactionId) + "|" +
               FileManager.clean(bookingId) + "|" +
               FileManager.clean(type) + "|" +
               amount + "|" +
               FileManager.clean(date) + "|" +
               FileManager.clean(description);
    }

    public static Transaction fromFileString(String line) {
        String[] p = FileManager.split(line, 6);
        if (p.length < 6) return null;
        try {
            return new Transaction(p[0], p[1], p[2],
                    Double.parseDouble(p[3]), p[4], p[5]);
        } catch (Exception e) {
            return null;
        }
    }

    public void display() {
        System.out.printf("%-10s %-10s %-14s %-12.2f %-14s %s%n",
                transactionId, bookingId, type, amount, date, description);
    }
}
