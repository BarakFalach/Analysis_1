public class Product extends myObject{
    private String id;
    private String name;
    private Supplier mySupplier;
    private int price;
    private PremiumAccount premiumAccount;

    public Product(String id, String name, Supplier mySupplier, int price) {
        this.id = id;
        this.name = name;
        this.mySupplier = mySupplier;
        this.price = price;
        this.premiumAccount = null;
    }

    public void updateSupplier(Supplier newSupplier)
    {
        if(mySupplier!=null)
            removeFromSupplier();
        mySupplier = newSupplier;
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

    public String getId() {
        return id;
    }

}
