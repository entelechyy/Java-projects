import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Hippodrome {

    private List<Horse> horses;

    public List<Horse> getHorses() {
        return horses;
    }

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public static com.javarush.task.task21.task2113.Hippodrome game;

    public void run() throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move() {
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        for (Horse horse : horses) {
            horse.print();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public Horse getWinner() {
        Horse winner = null;
        double distance = Double.MIN_VALUE;
        for (Horse horse : horses) {
            double currentHorseDistance = horse.getDistance();
            if (currentHorseDistance > distance) {
                distance = currentHorseDistance;
                winner = horse;
            }
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");

    }

    public static void main(String[] args) throws InterruptedException {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Bell", 3, 0));
        horses.add(new Horse("Chuck", 3, 0));
        horses.add(new Horse("Void", 3, 0));

        game = new com.javarush.task.task21.task2113.Hippodrome(horses);

        game.run();

        game.printWinner();
    }
}
