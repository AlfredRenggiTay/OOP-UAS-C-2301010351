package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem item){
        items.add(item);
    }

    public List<OrderItem> getItems(){
        return items;
    }

    public double getTotal(){
        double total = 0;
        for(OrderItem item : items){
            total += item.getTotal();
        }
        return total;
    }
}