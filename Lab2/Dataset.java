import java.util.ArrayList;

public class Dataset {
    // attributes
    protected int dim;
    protected ArrayList<Record> data;

    // constructor
    public Dataset(int dim) {
        this.dim = dim;
        this.data = new ArrayList<>();
    }

    // getter methods
    public int getDim() {
        return dim;
    }

    public ArrayList<Record> getData() {
        return data;
    }

    // methods
    public void addRecord(Record r) {
        data.add(r);
    }

    public Vector meanInput() {
        double[] means = new double[dim];
        for (int i = 0; i < dim; i++) {
            Vector v_i = data.get(i).getInput();
            Vector v = new Vector(v_i.getDim(), 1);
            means[i] = (v_i.dotProduct(v))/v_i.getDim();
        }
        Vector mean_of_inputs = new Vector(means);
        return mean_of_inputs;
    }

    public Vector stdInput() {
        double[] std_inputs = new double[dim];
        Vector means = this.meanInput();
        for (int i = 0; i < dim; i++) {
            double mean = means.getElem(i);
            Vector v_i = data.get(i).getInput();
            double sum_inputs = 0;
            for (int j = 0; j < v_i.getDim(); j++) {
                sum_inputs += Math.pow((v_i.getElem(j) - mean), 2);
            }
            std_inputs[i] = sum_inputs/v_i.getDim();
        }
        Vector v_std_inputs = new Vector(std_inputs);
        return v_std_inputs.sqrt();
    }

    public double meanOutput() {
        double mean_of_outputs = 0;
        for (int i = 0; i < dim; i++) {
            mean_of_outputs += data.get(i).getOutput();
        }
        mean_of_outputs /= dim;
        return mean_of_outputs;
    }

    public double stdOutput() {
        double mean_output = this.meanOutput();
        double sum_squares = 0;
        for (int i = 0; i < this.dim; i++) {
            sum_squares += Math.pow(this.data.get(i).getOutput() - mean_output, 2);
        }
        return Math.sqrt(sum_squares / this.dim);
    }

    public StandardizedDataset standardize() {
        Vector mi = this.meanInput();
        Vector si = this.stdInput();
        double mo = this.meanOutput();
        double so = this.stdOutput();
        StandardizedDataset stddtst = new StandardizedDataset(dim, mi, si, mo, so);
        for (int i = 0; i < dim; i++) {
            Record r = stddtst.transform(data.get(i), i);
            stddtst.addRecord(r);
        }
        return stddtst;
    }

    @Override
    public String toString() {
        return "Dataset(dim=" + dim + ", records=" + data.size() + ")";
    }
}
