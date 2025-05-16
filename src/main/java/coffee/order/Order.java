package coffee.order;

public class Order implements Comparable<Order> {

    private final int id;
    private final String customer;

    public Order(int id, String customer) {
        this.id = id;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    @Override
    public int compareTo(Order order) {
        if (id == order.getId()) {
            return 0;
        } else if (id > order.getId()) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                '}';
    }
}
