public class LineItem {
    private int quantity;
    private int price;
    private Order myOrder;

    public LineItem(int quantity, int price, Order myOrder) {
        this.quantity = quantity;
        this.price = price;
        this.myOrder = myOrder;
    }

    public int getPrice() {
        return price;
    }
}
