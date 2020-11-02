import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String number;
    private Date ordered;
    private Date shipped;
    private Address ship_to;
    private  OrderStatus status;
    private float total;
    private ArrayList<LineItem> lineItemList;
    private ArrayList<Payment> paymentsList;
    private Account myAccount;

    public Order(String number, Date ordered,Account myAccount) {
        this.myAccount = myAccount;
        this.number = number;
        this.ordered = ordered;
//        this.ship_to = myAccount.getAddress(); //TODO:: update address
        this.status = OrderStatus.New;
        lineItemList = new ArrayList<>();
        paymentsList = new ArrayList<>();
    }

    public void setShipped(Date shipped) {
        this.shipped = shipped;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void addLineItem(LineItem newLineItem){
        lineItemList.add(newLineItem);
        this.total+=newLineItem.getPrice();
    }

    public void addPayment(Payment newPayment){
        paymentsList.add(newPayment);
    }
}



