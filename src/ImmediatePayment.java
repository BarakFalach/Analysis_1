public class ImmediatePayment extends Payment {
    public ImmediatePayment(String id, String details, Order myOrder, Account myAccount) {
        super(id, details, myOrder, myAccount);
    }
    private boolean phoneConfirmation;

    public boolean isPhoneConfirmation() {
        return phoneConfirmation;
    }
}
