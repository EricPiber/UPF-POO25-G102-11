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
        if(dataset instanceof StandardizedDataset) {
            Vector trans_v = dataset.transform(v);          // transforming, so the vector adapts to the standardized dataset
            Vector trans_v_augmented = trans_v.augment();   // augmenting, for the prediction
            double y = model.predict(trans_v_augmented);    // predicting
            return dataset.output(y);                       // translating the result back and returning it
        } else {
            Vector v_augmented = v.augment();
            return model.predict(v_augmented);
        }
    }

    @Override
    public String toString() {
        return model.getParams().toString();        // using toString() method from Vector class
    }
}
