import java.util.Date;

public abstract class Payment extends myObject{
    protected String id;
    protected Date paid;
    protected float total;
    protected String details;
    protected Order myOrder;
    protected Account myAccount;

    public Payment(String id, String details, Order myOrder, Account myAccount) {
        this.id = id;
        this.details = details;
        this.myOrder = myOrder;
        this.total = myOrder.getTotal();
        this.myAccount = myAccount;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", paid=" + paid +
                ", total=" + total +
                ", details='" + details + '\'' +
                ", myOrder=" + myOrder.getId() +
                ", myAccount=" + myAccount.getId() +
                '}';
    }
}
