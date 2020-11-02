import java.util.Date;

public class DelayedPayment extends Payment {
    Date paymentDate;

    public DelayedPayment(String id, String details, Order myOrder, Account myAccount) {
        super(id, details, myOrder, myAccount);
    }

    public Date getPaymentDate() {
        return paymentDate;
    }
}
