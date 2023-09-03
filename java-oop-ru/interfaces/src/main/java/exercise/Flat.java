package exercise;

// BEGIN
class Flat implements Home {

    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
        this.generalArea = this.area + this.balconyArea;
    }
    private double generalArea;
    @Override
    public double getArea() {
        return generalArea;
    }

    @Override
    public String toString() {
        return "Квартира площадью " + generalArea
                + " метров на " + floor
                + " этаже";
    }

    @Override
    public int compareTo(Home anotherHome) {
        double diff = this.getArea() - anotherHome.getArea();
        if (diff > 0) {
            return 1;
        } else if (diff < 0) {
            return -1;
        }
        return 0;
    }
}
// END
