import java.util.ArrayList;

public class main {

    private ArrayList<Web_User> web_users = new ArrayList<Web_User>();
    private ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
    private ArrayList<Product> products = new ArrayList<Product>();

    public static void main(String[] args) {
    }

    public UserState add_WebUser(String id){
        if(web_users.stream().anyMatch(user -> user.getLogin_id().equals(id))){
            return UserState.Banned;
        }

        // Tom - insert password;
        String password = "223";

        Web_User newUser = new Web_User(id, password, UserState.New);
        return UserState.New;
    }

    public void remove_WebUser(String id){
        if(web_users.stream().anyMatch(user -> user.getLogin_id().equals(id)))
            web_users.removeIf(o->o.getLogin_id().equals(id));
    }

    public void login_WebUser(int id){

    }


}
