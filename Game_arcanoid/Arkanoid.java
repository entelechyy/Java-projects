import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Arkanoid {
    private int width;
    private int height;
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    private Ball ball;
    private Stand stand;
    private boolean isGameOver = false;

    public Arkanoid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    void draw(Canvas canvas) {
        drawBorders(canvas);

        for (Brick brick : bricks) {
            brick.draw(canvas);
        }

        ball.draw(canvas);

        stand.draw(canvas);

    }

    private void drawBorders(Canvas canvas) {
        for (int i = 0; i < width + 2; i++) {
            for (int j = 0; j < height + 2; j++) {
                canvas.setPoint(i, j, '.');
            }
        }

        for (int i = 0; i < width + 2; i++) {
            canvas.setPoint(i, 0, '-');
            canvas.setPoint(i, height + 1, '-');
        }

        for (int i = 0; i < height + 2; i++) {
            canvas.setPoint(0, i, '|');
            canvas.setPoint(width + 1, i, '|');
        }
    }

    void run() throws Exception {
        Canvas canvas = new Canvas(width, height);

        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        while (!isGameOver) {
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();

                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    stand.moveLeft();
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    stand.moveRight();
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ball.start();
            }

            move();

            checkBricksBump();
            checkStandBump();

            checkEndGame();

            canvas.clear();
            draw(canvas);
            canvas.print();

            Thread.sleep(300);
        }
        System.out.println("Game Over!");
    }

    public void move() {
        ball.move();
        stand.move();
    }

    void checkBricksBump() {
        for (Brick brick : new ArrayList<Brick>(bricks)) {
            if (ball.intersects(brick)) {
                double angle = Math.random() * 360;
                ball.setDirection(angle);

                bricks.remove(brick);
            }
        }
    }

    void checkStandBump() {
        if (ball.intersects(stand)) {
            double angle = 90 + 20 * (Math.random() - 0.5);
            ball.setDirection(angle);
        }
    }

    void checkEndGame() {
        if (ball.getY() > height && ball.getDy() > 0)
            isGameOver = true;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static com.javarush.task.task24.task2413.Arkanoid game;

    public static void main(String[] args) throws Exception {
        game = new com.javarush.task.task24.task2413.Arkanoid(20, 30);

        Ball ball = new Ball(10, 29, 2, 95);
        game.setBall(ball);

        Stand stand = new Stand(10, 30);
        game.setStand(stand);

        game.getBricks().add(new Brick(3, 3));
        game.getBricks().add(new Brick(7, 5));
        game.getBricks().add(new Brick(12, 5));
        game.getBricks().add(new Brick(16, 3));

        game.run();
    }
}



















