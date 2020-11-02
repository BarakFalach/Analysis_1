public class LineItem {
    private int quantity;
    private int price;
    private Order myOrder;
    private Product myProduct;
    private ShoppingCart myShoppingCart;

    public LineItem(int quantity,Order myOrder,Product myProduct) {
        this.quantity = quantity;
        this.myProduct = myProduct;
        this.price = quantity*myProduct.getPrice();
        this.myShoppingCart=myOrder.getMyAccount().getShoppingCart();
        this.myOrder = myOrder;
    }

    public int getPrice() {
        return price;
    }
}
