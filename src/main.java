import java.util.*;

public class main {

    private static ArrayList<Web_User> web_users = new ArrayList<>();
    private static ArrayList<Supplier> suppliers = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();

    public static void main(String[] args) {

        initiateSystem();

        String choice;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("please choose one of the following numbers:");
            System.out.println("1\t Add WebUser");
            System.out.println("2\t Remove WebUser");
            System.out.println("3\t Login WebUser");
            System.out.println("4\t Add Product");
            System.out.println("5\t Delete Product");
            System.out.println("6\t Show all objects");
            System.out.println("7\t Show Object ID");
            System.out.println("9\t quit");

            choice = scan.nextLine();
            switch (choice) {
                case "1": //Add User
                    add_WebUser(scan);
                    break;
                case "2": //Remove
                    remove_WebUser(scan);
                    break;
                case "3": //Login
                    login_WebUser(scan);
                    break;
                case "4": //Add Product
                    break;
                case "5": //Delete Product
                    break;
                case "6": //Show all objects
                    break;
                case "7": //Show Object ID
                    break;
            }
        } while (!choice.equals("9")); // end of loop
    }


    public static void add_WebUser(Scanner scan){
        boolean addeduser = true;
        while(addeduser) {
            System.out.println("Please Enter id of the new User:");
            String id = scan.nextLine();
            if(web_users.stream().anyMatch(user -> user.getLogin_id().equals(id))){
                System.out.println("User Already exist please enter another id");
            }
            else {
                System.out.println("Please Enter Password for "+ id + " :");
                String pass = scan.nextLine();
                System.out.println("Please Enter city:");
                String city = scan.nextLine();
                System.out.println("Please Enter street:");
                String street = scan.nextLine();
                System.out.println("Please Enter number of apartment:");
                int number = scan.nextInt();
                System.out.println("Please Enter Phone number:");
                String phone = scan.nextLine();
                System.out.println("Please Enter email:");
                String email = scan.nextLine();
                System.out.println("Please Enter billing address:");
                String billing_address = scan.nextLine();
                System.out.println("Please Enter account balnace:");
                int balance = scan.nextInt();
                System.out.println("Is this a Premium account?[y/n]");
                Customer customer = new Customer(id,new Address(city,street,number),phone,email);
                Web_User user = new Web_User(id,pass,UserState.New);
                Account account;
                if(scan.nextLine().equals("y")){
                    account = new PremiumAccount(id,billing_address,false,balance);
                }
                else account = new Account(id,billing_address,false,balance);
                customer.setAccount(account);
                user.setCustomer(customer);
                web_users.add(user);
                System.out.println("\t User added!");
                addeduser = false;
            }
        }
    }

    public static void remove_WebUser(Scanner scan){
        boolean removeduser = true;
        while(removeduser) {
            System.out.println("Please Enter id to remove:");
            String id = scan.nextLine();
            if(web_users.stream().anyMatch(user -> user.getLogin_id().equals(id))){
                web_users.removeIf(o->o.getLogin_id().equals(id));
                System.out.println("\t User Removed!");
                removeduser = false;
            }
            else {
                System.out.println("User "+id+" dosen't exist please try again");
                System.out.println("do you want to try again? [y/n]");
                String again = scan.nextLine();
                if(again.equals("n")) removeduser = false;
            }
        }
    }

    private static void initiateSystem(){
        Supplier s = new Supplier("123", "moshe");
        Product p0 = new Product("Bamba", "Bamba",s,10);
        Product p1 = new Product("Ramen", "Ramen",s,20);

        p0.updateSupplier(s);
        p1.updateSupplier(s);
        s.addProduct(p0);
        s.addProduct(p1);

        suppliers.add(s);
        products.add(p0);
        products.add(p1);
    }

    public static void login_WebUser(Scanner scan) {
        boolean loggeduser = true;
        boolean loginmenu = true;
        while (loggeduser) {
            System.out.println("Please id to login:");
            String id = scan.nextLine();
            if (web_users.stream().anyMatch(user -> user.getLogin_id().equals(id))) {
                System.out.println("Please enter password for " + id + " :");
                String pass = scan.nextLine();
                if (web_users.stream().anyMatch(user -> user.getPassword().equals(pass))) {
                    //new menu
                    // Make order
                    // Display order
                    // Link Product(premium only)
                    // Logout WebUser
                    do {
                        System.out.println("1-\t Make order");
                        System.out.println("2-\t Display order");
                        System.out.println("3-\t Link Product (Premium Only)");
                        System.out.println("4-\t Logout");
                        String choice = scan.nextLine();
                        switch (choice) {
                            case "1": //Make Order
                                System.out.println("Making Order");
                                break;
                            case "2": //Display Order
                                System.out.println("Displaying Order");
                                break;
                            case "3": //Link Product
                                System.out.println("Linking Order");
                                break;
                            case "4": //Logout
                                logoutUser(id);
                                loginmenu = false;
                                break;
                        }

                    } while (loginmenu);
                } else { //Wrong pass or Logout
                    if(!loginmenu) System.out.println("Logged Out");
                    else System.out.println("Wrong Password!");
                    loggeduser = false;
                }
            } else {
                System.out.println("User " + id + " dosen't exist please try again");
            }
        }
    }

    public static void logoutUser(String id){
        if(web_users.stream().anyMatch(o -> o.getLogin_id().equals(id))) {
            web_users.stream().filter(o -> o.getLogin_id().equals(id)).findFirst().get().getCustomer().getAccount().setClosed(new Date());
            web_users.stream().filter(o -> o.getLogin_id().equals(id)).findFirst().get().getCustomer().getAccount().setIs_closed(true);
            web_users.stream().filter(o -> o.getLogin_id().equals(id)).findFirst().get().setUserState(UserState.Blocked);
        }
    }

}
