import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Optional;

public class main {

    private static ArrayList<Web_User> web_users = new ArrayList<Web_User>();
    private ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
    private ArrayList<Product> products = new ArrayList<Product>();

    public static void main(String[] args) {
        String choice = null;
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
    

    public UserState add_WebUser(String id, String password){
        web_users.add(new Web_User(id, password, UserState.New));
        return UserState.New;
    }

    public boolean remove_WebUser(String id){
        return web_users.removeIf(o -> o.getLogin_id().equals(id));
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
