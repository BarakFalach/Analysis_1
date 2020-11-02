import java.util.Date;

public class ShoppingCart {
    private Date created;
    private Account account;
    private Web_User web_user;
    private LineItem lineItem;

    public ShoppingCart(Date created, Account account) {
        this.created = created;
        this.account = account;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Web_User getWeb_user() {
        return web_user;
    }

    public void setWeb_user(Web_User web_user) {
        this.web_user = web_user;
    }

    public LineItem getLineItem() {
        return lineItem;
    }

    public void setLineItem(LineItem lineItem) {
        this.lineItem = lineItem;
    }
}
