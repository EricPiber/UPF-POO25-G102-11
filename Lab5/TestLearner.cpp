
#include "SupervisedLearner.h"

int main() {

    // building dataset
    std::vector<double> a {1, 2, 3, 4};
    std::vector<double> b {5, 6, 7, 8};
    std::vector<double> c {9, 10, 11, 12};
    std::vector<double> d {13, 14, 15, 16};
    std::vector<double> e {1, 5, 3, 1};

    Vector u(a), v(b), w(c), x(d), y(e);

    double output_a = 1*1 + 2*2 - 3*1 + 4*1;
    double output_b = 5*1 + 6*2 - 7*1 + 8*1;
    double output_c = 9*1 + 10*2 - 11*1 + 12*1;
    double output_d = 13*1 + 14*2 - 15*1 + 16*1;
    double output_e = 1*1 + 5*2 - 3*1 + 1*1;

    Record r1(u, output_a);
    Record r2(v, output_b);
    Record r3(w, output_c);
    Record r4(x, output_d);
    Record r5(y, output_e);

    Dataset dtst(4);
    dtst.addRecord(r1);
    dtst.addRecord(r2);
    dtst.addRecord(r3);
    dtst.addRecord(r4);
    dtst.addRecord(r5);

    std::vector<double> a1 {3, 2, 3, 4};
    std::vector<double> b1 {1, -5, 2, 8};
    std::vector<double> c1 {-3, 1, -2, 12};
    std::vector<double> d1 {-3, 4, 5, 16};
    std::vector<double> e1 {5, 5, 5, 1};

    Vector u1(a1), v1(b1), w1(c1), x1(d1), y1(e1);

    double output_a1 = 3*1 + 2*2 - 3*1 + 4*1;
    double output_b1 = 1*1 + -5*2 - 2*1 + 8*1;
    double output_c1 = -3*1 + 1*2 - -2*1 + 12*1;
    double output_d1 = -3*1 + 4*2 - 5*1 + 16*1;
    double output_e1 = 5*1 + 5*2 - 5*1 + 1*1;

    Record r6(u1, output_a1);
    Record r7(v1, output_b1);
    Record r8(w1, output_c1);
    Record r9(x1, output_d1);
    Record r10(y1, output_e1);

    dtst.addRecord(r6);
    dtst.addRecord(r7);
    dtst.addRecord(r8);
    dtst.addRecord(r9);
    dtst.addRecord(r10);

    // defining parameters for algorithms
    double lr = 0.001;    // learning rate
    double sc = 0.000001; // stopping criterion

    // testing constructors
    GradientDescent grad_desc(lr, sc);
    StochasticGradientDescent s_grad_desc(lr, 5, 1000000);

    SupervisedLearner sl1(&grad_desc, dtst);
    SupervisedLearner sl2(&s_grad_desc, dtst);

    // testing methods, model solving
    sl1.solve();
    Vector testInput(std::vector<double>{1, 1, 1, 1});
    double prediction1 = sl1.predict(testInput);

    sl2.solve();
    double prediction2 = sl2.predict(testInput);

    // checking correctness of method implementations
    std::cout << "Learned model parameters (theta): " << sl1 << "\n";
    std::cout << "Prediction for (1, 1, 1, 1): " << prediction1 << "\n";
    std::cout << "Expected: 3\n";

    std::cout << "Learned model parameters (theta): " << sl2 << "\n";
    std::cout << "Prediction for (1, 1, 1, 1): " << prediction2 << "\n";
    std::cout << "Expected: 3\n";
    
    return 0;
}

