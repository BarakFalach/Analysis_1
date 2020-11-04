import java.util.ArrayList;
import java.util.Date;

public class Account extends myObject{
    protected String id;
    protected String billing_address;
    protected boolean is_closed;
    protected Date open;
    protected Date closed;
    protected int balance;
    protected ShoppingCart shoppingCart;
    protected Customer customer;
    protected ArrayList<Order> ordersList;
    protected ArrayList<Payment> paymentsList;

    public Account(String id, String billing_address, boolean is_closed, int balance) {
        this.id = id;
        this.billing_address = billing_address;
        this.is_closed = is_closed;
        this.open = new Date();
        this.closed = null;
        this.balance = balance;
        this.ordersList = new ArrayList<>();
        this.paymentsList = new ArrayList<>();
    }

    public void addOrder(Order newOrder){
        ordersList.add(newOrder);
    }
    public void addPayment(Payment newPayment){
        paymentsList.add(newPayment);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(String billing_address) {
        this.billing_address = billing_address;
    }

    public boolean isIs_closed() {
        return is_closed;
    }

    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
    }

    public Date getOpen() {
        return open;
    }

    public void setOpen(Date open) {
        this.open = open;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getLastOrder(){
        if(ordersList.isEmpty())
            return "No order to show";
        return ordersList.get(ordersList.size()-1).toString();
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", billing_address='" + billing_address + '\'' +
                ", is_closed=" + is_closed +
                ", open=" + open +
                ", closed=" + (closed!=null? closed.toString(): "open") +
                ", balance=" + balance +
                ", shoppingCart=" + shoppingCart.getId() +
                ", customer=" + customer.getId() +
                ", ordersList=" + ordersList.toString() +
                ", paymentsList=" + paymentsList.toString() +
                '}';
    }
}
