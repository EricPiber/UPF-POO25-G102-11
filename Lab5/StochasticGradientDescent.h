
#ifndef __STOCHASTICGRADIENTDESCENT__
#define __STOCHASTICGRADIENTDESCENT__

#include "Algorithm.h"

class StochasticGradientDescent : public Algorithm {
    private:
        int batchSize;
        int iterations;

    public:
        StochasticGradientDescent(double lr, int bs, int it) :
        Algorithm(lr), batchSize(bs), iterations(it) {}

        Vector stochasticGradient(Dataset ds, Model m) {
            std::vector<Record> data = ds.getData();
            // getting values needed for computing stochastic gradient
            std::vector<Record> batch;
            std::sample(data.begin(), data.end(), std::back_inserter(batch), batchSize, std::mt19937{std::random_device{}()});
            // now batch is a subset with batchSize records

            Vector grad(m.getParams().getDim(), 0);   // initialized with all 0s

            for(int i=0; i < batchSize; i++) {
                // computting summatory
                Vector xi = batch[i].getInput().augment();
                double yi = batch[i].getOutput();

                double aux = m.predict(xi);
                aux -= yi;
                grad.add(xi.copy().multiply(aux));
            }
            grad.multiply(1.0/batchSize);
            
            return grad;
        }

        Model solve(Dataset ds) override {
            Model m(ds.getDim() + 1);               // initializing model

            for(int i=0; i<iterations; i++) {       // until we get to the specified number of iterations
                Vector gradient = stochasticGradient(ds, m);
                m.update(gradient, learningRate);
            }
            return m;
        }
};

#endif