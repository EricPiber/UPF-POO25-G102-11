package Lab1;

public class TestRecord {
    public static void main( String[] args ) {
        // parameters
        double[] e = {1, 2, 3, 4};
        int dim = 4;
        double val = 5;

        // testing constructors
        Vector u = new Vector(e);
        Vector v = new Vector(dim, val);
        Record r = new Record(u, val);

        // testing getter methods
        System.out.printf("getDim() works if 4 = %d\n", u.getDim());
        System.out.printf("getInput() works if [1, 2, 3, 4] = %s\n", (r.getInput()).toString());
        System.out.printf("getOutput() works if 5 = %f\n", r.getOutput());

        // testing methods
        System.out.printf("u.add(v) works if [6, 7, 8, 9] = %s\n", u.add(v).toString());
        System.out.printf("u.subtract(v) works if [1, 2, 3, 4] = %s\n", u.subtract(v).toString());
        System.out.printf("u.multiply(v) works if [5, 10, 15, 20] = %s\n", u.multiply(v).toString());
        System.out.printf("u.divide(v) works if [1, 2, 3, 4] = %s\n", u.divide(v).toString());
        System.out.printf("u.multiply(3) works if [3, 6, 9, 12] = %s\n", u.multiply(3).toString());
        System.out.printf("u.divide(3) works if [1, 2, 3, 4] = %s\n", u.divide(3).toString());
        System.out.printf("v.sqrt() works if [2.236, 2.236, 2.236, 2.236] = %s\n", v.sqrt().toString());
        v.multiply(v);  // restoring Vector v
        System.out.printf("u.dotProduct(v) works if 50 = %f\n", u.dotProduct(v));
        System.out.printf("v.norm() works if 10 = %f\n", v.norm());
        System.out.printf("u.toString() works if [1, 2, 3, 4] = %s\n", u.toString());
        System.out.printf("r.toString() works if ([1, 2, 3, 4], 5) = %s\n", r.toString());
    }
}
