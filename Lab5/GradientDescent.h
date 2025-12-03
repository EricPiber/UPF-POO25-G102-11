
#ifndef __GRADIENTDESCENT__
#define __GRADIENTDESCENT__

#include "Algorithm.h"

class GradientDescent : public Algorithm {
    private:
        double stoppingCriterion;

    public:
        GradientDescent(double lr, double sc) : Algorithm(lr), stoppingCriterion(sc) {}

        Vector gradient(Dataset ds, Model m) {
            // getting values needed for computing gradient
            int n = ds.getData().size();
            Vector grad(m.getParams().getDim(), 0);     // initialized with all 0s
            std::vector<Record> data = ds.getData();

            for(int i=0; i<n; i++) {
                // computting summatory
                Vector xi = data[i].getInput().augment();
                double yi = data[i].getOutput();

                double aux = m.predict(xi);
                aux -= yi;
                grad.add(xi.copy().multiply(aux));
            }
            grad.multiply(1.0/n);

            return grad;
        }

        Model solve(Dataset ds) override {
            Model m(ds.getDim() + 1);                           // initializing model
            double gradient_norm = stoppingCriterion + 1;     // assuring it enters while loop

            while (gradient_norm >= stoppingCriterion) {       // until gradient norm is smaller than stopping criterion
                Vector grad = gradient(ds, m);
                m.update(grad, learningRate);
                gradient_norm = grad.norm();
            }
            return m;
        }
};

#endif