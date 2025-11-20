public class TestLearner {
    public static void main(String[] args) {
        // PARAMETERS AND INSTANCES TO TEST METHODS
        // from previous labs
        double[] a = {1, 2, 3, 4};
        double[] b = {5, 6, 7, 8};
        double[] c = {9, 10, 11, 12};
        double[] d = {13, 14, 15, 16};
        double[] e = {1, 5, 3, 1};
        Vector u = new Vector(a);
        Vector v = new Vector(b);
        Vector w = new Vector(c);
        Vector x = new Vector(d);
        Vector y = new Vector(e);
        double output_a = 1*1 + 2*2 - 3*1 + 4*0;
        double output_b = 5*1 + 6*2 - 7*1 + 8*0;
        double output_c = 9*1 + 10*2 - 11*1 + 12*0;
        double output_d = 13*1 + 14*2 - 15*1 + 16*0;
        double output_e = 1*1 + 5*2 - 3*1 + 1*0;
        Record r1 = new Record(u, output_a);
        Record r2 = new Record(v, output_b);
        Record r3 = new Record(w, output_c);
        Record r4 = new Record(x, output_d);
        Record r5 = new Record(y, output_e);
        Dataset dtst = new RawDataset(4);
        dtst.addRecord(r1);
        dtst.addRecord(r2);
        dtst.addRecord(r3);
        dtst.addRecord(r4);
        dtst.addRecord(r5);

        
        double[] a1 = {3, 2, 3, 4};
        double[] b1 = {1, -5, 2, 8};
        double[] c1 = {-3, 1, -2, 12};
        double[] d1 = {-3, 4, 5, 16};
        double[] e1 = {5, 5, 5, 1};
        Vector u1 = new Vector(a1);
        Vector v1 = new Vector(b1);
        Vector w1 = new Vector(c1);
        Vector x1 = new Vector(d1);
        Vector y1 = new Vector(e1);
        double output_a1 = 3*1 + 2*2 - 3*1 + 4*0;
        double output_b1 = 1*1 + -5*2 - 2*1 + 8*0;
        double output_c1 = -3*1 + 1*2 - -2*1 + 12*0;
        double output_d1 = -3*1 + 4*2 - 5*1 + 16*0;
        double output_e1 = 5*1 + 5*2 - 5*1 + 1*0;
        Record r6 = new Record(u1, output_a1);
        Record r7 = new Record(v1, output_b1);
        Record r8 = new Record(w1, output_c1);
        Record r9 = new Record(x1, output_d1);
        Record r10 = new Record(y1, output_e1);
        dtst.addRecord(r6);
        dtst.addRecord(r7);
        dtst.addRecord(r8);
        dtst.addRecord(r9);
        dtst.addRecord(r10);
        

        // from Lab3
        double lr = 0.001;          // learning rate
        double sc = 0.000001;       // stopping criterion

        // testing constructors
        GradientDescent grad_desc = new GradientDescent(lr, sc);
        StochasticGradientDescent s_grad_desc = new StochasticGradientDescent(lr, 5, 1000000);
        SupervisedLearner sl1 = new SupervisedLearner(grad_desc, dtst);
        SupervisedLearner sl2 = new SupervisedLearner(s_grad_desc, dtst);
        StandardizedDataset stddtst = ((RawDataset) dtst).standardize();
        SupervisedLearner sl3 = new SupervisedLearner(grad_desc, stddtst);

        // TESTING METHODS
        sl1.solve();
        Vector testInput = new Vector(new double[]{1, 1, 1, 1});
        double prediction1 = sl1.predict(testInput);

        sl2.solve();
        double prediction2 = sl2.predict(testInput);

        sl3.solve();
        double prediction3 = sl3.predict(testInput);

        // checking correctness

        System.out.println("Learned model parameters (theta): " + sl1);
        System.out.println("Prediction for (1, 1, 1, 1): " + prediction1);
        System.out.println("Expected: 2");
        System.out.println("Learned model parameters (theta): " + sl2);
        System.out.println("Prediction for (1, 1, 1, 1): " + prediction2);
        System.out.println("Expected: 2");
        System.out.println("Learned model parameters (theta): " + sl3);
        System.out.println("Prediction for (1, 1, 1, 1): " + prediction3);
        System.out.println("Expected: 2");
    }
}
