import java.util.ArrayList;

public class Supplier {
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
    public void removeProduct(Product productToDelete)
    {
        productList.remove(productToDelete);
    }
    public int indexOfProduct(Product product)
    {
        return productList.indexOf(product);
    }

    public String toString(){
        StringBuilder res = new StringBuilder(
                "Supplier ID: " +
                id.toString() +
                " Supplier Name: " +
                name + '\n' +
                "Supplier Products:" + '\n');
        for (Product product : productList) {
            res.append(product.toString());
            res.append('\n');
        }
        return res.toString();
    }




}
