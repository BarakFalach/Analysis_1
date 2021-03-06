import java.util.ArrayList;

public class Supplier extends myObject implements productHolder{
    private String id;
    private String name;
    private ArrayList<Product> productList;

    public Supplier(String _id, String _name){
        id = _id;
        name = _name;
        productList = new ArrayList<>();
        main.addToObjects(this);
    }
    public void addProduct(Product newProduct)
    {
        productList.add(newProduct);
    }

    public String getId() {
        return id;
    }


    public void removeProduct(Product productToDelete)
    {
        productList.remove(productToDelete);
    }
    public int indexOfProduct(Product product)
    {
        return productList.indexOf(product);
    }

    @Override
    public Product getProductByName(String name)
    {
        for (Product product : productList){
            if (product.getName().equals(name))
                return product;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return "Supplier{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", productList=" + productList.toString() +
                '}';
    }

    @Override
    public void deleteObject() {

    }

    @Override
    public ArrayList<Product> getProducts() {
        return this.productList;
    }
}
