import java.util.ArrayList;
import java.util.Date;

public class Order extends myObject{
    private String number;
    private Date ordered;
    private Date shipped;
    private Address ship_to;
    private  OrderStatus status;
    private float total;
    private ArrayList<LineItem> lineItemList;
    private ArrayList<Payment> paymentsList;
    private Account myAccount;

    public Order(String number, Date ordered, Account myAccount) {
        myAccount.addOrder(this);
        this.myAccount = myAccount;
        this.number = number;
        this.ordered = ordered;
        this.ship_to = myAccount.getCustomer().getAddress();
        this.status = OrderStatus.New;
        lineItemList = new ArrayList<>();
        paymentsList = new ArrayList<>();
        main.addToObjects(this);
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

    public void setMyAccount(Account myAccount) {
        this.myAccount = myAccount;
    }

    public void addPayment(Payment newPayment){
        paymentsList.add(newPayment);
    }

    public Account getMyAccount() {
        return myAccount;
    }

    @Override
    public String toString() {
        StringBuilder toPrint = new StringBuilder("Order{" +
                "number='" + number + '\'' +
                ", ordered=" + ordered +
                ", shipped=" + shipped +
                ", ship_to=" + ship_to +
                ", status=" + status +
                ", total=" + total +
                ", myAccount=" + myAccount.getId());
        toPrint.append("lineItems=");
        for (LineItem lineItem : lineItemList){
            toPrint.append(lineItem.getId());
        }
        toPrint.append(", payments=");
        for (Payment payment:this.paymentsList){
            toPrint.append(payment.getId()).append(" ");
        }
        toPrint.append("}");
        return toPrint.toString();
    }

    @Override
    public String getId() {
        return number;
    }

    public float getTotal() {
        return total;
    }

    @Override
    public void deleteObject() {
        while (this.lineItemList.size()!=0)
            this.lineItemList.get(0).deleteObject();
        main.removeFromObjects(this);
    }

    public void removeLineItem(LineItem lineItem){
        lineItemList.remove(lineItem);
    }
}



