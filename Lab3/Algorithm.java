import java.util.ArrayList;

public class Algorithm {
    // attributes
    private double learning_rate;
    private double stopping_criterion;

    // constructor method
    public Algorithm(double lr, double sc) {
        learning_rate = lr;
        stopping_criterion = sc;
    }

    // methods
    public Vector gradient(Dataset ds, Model m) {
        // getting values needed for computing gradient
        int n = ds.getDim();
        Vector params = m.getParams();
        Vector gradient = new Vector(params.getDim(), 0);   // initialized with all 0s
        ArrayList<Record> data = ds.getData();

        for(int i=0; i < n; i++) {
            // computting summatory
            Vector xi = data.get(i).getInput().augment();
            double yi = data.get(i).getOutput();

            double aux = params.dotProduct(xi);
            aux -= yi;
            gradient.add(xi.multiply(aux));
        }
        gradient.multiply(1/n);

        return gradient;
    }

    public Model solve(Dataset ds) {
        Model m = new Model(ds.getData().get(0).getInput().getDim() + 1);   // initializing model
        double gradient_norm = stopping_criterion + 1;      // assuring it enters while loop

        while (gradient_norm >= stopping_criterion) {       // until gradient norm is smaller than stopping criterion
            Vector gradient = gradient(ds, m);
            m.update(gradient, learning_rate);
            gradient_norm = gradient.norm();
        }
        return m;
    }
}
