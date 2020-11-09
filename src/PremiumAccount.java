import java.util.ArrayList;
import java.util.Date;

public class PremiumAccount extends Account implements productHolder {

    ArrayList<Product> productList = new ArrayList<>();

    public PremiumAccount(String id, ShoppingCart cart,String billing_address, boolean is_closed, int balance) {
        super(id, cart, billing_address, is_closed, balance);
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
                "PremiumAccount ID: " +this.id+ '\n' +
                        "PremiumAccount Products:" + '\n');
        for (Product product : productList) {
            res.append(product.toString());
            res.append('\n');
        }
        res.append(super.toString());
        return res.toString();
    }

    @Override
    public ArrayList<Product> getProducts() {
        return this.productList;
    }

    @Override
    public Product getProductByName(String name) {
        for (Product product : productList){
            if (product.getName().equals(name))
                return product;
        }
        return null;
    }
}
