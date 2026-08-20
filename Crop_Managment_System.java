import java.util.ArrayList;
import java.util.Scanner;

// =====================================================
// USER CLASS
// =====================================================
abstract class User {

    private int id;
    private String name;
    private String phone;

    User(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    abstract void displayDetails();
}


// =====================================================
// SELLER CLASS
// =====================================================
class Seller extends User {

    private String address;

    Seller(int id, String name, String phone, String address) {
        super(id, name, phone);
        this.address = address;
    }

    @Override
    void displayDetails() {

        System.out.println(Color.YELLOW + "\n----- SELLER DETAILS -----" + Color.RESET);

        System.out.println("Seller ID : " + getId());
        System.out.println("Name      : " + getName());
        System.out.println("Phone     : " + getPhone());
        System.out.println("Address   : " + address);
    }

    public void addCrop(ArrayList<Crop> crops, Crop crop) {

        crops.add(crop);

        System.out.println(
            Color.GREEN + "\nCrop added successfully!" + Color.RESET
        );
    }
}


// =====================================================
// DEALER CLASS
// =====================================================
class Dealer extends User {

    private String companyName;

    Dealer(int id, String name, String phone, String companyName) {
        super(id, name, phone);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    void displayDetails() {

        System.out.println(Color.YELLOW + "\n----- DEALER DETAILS -----" + Color.RESET);

        System.out.println("Dealer ID    : " + getId());
        System.out.println("Name         : " + getName());
        System.out.println("Phone        : " + getPhone());
        System.out.println("Company Name : " + companyName);
    }
}


// =====================================================
// CROP CLASS
// =====================================================
class Crop {

    private int cropId;
    private String cropName;
    private String cropType;
    private double quantity;
    private double pricePerKg;
    private int sellerId;

    Crop(int cropId, String cropName, String cropType,
         double quantity, double pricePerKg, int sellerId) {

        this.cropId = cropId;
        this.cropName = cropName;
        this.cropType = cropType;
        this.quantity = quantity;
        this.pricePerKg = pricePerKg;
        this.sellerId = sellerId;
    }

    public int getCropId() {
        return cropId;
    }

    public String getCropName() {
        return cropName;
    }

    public String getCropType() {
        return cropType;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setPricePerKg(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }
}


// =====================================================
// TRANSACTION CLASS
// =====================================================
class Transaction {

    private int transactionId;
    private int dealerId;
    private int cropId;
    private String cropName;
    private double quantity;
    private double totalAmount;

    Transaction(int transactionId, int dealerId,
                int cropId, String cropName,
                double quantity, double totalAmount) {

        this.transactionId = transactionId;
        this.dealerId = dealerId;
        this.cropId = cropId;
        this.cropName = cropName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void displayTransaction() {

        System.out.println(
            Color.CYAN + "--------------------------------------"
            + Color.RESET
        );

        System.out.println("Transaction ID : " + transactionId);
        System.out.println("Dealer ID      : " + dealerId);
        System.out.println("Crop ID        : " + cropId);
        System.out.println("Crop Name      : " + cropName);
        System.out.println("Quantity       : " + quantity + " kg");
        System.out.println("Total Amount   : " + totalAmount);

        System.out.println(
            Color.CYAN + "--------------------------------------"
            + Color.RESET
        );
    }
}


// =====================================================
// COLOR CLASS
// =====================================================
class Color {

    // Reset
    public static final String RESET = "\u001B[0m";

    // Colors
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // Bold
    public static final String BOLD = "\u001B[1m";
}


// =====================================================
// MAIN CLASS
// =====================================================
class CropManagementSystem {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Seller> sellers = new ArrayList<>();
    static ArrayList<Dealer> dealers = new ArrayList<>();
    static ArrayList<Crop> crops = new ArrayList<>();
    static ArrayList<Transaction> transactions = new ArrayList<>();


    // =====================================================
    // PHONE NUMBER VALIDATION
    // =====================================================
    static boolean isValidPhone(String phone) {

        if (phone.length() != 10) {
            return false;
        }

        if (phone.charAt(0) < '6'
                || phone.charAt(0) > '9') {

            return false;
        }

        for (int i = 0; i < phone.length(); i++) {

            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }

        return true;
    }


    // =====================================================
    // CHECK PHONE EXISTS
    // =====================================================
    static boolean phoneExists(String phone) {

        for (Seller seller : sellers) {

            if (seller.getPhone().equals(phone)) {
                return true;
            }
        }

        for (Dealer dealer : dealers) {

            if (dealer.getPhone().equals(phone)) {
                return true;
            }
        }

        return false;
    }


    // =====================================================
    // CHECK SELLER ID
    // =====================================================
    static boolean sellerIdExists(int id) {

        for (Seller seller : sellers) {

            if (seller.getId() == id) {
                return true;
            }
        }

        return false;
    }


    // =====================================================
    // CHECK DEALER ID
    // =====================================================
    static boolean dealerIdExists(int id) {

        for (Dealer dealer : dealers) {

            if (dealer.getId() == id) {
                return true;
            }
        }

        return false;
    }


    // =====================================================
    // CHECK CROP ID
    // =====================================================
    static boolean cropIdExists(int id) {

        for (Crop crop : crops) {

            if (crop.getCropId() == id) {
                return true;
            }
        }

        return false;
    }


    // =====================================================
    // CHECK TRANSACTION ID
    // =====================================================
    static boolean transactionIdExists(int id) {

        for (Transaction transaction : transactions) {

            if (transaction.getTransactionId() == id) {
                return true;
            }
        }

        return false;
    }


    // =====================================================
    // ADD DEFAULT CROPS
    // =====================================================
    static void addDefaultCrops(Seller seller) {

        if (crops.size() > 0) {
            return;
        }

        Crop crop1 = new Crop(
                101,
                "Rice",
                "Paddy",
                500,
                45,
                seller.getId()
        );

        Crop crop2 = new Crop(
                102,
                "Wheat",
                "Grain",
                300,
                40,
                seller.getId()
        );

        Crop crop3 = new Crop(
                103,
                "Corn",
                "Grain",
                250,
                35,
                seller.getId()
        );

        crops.add(crop1);
        crops.add(crop2);
        crops.add(crop3);

        System.out.println(
            Color.GREEN +
            "Default crops added successfully!"
            + Color.RESET
        );
    }


    // =====================================================
    // SELLER REGISTRATION
    // =====================================================
    static void registerSeller() {

        System.out.println(
            Color.YELLOW +
            "\n===== SELLER REGISTRATION ====="
            + Color.RESET
        );

        int id;

        while (true) {

            System.out.print("Enter Seller ID: ");
            id = sc.nextInt();
            sc.nextLine();

            if (id <= 0) {

                System.out.println(
                    Color.RED +
                    "ID must be greater than 0."
                    + Color.RESET
                );

            } else if (sellerIdExists(id)) {

                System.out.println(
                    Color.RED +
                    "Seller ID already exists!"
                    + Color.RESET
                );

            } else {

                break;
            }
        }


        System.out.print("Enter Seller Name: ");
        String name = sc.nextLine();


        // PHONE REGISTRATION
        String phone;

        while (true) {

            System.out.print(
                "Enter Phone Number (10 digits starting with 6-9): "
            );

            phone = sc.nextLine();

            if (!isValidPhone(phone)) {

                System.out.println(
                    Color.RED +
                    "Invalid phone number! "
                    + "Enter exactly 10 digits and "
                    + "the number must start with 6, 7, 8, or 9."
                    + Color.RESET
                );

            } else if (phoneExists(phone)) {

                System.out.println(
                    Color.RED +
                    "Phone number already registered!"
                    + Color.RESET
                );

            } else {

                break;
            }
        }


        System.out.print("Enter Address: ");
        String address = sc.nextLine();


        Seller seller =
                new Seller(id, name, phone, address);

        sellers.add(seller);


        // ADD DEFAULT CROPS
        addDefaultCrops(seller);


        System.out.println(
            Color.GREEN +
            "\n================================="
            + Color.RESET
        );

        System.out.println(
            Color.GREEN +
            "Seller registered successfully!"
            + Color.RESET
        );

        System.out.println(
            Color.GREEN +
            "Please login using your"
            + Color.RESET
        );

        System.out.println(
            Color.GREEN +
            "registered Seller ID and Phone Number."
            + Color.RESET
        );

        System.out.println(
            Color.GREEN +
            "================================="
            + Color.RESET
        );
    }


    // =====================================================
    // DEALER REGISTRATION
    // =====================================================
    static void registerDealer() {

        System.out.println(
            Color.YELLOW +
            "\n===== DEALER REGISTRATION ====="
            + Color.RESET
        );

        int id;

        while (true) {

            System.out.print("Enter Dealer ID: ");
            id = sc.nextInt();
            sc.nextLine();

            if (id <= 0) {

                System.out.println(
                    Color.RED +
                    "ID must be greater than 0."
                    + Color.RESET
                );

            } else if (dealerIdExists(id)) {

                System.out.println(
                    Color.RED +
                    "Dealer ID already exists!"
                    + Color.RESET
                );

            } else {

                break;
            }
        }


        System.out.print("Enter Dealer Name: ");
        String name = sc.nextLine();


        // PHONE REGISTRATION
        String phone;

        while (true) {

            System.out.print(
                "Enter Phone Number (10 digits starting with 6-9): "
            );

            phone = sc.nextLine();

            if (!isValidPhone(phone)) {

                System.out.println(
                    Color.RED +
                    "Invalid phone number! "
                    + "Enter exactly 10 digits and "
                    + "the number must start with 6, 7, 8, or 9."
                    + Color.RESET
                );

            } else if (phoneExists(phone)) {

                System.out.println(
                    Color.RED +
                    "Phone number already registered!"
                    + Color.RESET
                );

            } else {

                break;
            }
        }


        System.out.print("Enter Company Name: ");
        String company = sc.nextLine();


        Dealer dealer =
                new Dealer(id, name, phone, company);

        dealers.add(dealer);


        System.out.println(
            Color.GREEN +
            "\n================================="
            + Color.RESET
        );

        System.out.println(
            Color.GREEN +
            "Dealer registered successfully!"
            + Color.RESET
        );

        System.out.println(
            Color.GREEN +
            "Please login using your"
            + Color.RESET
        );

        System.out.println(
            Color.GREEN +
            "registered Dealer ID and Phone Number."
            + Color.RESET
        );

        System.out.println(
            Color.GREEN +
            "================================="
            + Color.RESET
        );
    }


    // =====================================================
    // GENERATE OTP
    // =====================================================
    static int generateOTP() {

        return (int)(Math.random() * 900000) + 100000;
    }


    // =====================================================
    // OTP VERIFICATION
    // =====================================================
    static boolean verifyOTP() {

        int otp = generateOTP();

        System.out.println(
            Color.YELLOW +
            "\n================================="
            + Color.RESET
        );

        System.out.println(
            Color.YELLOW +
            "          OTP AUTHENTICATION"
            + Color.RESET
        );

        System.out.println(
            Color.YELLOW +
            "================================="
            + Color.RESET
        );

        // Testing purpose:
        // In real application OTP should be sent through SMS.
        System.out.println(
            Color.CYAN +
            "Your OTP is: " + otp
            + Color.RESET
        );

        System.out.print(
            "Enter OTP: "
        );

        int enteredOTP = sc.nextInt();
        sc.nextLine();

        if (enteredOTP == otp) {

            System.out.println(
                Color.GREEN +
                "OTP verified successfully!"
                + Color.RESET
            );

            return true;

        } else {

            System.out.println(
                Color.RED +
                "Invalid OTP!"
                + Color.RESET
            );

            return false;
        }
    }


    // =====================================================
    // SELLER LOGIN
    // =====================================================
    static Seller sellerLogin() {

        System.out.println(
            Color.YELLOW +
            "\n===== SELLER LOGIN ====="
            + Color.RESET
        );

        System.out.print("Enter Seller ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print(
            "Enter Registered Phone Number: "
        );

        String phone = sc.nextLine();


        if (!isValidPhone(phone)) {

            System.out.println(
                Color.RED +
                "\nInvalid phone number format!"
                + Color.RESET
            );

            System.out.println(
                Color.RED +
                "Phone number must contain exactly "
                + "10 digits and start with 6-9."
                + Color.RESET
            );

            return null;
        }


        for (Seller seller : sellers) {

            if (seller.getId() == id
                    && seller.getPhone().equals(phone)) {

                System.out.println(
                    Color.GREEN +
                    "\n================================="
                    + Color.RESET
                );

                System.out.println(
                    Color.GREEN +
                    "Seller ID and Phone Number Verified!"
                    + Color.RESET
                );

                System.out.println(
                    Color.CYAN +
                    "OTP will be sent to: " + phone
                    + Color.RESET
                );

                System.out.println(
                    Color.GREEN +
                    "================================="
                    + Color.RESET
                );


                // OTP VERIFICATION
                if (!verifyOTP()) {

                    System.out.println(
                        Color.RED +
                        "\nSeller Login Failed!"
                        + Color.RESET
                    );

                    System.out.println(
                        Color.RED +
                        "Incorrect OTP."
                        + Color.RESET
                    );

                    return null;
                }


                System.out.println(
                    Color.GREEN +
                    "\n================================="
                    + Color.RESET
                );

                System.out.println(
                    Color.GREEN +
                    "Seller Login Successful!"
                    + Color.RESET
                );

                System.out.println(
                    Color.GREEN +
                    "Welcome " + seller.getName()
                    + Color.RESET
                );

                System.out.println(
                    Color.GREEN +
                    "================================="
                    + Color.RESET
                );

                return seller;
            }
        }


        System.out.println(
            Color.RED +
            "\n================================="
            + Color.RESET
        );

        System.out.println(
            Color.RED +
            "Login Failed!"
            + Color.RESET
        );

        System.out.println(
            Color.RED +
            "Seller ID and Phone Number"
            + Color.RESET
        );

        System.out.println(
            Color.RED +
            "do not match the registered details."
            + Color.RESET
        );

        System.out.println(
            Color.RED +
            "================================="
            + Color.RESET
        );

        return null;
    }


    // =====================================================
    // DEALER LOGIN
    // =====================================================
    static Dealer dealerLogin() {

        System.out.println(
            Color.YELLOW +
            "\n===== DEALER LOGIN ====="
            + Color.RESET
        );

        System.out.print("Enter Dealer ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print(
            "Enter Registered Phone Number: "
        );

        String phone = sc.nextLine();


        if (!isValidPhone(phone)) {

            System.out.println(
                Color.RED +
                "\nInvalid phone number format!"
                + Color.RESET
            );

            System.out.println(
                Color.RED +
                "Phone number must contain exactly "
                + "10 digits and start with 6-9."
                + Color.RESET
            );

            return null;
        }


        for (Dealer dealer : dealers) {

            if (dealer.getId() == id
                    && dealer.getPhone().equals(phone)) {

                System.out.println(
                    Color.GREEN +
                    "\n================================="
                    + Color.RESET
                );

                System.out.println(
                    Color.GREEN +
                    "Dealer ID and Phone Number Verified!"
                    + Color.RESET
                );

                System.out.println(
                    Color.CYAN +
                    "OTP will be sent to: " + phone
                    + Color.RESET
                );

                System.out.println(
                    Color.GREEN +
                    "================================="
                    + Color.RESET
                );


                // OTP VERIFICATION
                if (!verifyOTP()) {

                    System.out.println(
                        Color.RED +
                        "\nDealer Login Failed!"
                        + Color.RESET
                    );

                    System.out.println(
                        Color.RED +
                        "Incorrect OTP."
                        + Color.RESET
                    );

                    return null;
                }


                System.out.println(
                    Color.GREEN +
                    "\n================================="
                    + Color.RESET
                );

                System.out.println(
                    Color.GREEN +
                    "Dealer Login Successful!"
                    + Color.RESET
                );

                System.out.println(
                    Color.GREEN +
                    "Welcome " + dealer.getName()
                    + Color.RESET
                );

                System.out.println(
                    Color.GREEN +
                    "================================="
                    + Color.RESET
                );

                return dealer;
            }
        }


        System.out.println(
            Color.RED +
            "\n================================="
            + Color.RESET
        );

        System.out.println(
            Color.RED +
            "Login Failed!"
            + Color.RESET
        );

        System.out.println(
            Color.RED +
            "Dealer ID and Phone Number"
            + Color.RESET
        );

        System.out.println(
            Color.RED +
            "do not match the registered details."
            + Color.RESET
        );

        System.out.println(
            Color.RED +
            "================================="
            + Color.RESET
        );

        return null;
    }


    // =====================================================
    // SELLER ACCESS PAGE
    // =====================================================
    static void sellerAccess() {

        int choice;

        do {

            System.out.println(
                Color.BLUE +
                "\n================================"
                + Color.RESET
            );

            System.out.println(
                Color.BLUE +
                "             SELLER"
                + Color.RESET
            );

            System.out.println(
                Color.BLUE +
                "================================"
                + Color.RESET
            );

            System.out.println(
                Color.WHITE +
                "1. Register"
                + Color.RESET
            );

            System.out.println(
                Color.WHITE +
                "2. Login"
                + Color.RESET
            );

            System.out.println(
                Color.WHITE +
                "3. Back"
                + Color.RESET
            );

            System.out.print(
                "\nEnter your choice: "
            );

            choice = sc.nextInt();
            sc.nextLine();


            switch (choice) {

                case 1:
                    registerSeller();
                    break;

                case 2:

                    if (sellers.size() == 0) {

                        System.out.println(
                            Color.RED +
                            "\nNo seller is registered."
                            + Color.RESET
                        );

                        System.out.println(
                            Color.YELLOW +
                            "Please register first."
                            + Color.RESET
                        );

                    } else {

                        Seller seller = sellerLogin();

                        if (seller != null) {
                            sellerMenu(seller);
                        }
                    }

                    break;

                case 3:

                    System.out.println(
                        Color.YELLOW +
                        "Returning to main menu..."
                        + Color.RESET
                    );

                    break;

                default:

                    System.out.println(
                        Color.RED +
                        "Invalid choice!"
                        + Color.RESET
                    );
            }

        } while (choice != 3);
    }


    // =====================================================
    // DEALER ACCESS PAGE
    // =====================================================
    static void dealerAccess() {

        int choice;

        do {

            System.out.println(
                Color.BLUE +
                "\n================================"
                + Color.RESET
            );

            System.out.println(
                Color.BLUE +
                "             DEALER"
                + Color.RESET
            );

            System.out.println(
                Color.BLUE +
                "================================"
                + Color.RESET
            );

            System.out.println(
                Color.WHITE +
                "1. Register"
                + Color.RESET
            );

            System.out.println(
                Color.WHITE +
                "2. Login"
                + Color.RESET
            );

            System.out.println(
                Color.WHITE +
                "3. Back"
                + Color.RESET
            );

            System.out.print(
                "\nEnter your choice: "
            );

            choice = sc.nextInt();
            sc.nextLine();


            switch (choice) {

                case 1:
                    registerDealer();
                    break;

                case 2:

                    if (dealers.size() == 0) {

                        System.out.println(
                            Color.RED +
                            "\nNo dealer is registered."
                            + Color.RESET
                        );

                        System.out.println(
                            Color.YELLOW +
                            "Please register first."
                            + Color.RESET
                        );

                    } else {

                        Dealer dealer = dealerLogin();

                        if (dealer != null) {
                            dealerMenu(dealer);
                        }
                    }

                    break;

                case 3:

                    System.out.println(
                        Color.YELLOW +
                        "Returning to main menu..."
                        + Color.RESET
                    );

                    break;

                default:

                    System.out.println(
                        Color.RED +
                        "Invalid choice!"
                        + Color.RESET
                    );
            }

        } while (choice != 3);
    }


    // =====================================================
    // ADD CROP
    // =====================================================
    static void addCrop(Seller seller) {

        System.out.println(
            Color.YELLOW +
            "\n===== ADD CROP ====="
            + Color.RESET
        );

        int cropId;

        while (true) {

            System.out.print("Enter Crop ID: ");
            cropId = sc.nextInt();
            sc.nextLine();

            if (cropId <= 0) {

                System.out.println(
                    Color.RED +
                    "Crop ID must be greater than 0."
                    + Color.RESET
                );

            } else if (cropIdExists(cropId)) {

                System.out.println(
                    Color.RED +
                    "Crop ID already exists!"
                    + Color.RESET
                );

            } else {

                break;
            }
        }


        System.out.print("Enter Crop Name: ");
        String cropName = sc.nextLine();

        System.out.print("Enter Crop Type: ");
        String cropType = sc.nextLine();


        double quantity;

        while (true) {

            System.out.print("Enter Quantity (kg): ");
            quantity = sc.nextDouble();

            if (quantity <= 0) {

                System.out.println(
                    Color.RED +
                    "Quantity must be greater than 0."
                    + Color.RESET
                );

            } else {

                break;
            }
        }


        double price;

        while (true) {

            System.out.print("Enter Price per kg: ");
            price = sc.nextDouble();

            if (price <= 0) {

                System.out.println(
                    Color.RED +
                    "Price must be greater than 0."
                    + Color.RESET
                );

            } else {

                break;
            }
        }

        sc.nextLine();


        Crop crop = new Crop(
                cropId,
                cropName,
                cropType,
                quantity,
                price,
                seller.getId()
        );


        seller.addCrop(crops, crop);

        System.out.println(
            Color.GREEN +
            "Crop ID " + cropId +
            " assigned successfully."
            + Color.RESET
        );
    }


    // =====================================================
    // VIEW MY CROPS
    // =====================================================
    static void viewMyCrops(Seller seller) {

        System.out.println(
            Color.YELLOW +
            "\n===== MY CROPS ====="
            + Color.RESET
        );

        boolean found = false;

        for (Crop crop : crops) {

            if (crop.getSellerId() == seller.getId()) {

                System.out.println(
                    Color.CYAN +
                    "\nCrop ID      : " +
                    crop.getCropId()
                    + Color.RESET
                );

                System.out.println(
                    "Crop Name    : " +
                    crop.getCropName()
                );

                System.out.println(
                    "Crop Type    : " +
                    crop.getCropType()
                );

                System.out.println(
                    "Quantity     : " +
                    crop.getQuantity() +
                    " kg"
                );

                System.out.println(
                    "Price per Kg : " +
                    crop.getPricePerKg()
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                Color.RED +
                "You have not added any crops."
                + Color.RESET
            );
        }
    }


    // =====================================================
    // UPDATE CROP PRICE
    // =====================================================
    static void updateCropPrice(Seller seller) {

        System.out.println(
            Color.YELLOW +
            "\n===== UPDATE CROP PRICE ====="
            + Color.RESET
        );

        System.out.print("Enter Crop ID: ");
        int cropId = sc.nextInt();

        Crop crop = findCrop(cropId);

        if (crop == null) {

            System.out.println(
                Color.RED +
                "Crop not found!"
                + Color.RESET
            );

            return;
        }

        if (crop.getSellerId() != seller.getId()) {

            System.out.println(
                Color.RED +
                "This crop does not belong to you!"
                + Color.RESET
            );

            return;
        }

        System.out.print(
            "Enter New Price per kg: "
        );

        double price = sc.nextDouble();
        sc.nextLine();

        if (price <= 0) {

            System.out.println(
                Color.RED +
                "Invalid price!"
                + Color.RESET
            );

            return;
        }

        crop.setPricePerKg(price);

        System.out.println(
            Color.GREEN +
            "Crop price updated successfully!"
            + Color.RESET
        );
    }


    // =====================================================
    // VIEW AVAILABLE CROPS
    // =====================================================
    static void viewAvailableCrops() {

        System.out.println(
            Color.YELLOW +
            "\n===== AVAILABLE CROPS ====="
            + Color.RESET
        );

        boolean found = false;

        for (Crop crop : crops) {

            if (crop.getQuantity() > 0) {

                System.out.println(
                    Color.CYAN +
                    "\nCrop ID      : " +
                    crop.getCropId()
                    + Color.RESET
                );

                System.out.println(
                    "Crop Name    : " +
                    crop.getCropName()
                );

                System.out.println(
                    "Crop Type    : " +
                    crop.getCropType()
                );

                System.out.println(
                    "Quantity     : " +
                    crop.getQuantity() +
                    " kg"
                );

                System.out.println(
                    "Price per Kg : " +
                    crop.getPricePerKg()
                );

                System.out.println(
                    "Seller ID    : " +
                    crop.getSellerId()
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                Color.RED +
                "No crops available."
                + Color.RESET
            );
        }
    }


    // =====================================================
    // SEARCH CROP
    // =====================================================
    static void searchCrop() {

        System.out.println(
            Color.YELLOW +
            "\n===== SEARCH CROP ====="
            + Color.RESET
        );

        System.out.print("Enter Crop Name: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Crop crop : crops) {

            if (crop.getCropName()
                    .equalsIgnoreCase(name)
                    && crop.getQuantity() > 0) {

                System.out.println(
                    Color.CYAN +
                    "\nCrop ID      : " +
                    crop.getCropId()
                    + Color.RESET
                );

                System.out.println(
                    "Crop Name    : " +
                    crop.getCropName()
                );

                System.out.println(
                    "Crop Type    : " +
                    crop.getCropType()
                );

                System.out.println(
                    "Quantity     : " +
                    crop.getQuantity() +
                    " kg"
                );

                System.out.println(
                    "Price per Kg : " +
                    crop.getPricePerKg()
                );

                System.out.println(
                    "Seller ID    : " +
                    crop.getSellerId()
                );

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                Color.RED +
                "Crop not found!"
                + Color.RESET
            );
        }
    }


    // =====================================================
    // BUY CROP
    // =====================================================
    static void buyCrop(Dealer dealer) {

        System.out.println(
            Color.YELLOW +
            "\n===== BUY CROP ====="
            + Color.RESET
        );

        System.out.print("Enter Crop ID: ");
        int cropId = sc.nextInt();

        Crop crop = findCrop(cropId);

        if (crop == null) {

            System.out.println(
                Color.RED +
                "Crop not found!"
                + Color.RESET
            );

            return;
        }

        if (crop.getQuantity() <= 0) {

            System.out.println(
                Color.RED +
                "Crop is out of stock."
                + Color.RESET
            );

            return;
        }

        System.out.print(
            "Enter Quantity to Buy (kg): "
        );

        double quantity = sc.nextDouble();

        if (quantity <= 0) {

            System.out.println(
                Color.RED +
                "Invalid quantity!"
                + Color.RESET
            );

            return;
        }

        if (quantity > crop.getQuantity()) {

            System.out.println(
                Color.RED +
                "Only " +
                crop.getQuantity() +
                " kg is available."
                + Color.RESET
            );

            return;
        }

        double total =
                quantity *
                crop.getPricePerKg();

        crop.setQuantity(
            crop.getQuantity() - quantity
        );


        int transactionId =
                generateTransactionId();


        Transaction transaction =
                new Transaction(
                    transactionId,
                    dealer.getId(),
                    crop.getCropId(),
                    crop.getCropName(),
                    quantity,
                    total
                );


        transactions.add(transaction);


        System.out.println(
            Color.GREEN +
            "\n========== PURCHASE BILL =========="
            + Color.RESET
        );

        System.out.println(
            Color.CYAN +
            "Transaction ID : " +
            transactionId
            + Color.RESET
        );

        System.out.println(
            "Dealer ID      : " +
            dealer.getId()
        );

        System.out.println(
            "Dealer Name    : " +
            dealer.getName()
        );

        System.out.println(
            "Crop ID        : " +
            crop.getCropId()
        );

        System.out.println(
            "Crop Name      : " +
            crop.getCropName()
        );

        System.out.println(
            "Quantity       : " +
            quantity +
            " kg"
        );

        System.out.println(
            "Price per Kg   : " +
            crop.getPricePerKg()
        );

        System.out.println(
            "-----------------------------------"
        );

        System.out.println(
            Color.GREEN +
            "TOTAL          : " +
            total
            + Color.RESET
        );

        System.out.println(
            Color.GREEN +
            "==================================="
            + Color.RESET
        );

        System.out.println(
            Color.GREEN +
            "\nPurchase successful!"
            + Color.RESET
        );
    }


    // =====================================================
    // DYNAMIC TRANSACTION ID
    // =====================================================
    static int generateTransactionId() {

        int id;

        do {

            id =
                (int)(Math.random() * 900000)
                + 100000;

        } while (transactionIdExists(id));

        return id;
    }


    // =====================================================
    // TRANSACTION HISTORY
    // =====================================================
    static void transactionHistory(Dealer dealer) {

        System.out.println(
            Color.YELLOW +
            "\n===== TRANSACTION HISTORY ====="
            + Color.RESET
        );

        boolean found = false;

        for (Transaction transaction :
                transactions) {

            if (transaction.getDealerId()
                    == dealer.getId()) {

                transaction.displayTransaction();

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                Color.RED +
                "No transactions found."
                + Color.RESET
            );
        }
    }


    // =====================================================
    // FIND CROP
    // =====================================================
    static Crop findCrop(int id) {

        for (Crop crop : crops) {

            if (crop.getCropId() == id) {

                return crop;
            }
        }

        return null;
    }


    // =====================================================
    // SELLER MENU
    // =====================================================
    static void sellerMenu(Seller seller) {

        int choice;

        do {

            System.out.println(
                Color.BLUE +
                "\n================================"
                + Color.RESET
            );

            System.out.println(
                Color.BLUE +
                "          SELLER MENU"
                + Color.RESET
            );

            System.out.println(
                Color.BLUE +
                "================================"
                + Color.RESET
            );

            System.out.println(
                Color.GREEN +
                "Logged in as: " +
                seller.getName()
                + Color.RESET
            );

            System.out.println(
                "\n1. Add Crop"
            );

            System.out.println(
                "2. View My Crops"
            );

            System.out.println(
                "3. Update Crop Price"
            );

            System.out.println(
                "4. View My Details"
            );

            System.out.println(
                "5. Logout"
            );

            System.out.print(
                "\nEnter your choice: "
            );

            choice = sc.nextInt();
            sc.nextLine();


            switch (choice) {

                case 1:
                    addCrop(seller);
                    break;

                case 2:
                    viewMyCrops(seller);
                    break;

                case 3:
                    updateCropPrice(seller);
                    break;

                case 4:
                    seller.displayDetails();
                    break;

                case 5:
                    System.out.println(
                        Color.GREEN +
                        "Seller logged out."
                        + Color.RESET
                    );
                    break;

                default:
                    System.out.println(
                        Color.RED +
                        "Invalid choice!"
                        + Color.RESET
                    );
            }

        } while (choice != 5);
    }


    // =====================================================
    // DEALER MENU
    // =====================================================
    static void dealerMenu(Dealer dealer) {

        int choice;

        do {

            System.out.println(
                Color.BLUE +
                "\n================================"
                + Color.RESET
            );

            System.out.println(
                Color.BLUE +
                "          DEALER MENU"
                + Color.RESET
            );

            System.out.println(
                Color.BLUE +
                "================================"
                + Color.RESET
            );

            System.out.println(
                Color.GREEN +
                "Logged in as: " +
                dealer.getName()
                + Color.RESET
            );

            System.out.println(
                "\n1. View Available Crops"
            );

            System.out.println(
                "2. Search Crop"
            );

            System.out.println(
                "3. Buy Crop"
            );

            System.out.println(
                "4. Transaction History"
            );

            System.out.println(
                "5. View My Details"
            );

            System.out.println(
                "6. Logout"
            );

            System.out.print(
                "\nEnter your choice: "
            );

            choice = sc.nextInt();
            sc.nextLine();


            switch (choice) {

                case 1:
                    viewAvailableCrops();
                    break;

                case 2:
                    searchCrop();
                    break;

                case 3:
                    buyCrop(dealer);
                    break;

                case 4:
                    transactionHistory(dealer);
                    break;

                case 5:
                    dealer.displayDetails();
                    break;

                case 6:
                    System.out.println(
                        Color.GREEN +
                        "Dealer logged out."
                        + Color.RESET
                    );
                    break;

                default:
                    System.out.println(
                        Color.RED +
                        "Invalid choice!"
                        + Color.RESET
                    );
            }

        } while (choice != 6);
    }


    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {

        int choice;

        do {

            System.out.println(
                Color.BLUE +
                "\n=========================================="
                + Color.RESET
            );

            System.out.println(
                Color.BLUE +
                "       CROP SELLER & DEALER SYSTEM"
                + Color.RESET
            );

            System.out.println(
                Color.BLUE +
                "=========================================="
                + Color.RESET
            );

            System.out.println(
                Color.GREEN +
                "1. Seller"
                + Color.RESET
            );

            System.out.println(
                Color.GREEN +
                "2. Dealer"
                + Color.RESET
            );

            System.out.println(
                Color.RED +
                "3. Exit"
                + Color.RESET
            );

            System.out.print(
                "\nSelect User Type: "
            );

            choice = sc.nextInt();
            sc.nextLine();


            switch (choice) {

                case 1:
                    sellerAccess();
                    break;

                case 2:
                    dealerAccess();
                    break;

                case 3:

                    System.out.println(
                        Color.GREEN +
                        "\nThank you for using the application!"
                        + Color.RESET
                    );

                    break;

                default:

                    System.out.println(
                        Color.RED +
                        "Invalid choice!"
                        + Color.RESET
                    );
            }

        } while (choice != 3);

        sc.close();
    }
}