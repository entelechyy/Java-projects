public class Horse {
    String name;
    double speed;
    double distance;

    public Horse(String name, double speed, double distance) {
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void move() {
        double randomNumber = Math.random();
        setDistance(getDistance() + getSpeed() * randomNumber);
    }

    public void print() {
        int dotsNumber = (int) getDistance();
        for (int i = 0; i < dotsNumber; i++) {
            System.out.print(".");
        }
        System.out.print(getName());
        System.out.println();
    }
}
