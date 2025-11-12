public class TestLearner {
    public static void main(String[] args) {
        // PARAMETERS AND INSTANCES TO TEST METHODS
        // from previous labs
        double[] a = {1, 2, 3, 4};
        double[] b = {5, 6, 7, 8};
        Vector u = new Vector(a);
        Vector v = new Vector(b);
        Record r1 = new Record(u, 5);
        Record r2 = new Record(v, 9);
        Dataset dtst = new Dataset(4);
        dtst.addRecord(r1);
        dtst.addRecord(r2);

        // from Lab3
        double lr = 0.001;          // learning rate
        double sc = 0.000001;       // stopping criterion
        Algorithm algorithm = new Algorithm(lr, sc);
        SupervisedLearner sl = new SupervisedLearner(algorithm, dtst);

        // TESTING METHODS


        // testing constructors


        // testing methods

        System.out.printf("addRecord() and getData() work if 2 = %d\n", dtst.getData().size());
        System.out.printf("getDim() works if 2 = %d\n", dtst.getDim());
        StandardizedDataset std_dtst = dtst.standardize();
        System.out.println("If standardize() works, then meanInput(), stdInput(), meanOutput(), stdOutput(), StandardizedDataset() and transform() work");
        System.out.println("standardize() works if mean = 0 and standard deviation = 1, in all cases:");
        System.out.printf("- Mean Input --> [0.0, 0.0, 0.0, 0.0] = %s\n", std_dtst.meanInput().toString());
        System.out.printf("- Std Input --> [1.0, 1.0, 1.0, 1.0] = %s\n", std_dtst.stdInput().toString());
        System.out.printf("- Mean Output --> 0 = %f\n", std_dtst.meanOutput());
        System.out.printf("- Std Output --> 1 = %f\n", std_dtst.stdOutput());
    }
}
