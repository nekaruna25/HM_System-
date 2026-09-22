public class Service {
    private String serviceId;
    private String serviceName;
    private double price;
    private String description;
    private boolean active;

    public Service(String serviceId, String serviceName, double price,
                   String description, boolean active) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.price = price;
        this.description = description;
        this.active = active;
    }

    public String getServiceId() { return serviceId; }
    public String getServiceName() { return serviceName; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public boolean isActive() { return active; }

    public void setPrice(double price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
    public void setActive(boolean active) { this.active = active; }

    public String toFileString() {
        return FileManager.clean(serviceId) + "|" +
               FileManager.clean(serviceName) + "|" +
               price + "|" +
               FileManager.clean(description) + "|" +
               active;
    }

    public static Service fromFileString(String line) {
        String[] p = FileManager.split(line, 5);
        if (p.length < 5) return null;
        try {
            return new Service(p[0], p[1], Double.parseDouble(p[2]),
                    p[3], Boolean.parseBoolean(p[4]));
        } catch (Exception e) {
            return null;
        }
    }

    public void display() {
        System.out.printf("%-8s %-20s %-12.2f %-8s%n",
                serviceId, serviceName, price, active ? "Active" : "Inactive");
        System.out.println("   " + description);
    }
}
