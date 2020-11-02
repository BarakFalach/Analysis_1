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
            System.out.println("1\t Add User to System");
            System.out.println("2\t Remove User from System");
            System.out.println("3\t Login");
            System.out.println("9\t quit");

            choice = scan.nextLine();
            switch (choice) {
                case "1":
                    //Sub Menu for Adding
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
                            web_users.add(new Web_User(id,pass,UserState.New));
                            addeduser = false;
                            System.out.println("\t User added!");
                        }
                    }
                    break;
                case "2":
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
                        }
                    }
                    break;
                case "3":

                    break;
            }
        } while (!choice.equals("9")); // end of loop
    }

    private static void initiateSystem(){
        Supplier s = new Supplier("123", "moshe");
        Product p0 = new Product("Bamba", "Bamba");
        Product p1 = new Product("Ramen", "Ramen");

        p0.updateSupplier(s);
        p1.updateSupplier(s);
        s.addProduct(p0);
        s.addProduct(p1);

        suppliers.add(s);
        products.add(p0);
        products.add(p1);
    }

    public UserState login_WebUser(String id, String password){
        if(web_users.stream().anyMatch(o -> o.getLogin_id().equals(id) && o.getPassword().equals(password))) {
            web_users.stream().filter(o -> o.getLogin_id().equals(id)).findFirst().get().setUserState(UserState.Active);
        }
        return UserState.Active;
    }

    public UserState logoutUser(String id){
        if(web_users.stream().anyMatch(o -> o.getLogin_id().equals(id))) {
            web_users.stream().filter(o -> o.getLogin_id().equals(id)).findFirst().get().setUserState(UserState.Blocked);
        }
        return UserState.Blocked;
    }

}
