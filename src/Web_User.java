public class Web_User {
    private String login_id;
    private String password;
    private UserState state;

    public Web_User(String id, String pass, UserState userState) {
        this.login_id = id;
        this.password = pass;
        this.state = userState;
    }

    public String getLogin_id() {
        return login_id;
    }

    public String getPassword() {
        return password;
    }

    public void logout() {
        this.state = UserState.Blocked;
    }

    public void setUserState(UserState userState) {
        this.state = userState;
    }

    public UserState getUserState() {
        return state;
    }
}