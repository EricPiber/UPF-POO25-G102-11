package Lab1;

public class Record {
    // attributes
    private Vector input;
    private double output;

    // constructor method
    public Record(Vector i, double o) {
        input = i;
        output = o;
    }

    // getter methods
    public Vector getInput() {
        return input;
    }

    public double getOutput() {
        return output;
    }

    // methods
    public String toString() {
        return String.format("(%s, %f)", input.toString(), output);
    }
}
