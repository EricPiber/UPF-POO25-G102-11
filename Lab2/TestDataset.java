public class TestDataset {
    public static void main(String[] args) {
        // parameters
        double[] e = {1, 2, 3, 4};
        int dim = 4;
        double val = 5;

        Vector u = new Vector(e);
        Vector v = new Vector(dim, val);
        Record r1 = new Record(u, val);
        Record r2 = new Record(v, 0);
        Dataset dtst = new Dataset(2);
        dtst.addRecord(r1);
        dtst.addRecord(r2);
        System.out.println(dtst.toString());
        System.out.println("hello");
    }
}
