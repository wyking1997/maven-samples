package com.jenkov;

public class Order implements Comparable{
    private int id;
    private int price;
    private int quatity;

    public Order(int id, int price, int quatity) {
        this.id = id;
        this.price = price;
        this.quatity = quatity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return id == order.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int compareTo(Order order){
        if (order == this)
            return 0;
        else
            return (order.getId() < this.id ? -1 : 1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    @Override
    public int compareTo(Object o) {
        Order ord = (Order) o;
        return (this.id < ord.id ? -1 :
                (this.id == ord.id ? 0 : 1));
    }
}
