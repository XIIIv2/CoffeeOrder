package coffee.order;

import java.util.NoSuchElementException;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoffeeOrderBoard {

    private final Logger logger = LoggerFactory.getLogger(CoffeeOrderBoard.class);
    private final PriorityBlockingQueue<Order> orders = new PriorityBlockingQueue<>();
    private final AtomicInteger counter = new AtomicInteger(1);

    public CoffeeOrderBoard() {
        logger.debug("new instance created.");
    }

    public void add(String customer) {
        Order order = new Order(counter.getAndIncrement(), customer);
        if (orders.offer(order)) {
            logger.debug("Order added: {}, next id={}", order, counter.get());
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

    public Order deliver(int id)
            throws IndexOutOfBoundsException, NoSuchElementException {

        if (orders.isEmpty()) {
            throw new IndexOutOfBoundsException("Can't deliver from empty queue");
        }
        Order order = orders.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Order with ID " + id + " doesn't exists.")
                );

        orders.remove(order);
        logger.debug("Delivering order by id ({}): {}", id, order);
        return order;
    }

    public void draw() {
        logger.debug("Drawing order board");
        System.out.println("Num | Name");
        orders.stream().forEach(o -> {
            System.out.println(o.getId() + " | " + o.getCustomer());
        });
    }
}
