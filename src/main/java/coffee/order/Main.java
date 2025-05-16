package coffee.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("App started!");

        CoffeeOrderBoard coffeeOrderBoard = new CoffeeOrderBoard();

        try {
            coffeeOrderBoard.deliver();
        } catch (IndexOutOfBoundsException e) {
            logger.error("Error", e);
        }

        coffeeOrderBoard.add("Jane Doe");
        coffeeOrderBoard.add("Bob Smith");
        coffeeOrderBoard.add("Alen");
        coffeeOrderBoard.add("Yoda");

        System.out.println("Delivered: " + coffeeOrderBoard.deliver());
        System.out.println("Delivered: " + coffeeOrderBoard.deliver());

        coffeeOrderBoard.add("Obi-van");
        coffeeOrderBoard.add("John Snow");

        coffeeOrderBoard.draw();

        logger.info("App finished.");
    }
}
