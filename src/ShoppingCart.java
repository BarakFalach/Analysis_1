import java.util.ArrayList;
import java.util.Date;

public class ShoppingCart extends myObject {
    private Date created;
    private String id;
    private Account account;
    private Web_User web_user;
    private ArrayList<LineItem> lineItemList;

    public ShoppingCart(String id, Date created, Account account) {
        this.id = id;
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
        return this.lineItemList.stream().filter(o -> o.getId().equals(id)).findFirst().get();
    }

    public void addLineItem(LineItem lineItem) {
        this.lineItemList.add(lineItem);
    }

    public String getId() {
        return id;
    }

    @Override
    public String showFullObject() {
        return null;
    }


}
