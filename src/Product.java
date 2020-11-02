public class Product {
    private String id;
    private String name;
    private Supplier mySupplier;

    public Product(String _id, String _name)
    {
        id = _id;
        name = _name;
        mySupplier = null;
    }
    public void updateSupplier(Supplier newSupplier)
    {
        mySupplier = newSupplier;
    }
    public String toString()
    {
        return "Product ID: " + id.toString() +" Product Name: " + name;
    }
}
