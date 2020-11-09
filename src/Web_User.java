public class Web_User extends myObject{
    private String login_id;
    private String password;
    private UserState state;
    private Customer customer;
    private ShoppingCart shoppingCart;

    public Web_User(String id, String pass, UserState userState) {
        this.login_id = id;
        this.password = pass;
        this.state = userState;
        main.addToObjects(id, this);
    }

    public String getPassword() {
        return password;
    }

    public void setUserState(UserState userState) {
        this.state = userState;
    }

    public UserState getUserState() {
        return state;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
        customer.setWeb_user(this);
    }

    public Customer getCustomer(){
        return this.customer;
    }

    public void setShoppingCart(ShoppingCart shoppingCart){
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart(){
        return this.shoppingCart;
    }

    @Override
    public String getId() {
        return login_id;
    }

    @Override
    public String toString() {
        return "Web_User{" +
                "login_id='" + login_id + '\'' +
                ", customer=" + customer.getId() +
                ", shoppingCart=" + shoppingCart.getId() +
                '}';
    }
    @Override
    public void deleteObject() {
        shoppingCart.deleteObject();
        customer.deleteObject();

    }
}