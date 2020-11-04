import java.util.ArrayList;

public class Supplier extends myObject{
    private String id;
    private String name;
    private ArrayList<Product> productList;

    public Supplier(String _id, String _name){
        id = _id;
        name = _name;
        productList = new ArrayList<>();
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

    public Product getProductByID(String id)
    {
        for (Product product : productList){
            if (product.getId()==id)
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

}
