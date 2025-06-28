package model;

public class OrderItem {
    private Menu menu;
    private int quantity;

    public OrderItem(Menu menu, int quantity){
        this.menu = menu;
        this.quantity = quantity;
    }

    public Menu getMenu(){ return menu; }
    public int getQuantity(){ return quantity; }
    public double getTotal(){ return menu.getPrice() * quantity; }
}