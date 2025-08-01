import com.javarush.task.task24.task2413.BaseObject;
import com.javarush.task.task24.task2413.Canvas;

public class Brick extends BaseObject {
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };

    public Brick(double x, double y) {
        super(x, y, 3);
    }

    @Override
    void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'H');
    }


    @Override
    void move() {
    }
}