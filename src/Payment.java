import java.util.Date;

public abstract class Payment {
    protected String id;
    protected Date paid;
    protected float total; //TODO:: what is total!
    protected String details;
    protected Order myOrder;
    protected Account myAccount;

    public Payment(String id, String details, Order myOrder, Account myAccount) {
        this.id = id;
        this.details = details;
        this.myOrder = myOrder;
        this.myAccount = myAccount;
    }


}
