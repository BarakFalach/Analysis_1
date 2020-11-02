public class Web_User {
    private String login_id;
    private String password;
    private UserState state;

    public Web_User (String id, String pass, UserState userState){
        this.login_id = id;
        this.password = pass;
        this.state = userState;
    }

    public String getLogin_id(){
        return login_id;
    }

    public UserState getUserState(){
        return state;
    }
}
