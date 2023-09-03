package exercise;

// BEGIN
class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }
    @Override
    public double getArea() {
        return area;
    }
    @Override
    public String toString() {
        return floorCount + " этажный коттедж площадью "
                + this.getArea() + " метров";
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
