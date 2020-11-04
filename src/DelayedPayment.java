import java.util.Date;

public class DelayedPayment extends Payment {
    Date paymentDate;

    public DelayedPayment(String id, Order myOrder, Account myAccount) {
        super(id ,myOrder, myAccount);
    }

    public Date getPaymentDate() {
        return paymentDate;
    }
}
