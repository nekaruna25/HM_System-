public class Customer {
    private String customerId;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String nationality;
    private String idNumber;

    public Customer(String customerId, String name, String phone, String email,
                    String address, String nationality, String idNumber) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.nationality = nationality;
        this.idNumber = idNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String toFileString() {
        return FileManager.clean(customerId) + "|" +
               FileManager.clean(name) + "|" +
               FileManager.clean(phone) + "|" +
               FileManager.clean(email) + "|" +
               FileManager.clean(address) + "|" +
               FileManager.clean(nationality) + "|" +
               FileManager.clean(idNumber);
    }

    public static Customer fromFileString(String line) {
        String[] p = FileManager.split(line, 7);
        if (p.length < 7) return null;
        return new Customer(p[0], p[1], p[2], p[3], p[4], p[5], p[6]);
    }

    public void display() {
        System.out.println("-----------------------------------------------");
        System.out.println("Customer ID : " + customerId);
        System.out.println("Name        : " + name);
        System.out.println("Phone       : " + phone);
        System.out.println("Email       : " + email);
        System.out.println("Address     : " + address);
        System.out.println("Nationality : " + nationality);
        System.out.println("ID Number   : " + idNumber);
    }
}
