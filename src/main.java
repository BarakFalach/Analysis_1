import java.util.ArrayList;
import java.util.Scanner;

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
                System.out.println("Is this a Premium account?[y/n]");
                if(scan.nextLine().equals("y")){
                    //web_users.add(new PremiumAccount())
                }
                web_users.add(new Web_User(id,pass,UserState.New));
                addeduser = false;
                System.out.println("\t User added!");
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

    private static void initiateSystem() {
        Supplier s = new Supplier("123", "moshe");
        Product p0 = new Product("Bamba", "Bamba", s, 10);
        Product p1 = new Product("Ramen", "Ramen", s, 20);

        p0.updateSupplier(s);
        p1.updateSupplier(s);
        s.addProduct(p0);
        s.addProduct(p1);

        suppliers.add(s);
        products.add(p0);
        products.add(p1);
    }

    public static void login_WebUser(Scanner scan){
        boolean loggeduser = true;
        while(loggeduser){
            System.out.println("Please id to login:");
            String id = scan.nextLine();
            if(web_users.stream().anyMatch(user -> user.getLogin_id().equals(id))){
                System.out.println("Please enter password for "+id+" :");
                String pass = scan.nextLine();
                if(web_users.stream().anyMatch(user -> user.getPassword().equals(pass))){
                    //new menu
                    // Make order
                    // Display order
                    // Link Product(premium only)
                    // Logout WebUser
                }
                else{
                    System.out.println("Wrong Password! please try again");
                    loggeduser = false;
                }
            }
            else {
                System.out.println("User "+id+" dosen't exist please try again");
            }
        }
    }

    public UserState logoutUser(String id){
        if(web_users.stream().anyMatch(o -> o.getLogin_id().equals(id))) {
            web_users.stream().filter(o -> o.getLogin_id().equals(id)).findFirst().get().setUserState(UserState.Blocked);
        }
        return UserState.Blocked;
    }

}
