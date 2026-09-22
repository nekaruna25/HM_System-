import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelSystem {
    private final List<Customer> customers = new ArrayList<>();
    private final List<Room> rooms = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();
    private final List<Service> services = new ArrayList<>();
    private final List<Payment> payments = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();
    private final List<Transaction> transactions = new ArrayList<>();

    private int customerCounter = 1001;
    private int bookingCounter = 2001;
    private int paymentCounter = 3001;
    private int transactionCounter = 4001;
    private int employeeCounter = 5001;
    private int serviceCounter = 6001;

    public void printMainMenu() {
        System.out.println();
        System.out.println("=============== MAIN MENU ===============");
        System.out.println("1. Customer Management");
        System.out.println("2. Room Management");
        System.out.println("3. Booking Management");
        System.out.println("4. Hotel Service Management");
        System.out.println("5. Payment Management");
        System.out.println("6. Employee Management");
        System.out.println("7. Search");
        System.out.println("8. Reports and Statements");
        System.out.println("9. Save Data");
        System.out.println("0. Exit");
        System.out.println("=========================================");
    }

    public void loadAllData() {
        loadCustomers();
        loadRooms();
        loadBookings();
        loadServices();
        loadPayments();
        loadEmployees();
        loadTransactions();
        createDefaultRooms();
        createDefaultServices();
        createDefaultEmployees();
    }

    public void saveAllData() {
        saveCustomers();
        saveRooms();
        saveBookings();
        saveServices();
        savePayments();
        saveEmployees();
        saveTransactions();
    }

    private void loadCustomers() {
        customers.clear();
        for (String line : FileManager.readLines("customers.txt")) {
            Customer c = Customer.fromFileString(line);
            if (c != null) customers.add(c);
        }
        updateCustomerCounter();
    }

    private void loadRooms() {
        rooms.clear();
        for (String line : FileManager.readLines("rooms.txt")) {
            Room r = Room.fromFileString(line);
            if (r != null) rooms.add(r);
        }
    }

    private void loadBookings() {
        bookings.clear();
        for (String line : FileManager.readLines("bookings.txt")) {
            Booking b = Booking.fromFileString(line);
            if (b != null) bookings.add(b);
        }
        updateBookingCounter();
    }

    private void loadServices() {
        services.clear();
        for (String line : FileManager.readLines("services.txt")) {
            Service s = Service.fromFileString(line);
            if (s != null) services.add(s);
        }
        updateServiceCounter();
    }

    private void loadPayments() {
        payments.clear();
        for (String line : FileManager.readLines("payments.txt")) {
            Payment p = Payment.fromFileString(line);
            if (p != null) payments.add(p);
        }
        updatePaymentCounter();
    }

    private void loadEmployees() {
        employees.clear();
        for (String line : FileManager.readLines("employees.txt")) {
            Employee e = Employee.fromFileString(line);
            if (e != null) employees.add(e);
        }
        updateEmployeeCounter();
    }

    private void loadTransactions() {
        transactions.clear();
        for (String line : FileManager.readLines("transactions.txt")) {
            Transaction t = Transaction.fromFileString(line);
            if (t != null) transactions.add(t);
        }
        updateTransactionCounter();
    }

    private void saveCustomers() {
        List<String> lines = new ArrayList<>();
        for (Customer c : customers) lines.add(c.toFileString());
        FileManager.writeLines("customers.txt", lines);
    }

    private void saveRooms() {
        List<String> lines = new ArrayList<>();
        for (Room r : rooms) lines.add(r.toFileString());
        FileManager.writeLines("rooms.txt", lines);
    }

    private void saveBookings() {
        List<String> lines = new ArrayList<>();
        for (Booking b : bookings) lines.add(b.toFileString());
        FileManager.writeLines("bookings.txt", lines);
    }

    private void saveServices() {
        List<String> lines = new ArrayList<>();
        for (Service s : services) lines.add(s.toFileString());
        FileManager.writeLines("services.txt", lines);
    }

    private void savePayments() {
        List<String> lines = new ArrayList<>();
        for (Payment p : payments) lines.add(p.toFileString());
        FileManager.writeLines("payments.txt", lines);
    }

    private void saveEmployees() {
        List<String> lines = new ArrayList<>();
        for (Employee e : employees) lines.add(e.toFileString());
        FileManager.writeLines("employees.txt", lines);
    }

    private void saveTransactions() {
        List<String> lines = new ArrayList<>();
        for (Transaction t : transactions) lines.add(t.toFileString());
        FileManager.writeLines("transactions.txt", lines);
    }

    private void updateCustomerCounter() {
        for (Customer c : customers) {
            customerCounter = Math.max(customerCounter, extractNumber(c.getCustomerId(), 1001) + 1);
        }
    }

    private void updateBookingCounter() {
        for (Booking b : bookings) {
            bookingCounter = Math.max(bookingCounter, extractNumber(b.getBookingId(), 2001) + 1);
        }
    }

    private void updatePaymentCounter() {
        for (Payment p : payments) {
            paymentCounter = Math.max(paymentCounter, extractNumber(p.getPaymentId(), 3001) + 1);
        }
    }

    private void updateTransactionCounter() {
        for (Transaction t : transactions) {
            transactionCounter = Math.max(transactionCounter, extractNumber(t.getTransactionId(), 4001) + 1);
        }
    }

    private void updateEmployeeCounter() {
        for (Employee e : employees) {
            employeeCounter = Math.max(employeeCounter, extractNumber(e.getEmployeeId(), 5001) + 1);
        }
    }

    private void updateServiceCounter() {
        for (Service s : services) {
            serviceCounter = Math.max(serviceCounter, extractNumber(s.getServiceId(), 6001) + 1);
        }
    }

    private int extractNumber(String text, int fallback) {
        try {
            return Integer.parseInt(text.replaceAll("\\D", ""));
        } catch (Exception e) {
            return fallback;
        }
    }

    private void createDefaultRooms() {
        if (!rooms.isEmpty()) return;

        rooms.add(new Room("101", "Single", 2500, "Available", 1, 1,
                "Single bed with basic facilities"));
        rooms.add(new Room("102", "Single", 2500, "Available", 1, 1,
                "Single bed with basic facilities"));
        rooms.add(new Room("201", "Double", 4000, "Available", 2, 2,
                "Double bed suitable for two guests"));
        rooms.add(new Room("202", "Double", 4000, "Available", 2, 2,
                "Double bed with balcony"));
        rooms.add(new Room("301", "Deluxe", 6000, "Available", 3, 3,
                "Deluxe room with premium facilities"));
        rooms.add(new Room("302", "Deluxe", 6000, "Available", 3, 3,
                "Deluxe room with city view"));
        rooms.add(new Room("401", "Suite", 9000, "Available", 4, 4,
                "Large suite with living area"));
        saveRooms();
    }

    private void createDefaultServices() {
        if (!services.isEmpty()) return;

        services.add(new Service("S001", "Breakfast", 500,
                "Breakfast for one guest", true));
        services.add(new Service("S002", "Laundry", 300,
                "Laundry service per order", true));
        services.add(new Service("S003", "Airport Pickup", 1500,
                "Airport transportation service", true));
        services.add(new Service("S004", "Extra Bed", 1000,
                "Extra bed for one night", true));
        services.add(new Service("S005", "Room Cleaning", 400,
                "Additional room cleaning", true));
        saveServices();
    }

    private void createDefaultEmployees() {
        if (!employees.isEmpty()) return;

        employees.add(new Employee("E001", "System Manager", "Manager",
                "9800000000", "manager@hotel.com", 45000, "Morning", "Active"));
        employees.add(new Employee("E002", "Reception Staff", "Receptionist",
                "9810000000", "reception@hotel.com", 28000, "Morning", "Active"));
        employees.add(new Employee("E003", "Housekeeping Staff", "Housekeeper",
                "9820000000", "housekeeping@hotel.com", 24000, "Day", "Active"));
        saveEmployees();
    }

    // ================= CUSTOMER MENU =================

    public void customerMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("========== CUSTOMER MANAGEMENT ==========");
            System.out.println("1. Add Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Search Customer");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (scanner.nextLine().trim()) {
                case "1": addCustomer(scanner); break;
                case "2": viewCustomers(); break;
                case "3": searchCustomer(scanner); break;
                case "4": updateCustomer(scanner); break;
                case "5": deleteCustomer(scanner); break;
                case "0": back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void addCustomer(Scanner scanner) {
        System.out.println("\n--- Add New Customer ---");
        String name = InputHelper.readRequired(scanner, "Name: ");
        String phone = InputHelper.readRequired(scanner, "Phone: ");
        String email = InputHelper.readRequired(scanner, "Email: ");
        String address = InputHelper.readRequired(scanner, "Address: ");
        String nationality = InputHelper.readRequired(scanner, "Nationality: ");
        String id = InputHelper.readRequired(scanner, "ID/Passport Number: ");

        if (findCustomerByIdNumber(id) != null) {
            System.out.println("A customer with this ID number already exists.");
            return;
        }

        String customerId = "C" + customerCounter++;
        Customer customer = new Customer(customerId, name, phone, email,
                address, nationality, id);
        customers.add(customer);
        saveCustomers();

        System.out.println("Customer added successfully.");
        System.out.println("Customer ID: " + customerId);
    }

    private void viewCustomers() {
        System.out.println("\n========== CUSTOMER LIST ==========");
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        for (Customer c : customers) {
            c.display();
        }
        System.out.println("Total customers: " + customers.size());
    }

    private void searchCustomer(Scanner scanner) {
        System.out.println("\n--- Search Customer ---");
        System.out.println("1. Customer ID");
        System.out.println("2. Name");
        System.out.println("3. Phone");
        System.out.print("Search by: ");
        String choice = scanner.nextLine().trim();

        String key = InputHelper.readRequired(scanner, "Enter search value: ");
        boolean found = false;

        for (Customer c : customers) {
            boolean match = false;

            if (choice.equals("1")) {
                match = c.getCustomerId().equalsIgnoreCase(key);
            } else if (choice.equals("2")) {
                match = c.getName().toLowerCase().contains(key.toLowerCase());
            } else if (choice.equals("3")) {
                match = c.getPhone().contains(key);
            } else {
                System.out.println("Invalid search option.");
                return;
            }

            if (match) {
                c.display();
                found = true;
            }
        }

        if (!found) System.out.println("No customer found.");
    }

    private void updateCustomer(Scanner scanner) {
        String id = InputHelper.readRequired(scanner, "Enter customer ID: ");
        Customer c = findCustomer(id);

        if (c == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("Press Enter to keep the old value.");
        c.setName(InputHelper.readOptional(scanner, "Name [" + c.getName() + "]: ", c.getName()));
        c.setPhone(InputHelper.readOptional(scanner, "Phone [" + c.getPhone() + "]: ", c.getPhone()));
        c.setEmail(InputHelper.readOptional(scanner, "Email [" + c.getEmail() + "]: ", c.getEmail()));
        c.setAddress(InputHelper.readOptional(scanner, "Address [" + c.getAddress() + "]: ", c.getAddress()));
        c.setNationality(InputHelper.readOptional(scanner,
                "Nationality [" + c.getNationality() + "]: ", c.getNationality()));

        saveCustomers();
        System.out.println("Customer updated successfully.");
    }

    private void deleteCustomer(Scanner scanner) {
        String id = InputHelper.readRequired(scanner, "Enter customer ID: ");
        Customer c = findCustomer(id);

        if (c == null) {
            System.out.println("Customer not found.");
            return;
        }

        if (hasActiveBookingForCustomer(id)) {
            System.out.println("Cannot delete customer with an active booking.");
            return;
        }

        if (InputHelper.yesNo(scanner, "Delete " + c.getName() + "?")) {
            customers.remove(c);
            saveCustomers();
            System.out.println("Customer deleted.");
        }
    }

    // ================= ROOM MENU =================

    public void roomMenu(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("============ ROOM MANAGEMENT ============");
            System.out.println("1. Add Room");
            System.out.println("2. View All Rooms");
            System.out.println("3. View Available Rooms");
            System.out.println("4. Update Room");
            System.out.println("5. Change Room Status");
            System.out.println("6. Delete Room");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (scanner.nextLine().trim()) {
                case "1": addRoom(scanner); break;
                case "2": viewRooms(); break;
                case "3": viewAvailableRooms(); break;
                case "4": updateRoom(scanner); break;
                case "5": changeRoomStatus(scanner); break;
                case "6": deleteRoom(scanner); break;
                case "0": back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void addRoom(Scanner scanner) {
        System.out.println("\n--- Add Room ---");
        String number = InputHelper.readRequired(scanner, "Room number: ");

        if (findRoom(number) != null) {
            System.out.println("Room already exists.");
            return;
        }

        String type = InputHelper.readRequired(scanner, "Room type: ");
        double price = InputHelper.readPositiveDouble(scanner, "Price per night: ");
        int floor = InputHelper.readPositiveInt(scanner, "Floor: ");
        int capacity = InputHelper.readPositiveInt(scanner, "Guest capacity: ");
        String description = InputHelper.readRequired(scanner, "Description: ");

        rooms.add(new Room(number, type, price, "Available",
                floor, capacity, description));
        saveRooms();

        System.out.println("Room added successfully.");
    }

    private void viewRooms() {
        System.out.println("\n================ ROOM LIST ================");
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
            return;
        }

        System.out.printf("%-8s %-12s %-12s %-12s %-7s %-9s%n",
                "Room", "Type", "Price", "Status", "Floor", "Capacity");
        System.out.println("-------------------------------------------------------------");

        for (Room room : rooms) {
            room.display();
        }
    }

    private void viewAvailableRooms() {
        System.out.println("\n=========== AVAILABLE ROOMS ===========");
        boolean found = false;

        for (Room room : rooms) {
            if (room.isAvailable()) {
                room.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available rooms.");
        }
    }

    private void updateRoom(Scanner scanner) {
        String number = InputHelper.readRequired(scanner, "Enter room number: ");
        Room room = findRoom(number);

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        String type = InputHelper.readOptional(scanner,
                "Room type [" + room.getRoomType() + "]: ", room.getRoomType());

        System.out.print("Price per night [" + room.getPricePerNight() + "]: ");
        String priceInput = scanner.nextLine().trim();
        double price = room.getPricePerNight();

        if (!priceInput.isEmpty()) {
            try {
                price = Double.parseDouble(priceInput);
                if (price <= 0) {
                    System.out.println("Invalid price. Old price kept.");
                    price = room.getPricePerNight();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Old price kept.");
            }
        }

        String description = InputHelper.readOptional(scanner,
                "Description [" + room.getDescription() + "]: ", room.getDescription());

        room.setRoomType(type);
        room.setPricePerNight(price);
        saveRooms();

        System.out.println("Room updated successfully.");
        System.out.println("Description remains: " + description);
    }

    private void changeRoomStatus(Scanner scanner) {
        String number = InputHelper.readRequired(scanner, "Room number: ");
        Room room = findRoom(number);

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        System.out.println("Current status: " + room.getStatus());
        System.out.println("1. Available");
        System.out.println("2. Occupied");
        System.out.println("3. Maintenance");
        System.out.print("New status: ");
        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) room.setStatus("Available");
        else if (choice.equals("2")) room.setStatus("Occupied");
        else if (choice.equals("3")) room.setStatus("Maintenance");
        else {
            System.out.println("Invalid status.");
            return;
        }

        saveRooms();
        System.out.println("Room status updated.");
    }

    private void deleteRoom(Scanner scanner) {
        String number = InputHelper.readRequired(scanner, "Room number: ");
        Room room = findRoom(number);

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        if (hasActiveBookingForRoom(number)) {
            System.out.println("Cannot delete a room with an active booking.");
            return;
        }

        if (InputHelper.yesNo(scanner, "Delete room " + number + "?")) {
            rooms.remove(room);
            saveRooms();
            System.out.println("Room deleted.");
        }
    }

    // ================= BOOKING MENU =================

    public void bookingMenu(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("=========== BOOKING MANAGEMENT ===========");
            System.out.println("1. Create Booking");
            System.out.println("2. View All Bookings");
            System.out.println("3. Find Booking");
            System.out.println("4. Check In");
            System.out.println("5. Check Out");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Add Service to Booking");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (scanner.nextLine().trim()) {
                case "1": createBooking(scanner); break;
                case "2": viewBookings(); break;
                case "3": findBookingMenu(scanner); break;
                case "4": checkIn(scanner); break;
                case "5": checkOut(scanner); break;
                case "6": cancelBooking(scanner); break;
                case "7": addServiceToBooking(scanner); break;
                case "0": back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void createBooking(Scanner scanner) {
        System.out.println("\n--- Create New Booking ---");

        String customerId = InputHelper.readRequired(scanner, "Customer ID: ");
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found. Add the customer first.");
            return;
        }

        String roomNumber = InputHelper.readRequired(scanner, "Room number: ");
        Room room = findRoom(roomNumber);

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        if (!room.isAvailable()) {
            System.out.println("Room is not available.");
            return;
        }

        int guests = InputHelper.readPositiveInt(scanner, "Number of guests: ");

        if (guests > room.getCapacity()) {
            System.out.println("Room capacity is only " + room.getCapacity() + ".");
            return;
        }

        String checkIn = InputHelper.readDate(scanner, "Check-in date");
        String checkOut = InputHelper.readDate(scanner, "Check-out date");

        long nights = calculateNights(checkIn, checkOut);

        if (nights <= 0) {
            System.out.println("Check-out must be after check-in.");
            return;
        }

        if (roomHasDateConflict(roomNumber, checkIn, checkOut)) {
            System.out.println("This room already has a booking for those dates.");
            return;
        }

        double roomCharge = room.getPricePerNight() * nights;
        String bookingId = "B" + bookingCounter++;

        Booking booking = new Booking(
                bookingId, customerId, roomNumber, checkIn, checkOut,
                guests, "Reserved", roomCharge, 0, 0, roomCharge
        );

        bookings.add(booking);
        room.setStatus("Occupied");

        addTransaction(
                bookingId,
                "ROOM_BOOKING",
                roomCharge,
                "Room booking created"
        );

        saveAllData();

        System.out.println("Booking created successfully.");
        System.out.println("Booking ID: " + bookingId);
        System.out.printf("Number of nights: %d%n", nights);
        System.out.printf("Room charge: %.2f%n", roomCharge);
    }

    private void viewBookings() {
        System.out.println("\n============== BOOKING LIST ==============");

        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Booking b : bookings) {
            b.display();
        }
    }

    private void findBookingMenu(Scanner scanner) {
        String id = InputHelper.readRequired(scanner, "Booking ID: ");
        Booking booking = findBooking(id);

        if (booking == null) {
            System.out.println("Booking not found.");
        } else {
            booking.display();
            displayCustomerForBooking(booking);
        }
    }

    private void checkIn(Scanner scanner) {
        String id = InputHelper.readRequired(scanner, "Booking ID: ");
        Booking booking = findBooking(id);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        if (booking.getStatus().equalsIgnoreCase("Cancelled")) {
            System.out.println("Cancelled booking cannot be checked in.");
            return;
        }

        if (booking.getStatus().equalsIgnoreCase("Checked-In")) {
            System.out.println("Guest is already checked in.");
            return;
        }

        booking.setStatus("Checked-In");
        Room room = findRoom(booking.getRoomNumber());
        if (room != null) room.setStatus("Occupied");

        addTransaction(id, "CHECK_IN", 0, "Guest checked in");
        saveAllData();

        System.out.println("Guest checked in successfully.");
    }

    private void checkOut(Scanner scanner) {
        String id = InputHelper.readRequired(scanner, "Booking ID: ");
        Booking booking = findBooking(id);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        if (booking.getStatus().equalsIgnoreCase("Checked-Out")) {
            System.out.println("Booking is already checked out.");
            return;
        }

        if (booking.getStatus().equalsIgnoreCase("Cancelled")) {
            System.out.println("Cancelled booking cannot be checked out.");
            return;
        }

        System.out.printf("Current total bill: %.2f%n", booking.getTotalAmount());
        double paid = getBookingPayments(id);

        System.out.printf("Amount already paid: %.2f%n", paid);
        double due = booking.getTotalAmount() - paid;

        if (due > 0) {
            System.out.printf("Amount due: %.2f%n", due);

            if (InputHelper.yesNo(scanner, "Record payment now?")) {
                recordPaymentForBooking(scanner, booking, due);
                paid = getBookingPayments(id);
                due = booking.getTotalAmount() - paid;
            }
        }

        if (due > 0.01) {
            System.out.printf("Checkout cannot be completed. Remaining due: %.2f%n", due);
            return;
        }

        booking.setStatus("Checked-Out");

        Room room = findRoom(booking.getRoomNumber());
        if (room != null) room.setStatus("Available");

        addTransaction(id, "CHECK_OUT", 0, "Guest checked out");
        saveAllData();

        System.out.println("Checkout completed successfully.");
    }

    private void cancelBooking(Scanner scanner) {
        String id = InputHelper.readRequired(scanner, "Booking ID: ");
        Booking booking = findBooking(id);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        if (booking.getStatus().equalsIgnoreCase("Checked-Out")) {
            System.out.println("Checked-out booking cannot be cancelled.");
            return;
        }

        if (InputHelper.yesNo(scanner, "Cancel booking " + id + "?")) {
            booking.setStatus("Cancelled");

            Room room = findRoom(booking.getRoomNumber());
            if (room != null) room.setStatus("Available");

            addTransaction(id, "CANCELLATION", 0, "Booking cancelled");
            saveAllData();

            System.out.println("Booking cancelled.");
        }
    }

    private void addServiceToBooking(Scanner scanner) {
        String bookingId = InputHelper.readRequired(scanner, "Booking ID: ");
        Booking booking = findBooking(bookingId);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        if (booking.getStatus().equalsIgnoreCase("Cancelled")) {
            System.out.println("Cannot add service to a cancelled booking.");
            return;
        }

        viewActiveServices();
        String serviceId = InputHelper.readRequired(scanner, "Service ID: ");
        Service service = findService(serviceId);

        if (service == null || !service.isActive()) {
            System.out.println("Active service not found.");
            return;
        }

        int quantity = InputHelper.readPositiveInt(scanner, "Quantity: ");
        double charge = service.getPrice() * quantity;

        booking.setServiceCharge(booking.getServiceCharge() + charge);
        recalculateBookingTotal(booking);

        addTransaction(bookingId, "SERVICE", charge,
                service.getServiceName() + " x " + quantity);

        saveAllData();

        System.out.printf("Service added. Charge: %.2f%n", charge);
        System.out.printf("New booking total: %.2f%n", booking.getTotalAmount());
    }

    private void recalculateBookingTotal(Booking booking) {
        double total = booking.getRoomCharge()
                + booking.getServiceCharge()
                - booking.getDiscount();

        if (total < 0) total = 0;
        booking.setTotalAmount(total);
    }

    // ================= SERVICE MENU =================

    public void serviceMenu(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("========== HOTEL SERVICE MANAGEMENT ==========");
            System.out.println("1. Add Service");
            System.out.println("2. View Services");
            System.out.println("3. Update Service");
            System.out.println("4. Activate/Deactivate Service");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (scanner.nextLine().trim()) {
                case "1": addService(scanner); break;
                case "2": viewServices(); break;
                case "3": updateService(scanner); break;
                case "4": toggleService(scanner); break;
                case "0": back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void addService(Scanner scanner) {
        String id = "S" + serviceCounter++;
        String name = InputHelper.readRequired(scanner, "Service name: ");
        double price = InputHelper.readPositiveDouble(scanner, "Service price: ");
        String description = InputHelper.readRequired(scanner, "Description: ");

        services.add(new Service(id, name, price, description, true));
        saveServices();

        System.out.println("Service added. ID: " + id);
    }

    private void viewServices() {
        System.out.println("\n=============== SERVICES ===============");
        if (services.isEmpty()) {
            System.out.println("No services found.");
            return;
        }

        for (Service s : services) {
            s.display();
        }
    }

    private void viewActiveServices() {
        System.out.println("\n========== ACTIVE SERVICES ==========");
        for (Service s : services) {
            if (s.isActive()) s.display();
        }
    }

    private void updateService(Scanner scanner) {
        String id = InputHelper.readRequired(scanner, "Service ID: ");
        Service service = findService(id);

        if (service == null) {
            System.out.println("Service not found.");
            return;
        }

        String name = InputHelper.readOptional(scanner,
                "Service name [" + service.getServiceName() + "]: ",
                service.getServiceName());

        System.out.print("Price [" + service.getPrice() + "]: ");
        String priceText = scanner.nextLine().trim();
        double price = service.getPrice();

        if (!priceText.isEmpty()) {
            try {
                price = Double.parseDouble(priceText);
                if (price <= 0) price = service.getPrice();
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Old price kept.");
            }
        }

        String description = InputHelper.readOptional(scanner,
                "Description [" + service.getDescription() + "]: ",
                service.getDescription());

        service.setPrice(price);
        service.setDescription(description);

        saveServices();
        System.out.println("Service updated: " + name);
    }

    private void toggleService(Scanner scanner) {
        String id = InputHelper.readRequired(scanner, "Service ID: ");
        Service service = findService(id);

        if (service == null) {
            System.out.println("Service not found.");
            return;
        }

        service.setActive(!service.isActive());
        saveServices();

        System.out.println("Service is now " +
                (service.isActive() ? "active." : "inactive."));
    }

    // ================= PAYMENT MENU =================

    public void paymentMenu(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("=========== PAYMENT MANAGEMENT ===========");
            System.out.println("1. Record Payment");
            System.out.println("2. View All Payments");
            System.out.println("3. Booking Payment History");
            System.out.println("4. Calculate Booking Balance");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (scanner.nextLine().trim()) {
                case "1": recordPayment(scanner); break;
                case "2": viewPayments(); break;
                case "3": paymentHistory(scanner); break;
                case "4": showBookingBalance(scanner); break;
                case "0": back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void recordPayment(Scanner scanner) {
        String bookingId = InputHelper.readRequired(scanner, "Booking ID: ");
        Booking booking = findBooking(bookingId);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        double due = booking.getTotalAmount() - getBookingPayments(bookingId);

        System.out.printf("Current total: %.2f%n", booking.getTotalAmount());
        System.out.printf("Already paid: %.2f%n", getBookingPayments(bookingId));
        System.out.printf("Balance due: %.2f%n", due);

        if (due <= 0.01) {
            System.out.println("No payment is required.");
            return;
        }

        recordPaymentForBooking(scanner, booking, due);
    }

    private void recordPaymentForBooking(Scanner scanner, Booking booking, double maximum) {
        double amount;

        while (true) {
            amount = InputHelper.readPositiveDouble(scanner, "Payment amount: ");
            if (amount <= maximum + 0.01) break;
            System.out.printf("Payment cannot exceed %.2f%n", maximum);
        }

        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. Bank Transfer");
        System.out.println("4. Mobile Wallet");
        String choice = InputHelper.readRequired(scanner, "Payment method: ");

        String method;
        switch (choice) {
            case "1": method = "Cash"; break;
            case "2": method = "Card"; break;
            case "3": method = "Bank Transfer"; break;
            case "4": method = "Mobile Wallet"; break;
            default:
                System.out.println("Invalid method.");
                return;
        }

        String reference = InputHelper.readRequired(scanner, "Reference/receipt number: ");
        String paymentId = "P" + paymentCounter++;
        String date = LocalDate.now().toString();

        Payment payment = new Payment(
                paymentId, booking.getBookingId(), booking.getCustomerId(),
                amount, method, date, "Completed", reference
        );

        payments.add(payment);

        addTransaction(
                booking.getBookingId(),
                "PAYMENT",
                amount,
                "Payment received by " + method
        );

        saveAllData();

        System.out.println("Payment recorded successfully.");
        System.out.println("Payment ID: " + paymentId);
    }

    private void viewPayments() {
        System.out.println("\n=============== PAYMENT LIST ===============");

        if (payments.isEmpty()) {
            System.out.println("No payments found.");
            return;
        }

        for (Payment p : payments) {
            p.display();
        }
    }

    private void paymentHistory(Scanner scanner) {
        String bookingId = InputHelper.readRequired(scanner, "Booking ID: ");
        boolean found = false;

        for (Payment p : payments) {
            if (p.getBookingId().equalsIgnoreCase(bookingId)) {
                p.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No payment history found.");
        }
    }

    private void showBookingBalance(Scanner scanner) {
        String bookingId = InputHelper.readRequired(scanner, "Booking ID: ");
        Booking booking = findBooking(bookingId);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        double paid = getBookingPayments(bookingId);
        double due = booking.getTotalAmount() - paid;

        System.out.println("=================================");
        System.out.printf("Total Bill : %.2f%n", booking.getTotalAmount());
        System.out.printf("Paid       : %.2f%n", paid);
        System.out.printf("Balance    : %.2f%n", Math.max(0, due));
        System.out.println("=================================");
    }

    // ================= EMPLOYEE MENU =================

    public void employeeMenu(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("========== EMPLOYEE MANAGEMENT ==========");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Change Employee Status");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (scanner.nextLine().trim()) {
                case "1": addEmployee(scanner); break;
                case "2": viewEmployees(); break;
                case "3": searchEmployee(scanner); break;
                case "4": updateEmployee(scanner); break;
                case "5": changeEmployeeStatus(scanner); break;
                case "0": back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void addEmployee(Scanner scanner) {
        String id = "E" + employeeCounter++;
        String name = InputHelper.readRequired(scanner, "Name: ");
        String position = InputHelper.readRequired(scanner, "Position: ");
        String phone = InputHelper.readRequired(scanner, "Phone: ");
        String email = InputHelper.readRequired(scanner, "Email: ");
        double salary = InputHelper.readPositiveDouble(scanner, "Salary: ");
        String shift = InputHelper.readRequired(scanner, "Shift: ");

        employees.add(new Employee(id, name, position, phone, email,
                salary, shift, "Active"));

        saveEmployees();
        System.out.println("Employee added. ID: " + id);
    }

    private void viewEmployees() {
        System.out.println("\n================ EMPLOYEES ================");
        System.out.printf("%-8s %-20s %-16s %-15s %-12s %-10s %-10s%n",
                "ID", "Name", "Position", "Phone", "Salary", "Shift", "Status");
        System.out.println("--------------------------------------------------------------------------------");

        for (Employee e : employees) {
            e.display();
        }
    }

    private void searchEmployee(Scanner scanner) {
        String text = InputHelper.readRequired(scanner, "Search name or position: ");
        boolean found = false;

        for (Employee e : employees) {
            if (e.getName().toLowerCase().contains(text.toLowerCase())
                    || e.getPosition().toLowerCase().contains(text.toLowerCase())) {
                e.display();
                found = true;
            }
        }

        if (!found) System.out.println("No employee found.");
    }

    private void updateEmployee(Scanner scanner) {
        String id = InputHelper.readRequired(scanner, "Employee ID: ");
        Employee e = findEmployee(id);

        if (e == null) {
            System.out.println("Employee not found.");
            return;
        }

        e.setPosition(InputHelper.readOptional(scanner,
                "Position [" + e.getPosition() + "]: ", e.getPosition()));

        e.setPhone(InputHelper.readOptional(scanner,
                "Phone [" + e.getPhone() + "]: ", e.getPhone()));

        e.setEmail(InputHelper.readOptional(scanner,
                "Email [" + e.getEmail() + "]: ", e.getEmail()));

        System.out.print("Salary [" + e.getSalary() + "]: ");
        String salaryText = scanner.nextLine().trim();

        if (!salaryText.isEmpty()) {
            try {
                double salary = Double.parseDouble(salaryText);
                if (salary > 0) e.setSalary(salary);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid salary. Old value kept.");
            }
        }

        e.setShift(InputHelper.readOptional(scanner,
                "Shift [" + e.getShift() + "]: ", e.getShift()));

        saveEmployees();
        System.out.println("Employee updated.");
    }

    private void changeEmployeeStatus(Scanner scanner) {
        String id = InputHelper.readRequired(scanner, "Employee ID: ");
        Employee e = findEmployee(id);

        if (e == null) {
            System.out.println("Employee not found.");
            return;
        }

        e.setStatus(e.getStatus().equalsIgnoreCase("Active")
                ? "Inactive" : "Active");

        saveEmployees();
        System.out.println("Employee status changed to " + e.getStatus());
    }

    // ================= SEARCH MENU =================

    public void searchMenu(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("================ SEARCH ================");
            System.out.println("1. Search Customer");
            System.out.println("2. Search Room");
            System.out.println("3. Search Booking");
            System.out.println("4. Search Employee");
            System.out.println("5. Search Payment");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (scanner.nextLine().trim()) {
                case "1": searchCustomer(scanner); break;
                case "2": searchRoom(scanner); break;
                case "3": findBookingMenu(scanner); break;
                case "4": searchEmployee(scanner); break;
                case "5": searchPayment(scanner); break;
                case "0": back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void searchRoom(Scanner scanner) {
        String text = InputHelper.readRequired(scanner, "Room number or type: ");
        boolean found = false;

        for (Room r : rooms) {
            if (r.getRoomNumber().equalsIgnoreCase(text)
                    || r.getRoomType().toLowerCase().contains(text.toLowerCase())) {
                r.display();
                found = true;
            }
        }

        if (!found) System.out.println("No room found.");
    }

    private void searchPayment(Scanner scanner) {
        String text = InputHelper.readRequired(scanner,
                "Payment ID, booking ID, or customer ID: ");
        boolean found = false;

        for (Payment p : payments) {
            if (p.getPaymentId().equalsIgnoreCase(text)
                    || p.getBookingId().equalsIgnoreCase(text)
                    || p.getCustomerId().equalsIgnoreCase(text)) {
                p.display();
                found = true;
            }
        }

        if (!found) System.out.println("No payment found.");
    }

    // ================= REPORT MENU =================

    public void reportMenu(Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println();
            System.out.println("============= REPORTS =============");
            System.out.println("1. Hotel Summary");
            System.out.println("2. Occupancy Report");
            System.out.println("3. Revenue Report");
            System.out.println("4. Customer Account Statement");
            System.out.println("5. Booking Statement");
            System.out.println("6. Transaction History");
            System.out.println("7. Employee Summary");
            System.out.println("0. Back");
            System.out.print("Choice: ");

            switch (scanner.nextLine().trim()) {
                case "1": hotelSummary(); break;
                case "2": occupancyReport(); break;
                case "3": revenueReport(); break;
                case "4": customerStatement(scanner); break;
                case "5": bookingStatement(scanner); break;
                case "6": transactionHistory(scanner); break;
                case "7": employeeSummary(); break;
                case "0": back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private void hotelSummary() {
        int available = 0;
        int occupied = 0;
        int maintenance = 0;

        for (Room room : rooms) {
            if (room.getStatus().equalsIgnoreCase("Available")) available++;
            else if (room.getStatus().equalsIgnoreCase("Occupied")) occupied++;
            else if (room.getStatus().equalsIgnoreCase("Maintenance")) maintenance++;
        }

        int activeBookings = 0;

        for (Booking b : bookings) {
            if (!b.getStatus().equalsIgnoreCase("Cancelled")
                    && !b.getStatus().equalsIgnoreCase("Checked-Out")) {
                activeBookings++;
            }
        }

        double revenue = 0;
        for (Payment p : payments) {
            if (p.getStatus().equalsIgnoreCase("Completed")) {
                revenue += p.getAmount();
            }
        }

        System.out.println("\n============== HOTEL SUMMARY ==============");
        System.out.println("Customers          : " + customers.size());
        System.out.println("Rooms              : " + rooms.size());
        System.out.println("Available Rooms    : " + available);
        System.out.println("Occupied Rooms     : " + occupied);
        System.out.println("Maintenance Rooms  : " + maintenance);
        System.out.println("Active Bookings    : " + activeBookings);
        System.out.println("Total Bookings     : " + bookings.size());
        System.out.println("Employees          : " + employees.size());
        System.out.printf("Collected Revenue  : %.2f%n", revenue);
        System.out.println("============================================");
    }

    private void occupancyReport() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        int occupied = 0;

        for (Room room : rooms) {
            if (room.getStatus().equalsIgnoreCase("Occupied")) {
                occupied++;
            }
        }

        double percentage = (occupied * 100.0) / rooms.size();

        System.out.println("\n=========== OCCUPANCY REPORT ===========");
        System.out.println("Total rooms : " + rooms.size());
        System.out.println("Occupied    : " + occupied);
        System.out.println("Available   : " + (rooms.size() - occupied));
        System.out.printf("Occupancy   : %.2f%%%n", percentage);
    }

    private void revenueReport() {
        double total = 0;
        double cash = 0;
        double card = 0;
        double bank = 0;
        double wallet = 0;

        for (Payment p : payments) {
            if (!p.getStatus().equalsIgnoreCase("Completed")) continue;

            total += p.getAmount();

            switch (p.getMethod().toLowerCase()) {
                case "cash": cash += p.getAmount(); break;
                case "card": card += p.getAmount(); break;
                case "bank transfer": bank += p.getAmount(); break;
                case "mobile wallet": wallet += p.getAmount(); break;
                default: break;
            }
        }

        System.out.println("\n============== REVENUE REPORT ==============");
        System.out.printf("Total Revenue       : %.2f%n", total);
        System.out.printf("Cash                : %.2f%n", cash);
        System.out.printf("Card                : %.2f%n", card);
        System.out.printf("Bank Transfer       : %.2f%n", bank);
        System.out.printf("Mobile Wallet       : %.2f%n", wallet);
        System.out.println("=============================================");
    }

    private void customerStatement(Scanner scanner) {
        String customerId = InputHelper.readRequired(scanner, "Customer ID: ");
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("\n=========== CUSTOMER STATEMENT ===========");
        customer.display();

        double totalBill = 0;
        double totalPaid = 0;

        for (Booking b : bookings) {
            if (b.getCustomerId().equalsIgnoreCase(customerId)) {
                System.out.println();
                b.display();
                totalBill += b.getTotalAmount();
            }
        }

        for (Payment p : payments) {
            if (p.getCustomerId().equalsIgnoreCase(customerId)) {
                totalPaid += p.getAmount();
            }
        }

        System.out.println("\n-------------------------------------------");
        System.out.printf("Total Booking Charges: %.2f%n", totalBill);
        System.out.printf("Total Payments       : %.2f%n", totalPaid);
        System.out.printf("Overall Balance      : %.2f%n",
                Math.max(0, totalBill - totalPaid));
    }

    private void bookingStatement(Scanner scanner) {
        String bookingId = InputHelper.readRequired(scanner, "Booking ID: ");
        Booking booking = findBooking(bookingId);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        System.out.println("\n============== BOOKING STATEMENT ==============");
        displayCustomerForBooking(booking);
        booking.display();

        System.out.println("\nPayments:");
        double paid = 0;

        for (Payment p : payments) {
            if (p.getBookingId().equalsIgnoreCase(bookingId)) {
                p.display();
                paid += p.getAmount();
            }
        }

        System.out.println("\nTransactions:");
        for (Transaction t : transactions) {
            if (t.getBookingId().equalsIgnoreCase(bookingId)) {
                t.display();
            }
        }

        System.out.println("\n-----------------------------------------------");
        System.out.printf("Total Bill : %.2f%n", booking.getTotalAmount());
        System.out.printf("Paid       : %.2f%n", paid);
        System.out.printf("Balance    : %.2f%n",
                Math.max(0, booking.getTotalAmount() - paid));
    }

    private void transactionHistory(Scanner scanner) {
        System.out.println("\n============= TRANSACTION HISTORY =============");

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        String bookingId = scanner.nextLine().trim();

        if (bookingId.isEmpty()) {
            for (Transaction t : transactions) {
                t.display();
            }
            return;
        }

        boolean found = false;
        for (Transaction t : transactions) {
            if (t.getBookingId().equalsIgnoreCase(bookingId)) {
                t.display();
                found = true;
            }
        }

        if (!found) System.out.println("No transactions for that booking.");
    }

    private void employeeSummary() {
        int active = 0;
        double totalSalary = 0;

        for (Employee e : employees) {
            if (e.getStatus().equalsIgnoreCase("Active")) active++;
            totalSalary += e.getSalary();
        }

        System.out.println("\n============= EMPLOYEE SUMMARY =============");
        System.out.println("Total Employees : " + employees.size());
        System.out.println("Active          : " + active);
        System.out.println("Inactive        : " + (employees.size() - active));
        System.out.printf("Total Salary    : %.2f%n", totalSalary);
    }

    // ================= TRANSACTION HELPERS =================

    private void addTransaction(String bookingId, String type,
                                double amount, String description) {
        String id = "T" + transactionCounter++;
        String date = LocalDate.now().toString();

        transactions.add(new Transaction(
                id, bookingId, type, amount, date, description
        ));
    }

    private double getBookingPayments(String bookingId) {
        double total = 0;

        for (Payment p : payments) {
            if (p.getBookingId().equalsIgnoreCase(bookingId)
                    && p.getStatus().equalsIgnoreCase("Completed")) {
                total += p.getAmount();
            }
        }

        return total;
    }

    private long calculateNights(String checkIn, String checkOut) {
        try {
            LocalDate start = LocalDate.parse(checkIn);
            LocalDate end = LocalDate.parse(checkOut);
            return ChronoUnit.DAYS.between(start, end);
        } catch (Exception e) {
            return -1;
        }
    }

    private boolean roomHasDateConflict(String roomNumber,
                                        String newCheckIn,
                                        String newCheckOut) {
        try {
            LocalDate newStart = LocalDate.parse(newCheckIn);
            LocalDate newEnd = LocalDate.parse(newCheckOut);

            for (Booking b : bookings) {
                if (!b.getRoomNumber().equalsIgnoreCase(roomNumber)) continue;
                if (b.getStatus().equalsIgnoreCase("Cancelled")
                        || b.getStatus().equalsIgnoreCase("Checked-Out")) {
                    continue;
                }

                LocalDate oldStart = LocalDate.parse(b.getCheckInDate());
                LocalDate oldEnd = LocalDate.parse(b.getCheckOutDate());

                boolean overlaps = newStart.isBefore(oldEnd)
                        && oldStart.isBefore(newEnd);

                if (overlaps) return true;
            }
        } catch (Exception e) {
            return true;
        }

        return false;
    }

    private boolean hasActiveBookingForCustomer(String customerId) {
        for (Booking b : bookings) {
            if (b.getCustomerId().equalsIgnoreCase(customerId)
                    && !b.getStatus().equalsIgnoreCase("Cancelled")
                    && !b.getStatus().equalsIgnoreCase("Checked-Out")) {
                return true;
            }
        }
        return false;
    }

    private boolean hasActiveBookingForRoom(String roomNumber) {
        for (Booking b : bookings) {
            if (b.getRoomNumber().equalsIgnoreCase(roomNumber)
                    && !b.getStatus().equalsIgnoreCase("Cancelled")
                    && !b.getStatus().equalsIgnoreCase("Checked-Out")) {
                return true;
            }
        }
        return false;
    }

    private void displayCustomerForBooking(Booking booking) {
        Customer customer = findCustomer(booking.getCustomerId());

        if (customer != null) {
            System.out.println("\nCustomer information:");
            customer.display();
        }
    }

    // ================= FIND METHODS =================

    private Customer findCustomer(String id) {
        for (Customer c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(id)) return c;
        }
        return null;
    }

    private Customer findCustomerByIdNumber(String idNumber) {
        for (Customer c : customers) {
            if (c.getIdNumber().equalsIgnoreCase(idNumber)) return c;
        }
        return null;
    }

    private Room findRoom(String number) {
        for (Room r : rooms) {
            if (r.getRoomNumber().equalsIgnoreCase(number)) return r;
        }
        return null;
    }

    private Booking findBooking(String id) {
        for (Booking b : bookings) {
            if (b.getBookingId().equalsIgnoreCase(id)) return b;
        }
        return null;
    }

    private Service findService(String id) {
        for (Service s : services) {
            if (s.getServiceId().equalsIgnoreCase(id)) return s;
        }
        return null;
    }

    private Employee findEmployee(String id) {
        for (Employee e : employees) {
            if (e.getEmployeeId().equalsIgnoreCase(id)) return e;
        }
        return null;
    }
}
