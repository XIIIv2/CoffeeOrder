package coffee.order;

import java.util.Optional;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoffeeOrderBoard {

    private final Logger logger = LoggerFactory.getLogger(CoffeeOrderBoard.class);
    private final PriorityQueue<Order> orders = new PriorityQueue<>();
    private final AtomicInteger counter = new AtomicInteger(1);

    public void add(String customer) {
        Order order = new Order(counter.getAndIncrement(), customer);
        if (orders.add(order)) {
            logger.debug("Order added: {}", order);
        } else {
            logger.debug("Failed to add order: {}", order);
        }
    }

    public Order deliver() throws IndexOutOfBoundsException {
        if (orders.isEmpty()) {
            throw new IndexOutOfBoundsException("Can't deliver from empty queue");
        }
        Order order = orders.remove();
        logger.debug("Delivering order: {}", order);
        return order;
    }

    public Order deliver(int id) throws IndexOutOfBoundsException {
        if (orders.isEmpty()) {
            throw new IndexOutOfBoundsException("Can't deliver from empty queue");
        }
        Optional<Order> order = orders.stream()
                .filter(o -> o.getId() == id)
                .findFirst();

        if (order.isEmpty()) {
            throw new IndexOutOfBoundsException("Order with ID " + id + " doesn't exists.");
        }
        Order o = order.get();
        orders.remove(o);
        logger.debug("Delivering order by id ({}): {}", id, o);
        return o;
    }

    public void draw() {
        logger.debug("Drawing order board");
        System.out.println("Num | Name");
        orders.stream().forEach(o -> {
            System.out.println(o.getId() + " | " + o.getCustomer());
        });
    }
}
