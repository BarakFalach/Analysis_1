import java.util.ArrayList;

public class Product extends myObject{
    private String id;
    private String name;
    private Supplier mySupplier;
    private int price;
    private PremiumAccount premiumAccount;
    private int quantity;
    private ArrayList<LineItem> lineItems;

    public Product(String id, String name, Supplier mySupplier, int price) {
        this.id = id;
        this.name = name;
        this.mySupplier = mySupplier;
        this.price = price;
        this.premiumAccount = null;
        this.lineItems = new ArrayList<>();
        main.addToObjects(this);
    }

    public void updateSupplier(Supplier newSupplier) {
        if(mySupplier!=null)
            removeFromSupplier();
        mySupplier = newSupplier;
    }

    public void addItem(LineItem lineItem){
        this.lineItems.add(lineItem);
    }

    @Override
    public void deleteObject(){
        for (LineItem item: this.lineItems)
            item.deleteObject();
        if (this.premiumAccount!=null)
            this.premiumAccount.removeProduct(this);
        this.mySupplier.removeProduct(this);
        main.removeFromObjects(this);
    }

    public void removeFromSupplier() {
        mySupplier.removeProduct(this);
    }

    public Supplier getMySupplier(){
        return mySupplier;
    }

    public void setPremiumAccount(PremiumAccount premiumAccount){
        this.premiumAccount = premiumAccount;
    }

    public void removeLineItem(LineItem item){
        lineItems.remove(item);
    }

    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mySupplier=" + mySupplier.getId() +
                ", price=" + price +
                '}';
    }

    public int getPrice() { return price ;}

    public void setPrice(int price){this.price = price;}

    public void setQuantity(int quantity){this.quantity = quantity;}

    public int getQuantity() {
        return quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
