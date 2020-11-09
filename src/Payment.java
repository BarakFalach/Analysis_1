import java.util.Date;

public abstract class Payment extends myObject{
    protected String id;
    protected Date paid;
    protected float total;
    protected String details;
    protected Order myOrder;
    protected Account myAccount;

    public Payment(String id, Order myOrder, Account myAccount) {
        this.id = id;
        this.details = myOrder.getId();
        this.myOrder = myOrder;
        this.total = myOrder.getTotal();
        this.myAccount = myAccount;
        main.addToObjects(this);
    }

    @Override
    public String getId() {
        return id;
    }

    public void setPaid(Date paid) {
        this.paid = paid;
    }

    public Date getPaid() {
        return paid;
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

    @Override
    public void deleteObject(){
        main.removeFromObjects(this);
    }
}
