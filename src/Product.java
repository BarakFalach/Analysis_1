public class Product {
    private String id;
    private String name;
    private Supplier mySupplier;
    private int price;

    public Product(String id, String name, Supplier mySupplier, int price) {
        this.id = id;
        this.name = name;
        this.mySupplier = mySupplier;
        this.price = price;
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


    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mySupplier=" + mySupplier +
                ", price=" + price +
                '}';
    }

    public int getPrice() { return price ;}

    public String getId() {
        return id;
    }

    public String getName() { return name; }
}
