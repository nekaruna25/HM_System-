public class Payment {
    private String paymentId;
    private String bookingId;
    private String customerId;
    private double amount;
    private String method;
    private String date;
    private String status;
    private String reference;

    public Payment(String paymentId, String bookingId, String customerId,
                   double amount, String method, String date,
                   String status, String reference) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.amount = amount;
        this.method = method;
        this.date = date;
        this.status = status;
        this.reference = reference;
    }

    public String getPaymentId() { return paymentId; }
    public String getBookingId() { return bookingId; }
    public String getCustomerId() { return customerId; }
    public double getAmount() { return amount; }
    public String getMethod() { return method; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
    public String getReference() { return reference; }

    public String toFileString() {
        return FileManager.clean(paymentId) + "|" +
               FileManager.clean(bookingId) + "|" +
               FileManager.clean(customerId) + "|" +
               amount + "|" +
               FileManager.clean(method) + "|" +
               FileManager.clean(date) + "|" +
               FileManager.clean(status) + "|" +
               FileManager.clean(reference);
    }

    public static Payment fromFileString(String line) {
        String[] p = FileManager.split(line, 8);
        if (p.length < 8) return null;
        try {
            return new Payment(p[0], p[1], p[2], Double.parseDouble(p[3]),
                    p[4], p[5], p[6], p[7]);
        } catch (Exception e) {
            return null;
        }
    }

    public void display() {
        System.out.println("-----------------------------------------------");
        System.out.println("Payment ID : " + paymentId);
        System.out.println("Booking ID : " + bookingId);
        System.out.println("Customer   : " + customerId);
        System.out.printf("Amount     : %.2f%n", amount);
        System.out.println("Method     : " + method);
        System.out.println("Date       : " + date);
        System.out.println("Status     : " + status);
        System.out.println("Reference  : " + reference);
    }
}
