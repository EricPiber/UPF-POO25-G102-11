import java.util.ArrayList;
import java.util.Random;

public class StochasticGradientDescent extends Algorithm {
    // attributes
    private int batch_size;
    private int iterations;
    private Random random;

    // constructor method
    public StochasticGradientDescent(double lr, int bs, int it) {
        super(lr);
        batch_size = bs;
        iterations = it;
    }

    public Vector stochasticGradient(Dataset ds, Model m) {
        // getting values needed for computing stochastic gradient
        random = new Random();
        int[] r = random.ints(0, ds.getData().size()).distinct().limit(batch_size).toArray();
        // now r is an array with batch_size random integers between 0 and the size of the dataset
        Vector grad = new Vector(m.getParams().getDim(), 0);   // initialized with all 0s
        ArrayList<Record> data = ds.getData();

        for(int i=0; i < batch_size; i++) {
            int ind = r[i];     // actual random index in the dataset
            // computting summatory
            Vector xi = data.get(ind).getInput().augment();
            double yi = data.get(ind).getOutput();

            double aux = m.predict(xi);
            aux -= yi;
            grad.add(xi.copy().multiply(aux));
        }
        grad.multiply(1.0/batch_size);
        
        return grad;
    }

    @Override
    public Model solve(Dataset ds) {
        Model m = new Model(ds.getDim() + 1);               // initializing model

        for(int i=0; i<iterations; i++) {       // until gradient norm is smaller than stopping criterion
            Vector gradient = stochasticGradient(ds, m);
            m.update(gradient, learning_rate);
        }
        return m;
    }
}
