public abstract class Algorithm {
    // attributes
    protected double learning_rate;

    // constructor method
    public Algorithm(double lr) {
        learning_rate = lr;
    }

    public abstract Model solve(Dataset ds);
}
