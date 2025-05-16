package coffee.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("App started!");

        CoffeeOrderBoard coffeeOrderBoard = new CoffeeOrderBoard();

        try {
            coffeeOrderBoard.deliver();
        } catch (IndexOutOfBoundsException e) {
            logger.error("Exception", e);
        }

        coffeeOrderBoard.add("John Doe");
        coffeeOrderBoard.add("Jane Doe");
        coffeeOrderBoard.add("Alen");
        coffeeOrderBoard.add("Bob Smith");
        coffeeOrderBoard.add("Yoda");

        System.out.println("Delivered: " + coffeeOrderBoard.deliver());
        System.out.println("Delivered by id: " + coffeeOrderBoard.deliver(4));
        System.out.println("Delivered: " + coffeeOrderBoard.deliver());

        try {
            coffeeOrderBoard.deliver(39);
        } catch (IndexOutOfBoundsException | NoSuchElementException e) {
            logger.error("Exception", e);
        }

        coffeeOrderBoard.add("Obi-van");
        coffeeOrderBoard.add("John Snow");

        coffeeOrderBoard.draw();

        logger.info("App finished.");
    }
}
