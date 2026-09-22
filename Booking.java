public class Booking {
    private String bookingId;
    private String customerId;
    private String roomNumber;
    private String checkInDate;
    private String checkOutDate;
    private int guests;
    private String status;
    private double roomCharge;
    private double serviceCharge;
    private double discount;
    private double totalAmount;

    public Booking(String bookingId, String customerId, String roomNumber,
                    String checkInDate, String checkOutDate, int guests,
                    String status, double roomCharge, double serviceCharge,
                    double discount, double totalAmount) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guests = guests;
        this.status = status;
        this.roomCharge = roomCharge;
        this.serviceCharge = serviceCharge;
        this.discount = discount;
        this.totalAmount = totalAmount;
    }

    public String getBookingId() { return bookingId; }
    public String getCustomerId() { return customerId; }
    public String getRoomNumber() { return roomNumber; }
    public String getCheckInDate() { return checkInDate; }
    public String getCheckOutDate() { return checkOutDate; }
    public int getGuests() { return guests; }
    public String getStatus() { return status; }
    public double getRoomCharge() { return roomCharge; }
    public double getServiceCharge() { return serviceCharge; }
    public double getDiscount() { return discount; }
    public double getTotalAmount() { return totalAmount; }

    public void setStatus(String status) { this.status = status; }
    public void setServiceCharge(double serviceCharge) { this.serviceCharge = serviceCharge; }
    public void setDiscount(double discount) { this.discount = discount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String toFileString() {
        return FileManager.clean(bookingId) + "|" +
               FileManager.clean(customerId) + "|" +
               FileManager.clean(roomNumber) + "|" +
               FileManager.clean(checkInDate) + "|" +
               FileManager.clean(checkOutDate) + "|" +
               guests + "|" +
               FileManager.clean(status) + "|" +
               roomCharge + "|" +
               serviceCharge + "|" +
               discount + "|" +
               totalAmount;
    }

    public static Booking fromFileString(String line) {
        String[] p = FileManager.split(line, 11);
        if (p.length < 11) return null;
        try {
            return new Booking(
                p[0], p[1], p[2], p[3], p[4],
                Integer.parseInt(p[5]), p[6],
                Double.parseDouble(p[7]), Double.parseDouble(p[8]),
                Double.parseDouble(p[9]), Double.parseDouble(p[10])
            );
        } catch (Exception e) {
            return null;
        }
    }

    public void display() {
        System.out.println("------------------------------------------------");
        System.out.println("Booking ID    : " + bookingId);
        System.out.println("Customer ID   : " + customerId);
        System.out.println("Room Number   : " + roomNumber);
        System.out.println("Check-in      : " + checkInDate);
        System.out.println("Check-out     : " + checkOutDate);
        System.out.println("Guests        : " + guests);
        System.out.println("Status        : " + status);
        System.out.printf("Room Charge   : %.2f%n", roomCharge);
        System.out.printf("Service Charge: %.2f%n", serviceCharge);
        System.out.printf("Discount      : %.2f%n", discount);
        System.out.printf("Total Amount  : %.2f%n", totalAmount);
    }
}
