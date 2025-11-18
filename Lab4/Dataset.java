import java.util.ArrayList;

public abstract class Dataset {
    // attributes
    protected int dim;
    protected ArrayList<Record> data;

    // constructor
    public Dataset(int dim) {
        this.dim = dim;
        data = new ArrayList<>();
    }

    // getter methods
    public int getDim() {
        return dim;
    }

    public ArrayList<Record> getData() {
        return data;
    }

    // setter methods
    public void addRecord(Record r) {
        data.add(r);
    }

    // methods
    public abstract Record transform(Record r);

    public abstract Vector transform(Vector x);

    public abstract double output(double d);

    @Override
    public String toString() {
        return "Dataset(dim=" + dim + ", records=" + data.size() + ")";
    }
}
