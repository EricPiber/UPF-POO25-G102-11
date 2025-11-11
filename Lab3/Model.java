public class Model {
    // attributes
    private Vector params;

    // constructor method
    public Model(int dim) {
        params = new Vector(dim, 0);
    }

    // getter method
    public Vector getParams() {
        return params;
    }

    // methods
    public double predict(Vector x) {
        return params.dotProduct(x);
    }

    public void update(Vector v, double rate) {
        v.multiply(rate);
        this.params.subtract(v);
    }
}
