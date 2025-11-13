public class SupervisedLearner {
    // attributes
    private Algorithm algorithm;
    private Dataset dataset;
    private Model model;

    // constructor method
    public SupervisedLearner(Algorithm a, Dataset d) {
        algorithm = a;
        dataset = d;
    }

    // methods
    public void solve() {
        model = algorithm.solve(dataset);
    }

    public double predict(Vector v) {
        Vector v_augmented = v.augment();       // augmenting input vector
        return model.predict(v_augmented);      // output given by the dot product
    }

    @Override
    public String toString() {
        return model.getParams().toString();        // using toString() method from Vector class
    }
}
