
#ifndef __GRADIENTDESCENT__
#define __GRADIENTDESCENT__

#include "Algorithm.h"

class GradientDescent : public Algorithm {
    private:
        double stoppingCriterion;

    public:
        GradientDescent(double lr, double sc) : Algorithm(lr), stoppingCriterion(sc) {}

        Vector gradient(const Dataset& ds, const Model& m) {
            int n = static_cast<int>(ds.getData().size());
            Vector grad(m.getParams().getDim(), 0);        // zeros

            const auto& data = ds.getData();               // (use const ref after Dataset change below)

            for (int i = 0; i < n; ++i) {
                Vector xi = data[i].getInput().augment();
                double yi = data[i].getOutput();
                double aux = m.predict(xi) - yi;

                grad = grad.add(xi.copy().multiply(aux));  // <-- USE return value
            }
            grad = grad.multiply(1.0 / n);                 // <-- USE return value
            return grad;
        }

        Model solve(const Dataset& ds) override {
            Model m(ds.getDim() + 1);                      // bias term at the end
            double gradient_norm = stoppingCriterion + 1;

            while (gradient_norm >= stoppingCriterion) {
                Vector grad = gradient(ds, m);
                m.update(grad, learningRate);
                gradient_norm = grad.norm();
            }
            return m;
        }
};

#endif