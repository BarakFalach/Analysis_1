import java.util.ArrayList;
import java.util.Date;

public class ShoppingCart extends myObject {
    private Date created;
    private String id;
    private Account account;
    private Web_User web_user;
    private ArrayList<LineItem> lineItemList;

    public ShoppingCart(String id, Web_User user) {
        this.id = id;
        this.web_user = user;
        this.created = new Date();
        this.lineItemList = new ArrayList<>();
        user.setShoppingCart(this);
        main.addToObjects(this);
    }

    public Date getCreated() {
        return this.created;
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
    public String toString() {
        StringBuilder toPrint = new StringBuilder("ShoppingCart{" +
                "created=" + created +
                ", id='" + id + '\'' +
                ", account=" + account.getId() +
                ", web_user=" + web_user.getId());
        toPrint.append("lineItems=");
        for (LineItem lineItem : lineItemList){
            toPrint.append(lineItem.getId());
        }
        toPrint.append('}');
        return toPrint.toString();
    }

    public void removeLineItem(LineItem lineItem){
        this.lineItemList.remove(lineItem);
    }

    @Override
    public void deleteObject() {
        main.removeFromObjects(this);
    }
}
