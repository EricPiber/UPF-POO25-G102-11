public class StandardizedDataset extends Dataset {
    // additional attributes
    private Vector mi; // vector with values means of inputs
    private Vector si; // vector with values of standard deviation of inputs
    private double mo; // mean of outputs
    private double so; // standard deviation of outputs

    // constructor
    public StandardizedDataset(int d, Vector mi, Vector si, double mo, double so) {
        super(d);
        this.mi = mi;
        this.si = si;
        this.mo = mo;
        this.so = so;
    }

    // methods
    @Override
    public Record transform(Record r) {
        Vector trans_x = r.getInput().copy();
        trans_x.subtract(mi).divide(si);
        double trans_y = (r.getOutput() - mo) / so;
        Record trans_r = new Record(trans_x, trans_y);
        return trans_r;
    }

    @Override
    public Vector transform(Vector x) {
        Vector trans_x = x.copy();
        trans_x.subtract(mi).divide(si);
        return trans_x;
    }

    @Override
    public double output(double d) {
        return d*so + mo;
    }
}
