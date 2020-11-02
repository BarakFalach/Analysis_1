import java.util.ArrayList;
import java.util.Date;

public class PremiumAccount extends  Account {

    ArrayList<Product> productList = new ArrayList<>();

    public PremiumAccount(String id, String billing_address, boolean is_closed, int balance) {
        super(id, billing_address, is_closed, balance);
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
                "PremiumAccount ID: " + '\n' +
                        "PremiumAccount Products:" + '\n');
        for (Product product : productList) {
            res.append(product.toString());
            res.append('\n');
        }
        return res.toString();
    }
}
