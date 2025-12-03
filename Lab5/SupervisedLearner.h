
#ifndef __SUPERVISEDLEARNER__
#define __SUPERVISEDLEARNER__

#include "GradientDescent.h"
#include "StochasticGradientDescent.h"

class SupervisedLearner {
    private:
        Algorithm *algorithm;
        Dataset dataset;
        Model model;

    public:
        SupervisedLearner(Algorithm *a, Dataset d) : algorithm(a), dataset(d), model(0) {}

        void solve() {
            model = algorithm->solve(dataset);
        }

        double predict(Vector v) {
            Vector v_augmented = v.augment();
            return model.predict(v_augmented);
        }

        friend std::ostream & operator<<( std::ostream & os, SupervisedLearner & sl ) {
            Vector v = sl.model.getParams();
            return os << v;
        }
};

#endif