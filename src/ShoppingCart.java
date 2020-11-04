import java.util.ArrayList;
import java.util.Date;

public class ShoppingCart {
    private Date created;
    private Account account;
    private Web_User web_user;
    private ArrayList<LineItem> lineItemList;

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

    public LineItem getLineItem(String id) {
        return this.lineItemList.stream().filter(o -> o.getID().equals(id)).findFirst().get();
    }

    public void setLineItem(LineItem lineItem) {
        this.lineItemList.add(lineItem);
    }

}
