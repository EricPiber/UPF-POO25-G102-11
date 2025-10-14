package Lab1; 

import java.util.Arrays;

public class Vector {
    // attributes
    private double[] elems;

    // constructor methods
    public Vector(double[] e) {
        elems = e;
    }
    
    public Vector(int dim, double val) {
        elems = new double[dim];
        for(int i=0; i<dim; i++) {
            elems[i] = val;
        }
    }

    // getter methods
    public int getDim() {
        return elems.length;
    }

    // methods
    public Vector add(Vector v) {
        int dim = this.getDim();
        for(int i=0; i<dim; i++) {
            elems[i] += v.elems[i];
        }
        return this;
    }

    public Vector subtract(Vector v) {
        int dim = this.getDim();
        for(int i=0; i<dim; i++) {
            elems[i] -= v.elems[i];
        }
        return this;
    }

    public Vector multiply(Vector v) {
        int dim = this.getDim();
        for(int i=0; i<dim; i++) {
            elems[i] *= v.elems[i];
        }
        return this;
    }

    public Vector divide(Vector v) {
        int dim = this.getDim();
        for(int i=0; i<dim; i++) {
            elems[i] /= v.elems[i];
        }
        return this;
    }

    public Vector multiply(double scalar) {
        int dim = this.getDim();
        for(int i=0; i<dim; i++) {
            elems[i] *= scalar;
        }
        return this;
    }

    public Vector divide(double scalar) {
        int dim = this.getDim();
        for(int i=0; i<dim; i++) {
            elems[i] /= scalar;
        }
        return this;
    }

    public Vector sqrt() {
        int dim = this.getDim();
        for(int i=0; i<dim; i++) {
            elems[i] = Math.sqrt(elems[i]);
        }
        return this;
    }

    public double dotProduct(Vector v) {
        double result = 0;
        int dim = this.getDim();
        for(int i=0; i<dim; i++) {
            result += (this.elems[i] * v.elems[i]);
        }
        return result;
    }

    public double norm() {
        double result = 0;
        int dim = this.getDim();
        for(int i=0; i<dim; i++) {
            result += (this.elems[i]*this.elems[i]);
        }
        result = Math.sqrt(result);
        return result;
    }

    public String toString() {
        return Arrays.toString(elems);
    }
}
