import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class main {

    private static ArrayList<Web_User> web_users = new ArrayList<Web_User>();
    private static ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
    private static ArrayList<Product> products = new ArrayList<Product>();

    public static void main(String[] args) {


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
