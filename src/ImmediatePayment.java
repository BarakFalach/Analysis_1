public class ImmediatePayment extends Payment {
    public ImmediatePayment(String id, Order myOrder, Account myAccount) {
        super(id, myOrder, myAccount);
    }
    private boolean phoneConfirmation;

    public boolean isPhoneConfirmation() {
        return phoneConfirmation;
    }

}
