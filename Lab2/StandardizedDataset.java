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

    // additional methods
    public Record transform(Record r, int idx) {
        Vector old_input = r.getInput();
        double[] new_input = new double[old_input.getDim()];
        for (int i = 0; i < old_input.getDim(); i++) {
            new_input[i] = ((old_input.getElem(i) - mi.getElem(idx)) / si.getElem(idx));
        }
        Vector v_input = new Vector(new_input);
        double output = ((r.getOutput() - mo) / so);
        Record res = new Record(v_input, output);
        return res;
    }
}
