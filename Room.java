public class Room {
    private String roomNumber;
    private String roomType;
    private double pricePerNight;
    private String status;
    private int floor;
    private int capacity;
    private String description;

    public Room(String roomNumber, String roomType, double pricePerNight,
                String status, int floor, int capacity, String description) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.status = status;
        this.floor = floor;
        this.capacity = capacity;
        this.description = description;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFloor() {
        return floor;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return status.equalsIgnoreCase("Available");
    }

    public String toFileString() {
        return FileManager.clean(roomNumber) + "|" +
               FileManager.clean(roomType) + "|" +
               pricePerNight + "|" +
               FileManager.clean(status) + "|" +
               floor + "|" +
               capacity + "|" +
               FileManager.clean(description);
    }

    public static Room fromFileString(String line) {
        String[] p = FileManager.split(line, 7);
        if (p.length < 7) return null;
        try {
            return new Room(
                p[0], p[1], Double.parseDouble(p[2]),
                p[3], Integer.parseInt(p[4]),
                Integer.parseInt(p[5]), p[6]
            );
        } catch (Exception e) {
            return null;
        }
    }

    public void display() {
        System.out.printf("%-8s %-12s %-12.2f %-12s %-7d %-9d%n",
                roomNumber, roomType, pricePerNight, status, floor, capacity);
        System.out.println("   Description: " + description);
    }
}
