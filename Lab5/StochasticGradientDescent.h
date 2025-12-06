
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

        Vector stochasticGradient(const Dataset& ds, const Model& m) {
            const auto& data = ds.getData();

            std::vector<Record> batch;
            std::sample(data.begin(), data.end(),
                        std::back_inserter(batch),
                        batchSize, std::mt19937{std::random_device{}()});

            Vector grad(m.getParams().getDim(), 0);

            for (int i = 0; i < batchSize; ++i) {
                Vector xi = batch[i].getInput().augment();
                double yi = batch[i].getOutput();
                double aux = m.predict(xi) - yi;

                grad = grad.add(xi.copy().multiply(aux));  // <-- USE return value
            }
            grad = grad.multiply(1.0 / batchSize);         // <-- USE return value
            return grad;
        }

        Model solve(const Dataset& ds) override {
            Model m(ds.getDim() + 1);
            for (int i = 0; i < iterations; ++i) {
                Vector grad = stochasticGradient(ds, m);
                m.update(grad, learningRate);
            }
            return m;
        }
};

#endif