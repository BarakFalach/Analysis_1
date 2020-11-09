public class LineItem extends myObject{
    private int quantity;
    private int price;
    private Order myOrder;
    private Product myProduct;
    private ShoppingCart myShoppingCart;
    private String ID;

    public LineItem(String ID,int quantity,Order myOrder,Product myProduct) {
        this.quantity = quantity;
        this.myProduct = myProduct;
        this.price = quantity*myProduct.getPrice();
        this.myShoppingCart=myOrder.getMyAccount().getShoppingCart();
        this.myOrder = myOrder;
        this.ID = ID;
        main.addToObjects(ID, this);
    }

    public int getPrice() {
        return price;
    }
    public String getId(){
        return this.ID;
    }

}
