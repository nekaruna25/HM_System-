public class Employee {
    private String employeeId;
    private String name;
    private String position;
    private String phone;
    private String email;
    private double salary;
    private String shift;
    private String status;

    public Employee(String employeeId, String name, String position,
                    String phone, String email, double salary,
                    String shift, String status) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.phone = phone;
        this.email = email;
        this.salary = salary;
        this.shift = shift;
        this.status = status;
    }

    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public double getSalary() { return salary; }
    public String getShift() { return shift; }
    public String getStatus() { return status; }

    public void setPosition(String position) { this.position = position; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setShift(String shift) { this.shift = shift; }
    public void setStatus(String status) { this.status = status; }

    public String toFileString() {
        return FileManager.clean(employeeId) + "|" +
               FileManager.clean(name) + "|" +
               FileManager.clean(position) + "|" +
               FileManager.clean(phone) + "|" +
               FileManager.clean(email) + "|" +
               salary + "|" +
               FileManager.clean(shift) + "|" +
               FileManager.clean(status);
    }

    public static Employee fromFileString(String line) {
        String[] p = FileManager.split(line, 8);
        if (p.length < 8) return null;
        try {
            return new Employee(p[0], p[1], p[2], p[3], p[4],
                    Double.parseDouble(p[5]), p[6], p[7]);
        } catch (Exception e) {
            return null;
        }
    }

    public void display() {
        System.out.printf("%-8s %-20s %-16s %-15s %-12.2f %-10s %-10s%n",
                employeeId, name, position, phone, salary, shift, status);
    }
}
