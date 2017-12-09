package io.ragnarok.shield;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Performs simple logistic regression.
 */
public class Logistic {

    /** the learning rate */
    private double rate;

    /** the weight to learn */
    private double[] weights;

    /** the number of iterations */
    private int ITERATIONS = 3000;

    public Logistic(int n) {
        this.rate = 0.3;
        weights = new double[n];
    }

    private static double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }

    public void train(List<Instance> instances) {
        for (int n=0; n<ITERATIONS; n++) {
            double lik = 0.0;
            for (int i=0; i<instances.size(); i++) {
                float[] x = instances.get(i).x;
                double predicted = classify(x);
                float label = instances.get(i).label;
                for (int j=0; j<weights.length; j++) {
                    weights[j] = weights[j] + rate * (label - predicted) * predicted * (1 - predicted) * x[j];
                }

            }
            System.out.println("iteration: " + n + " " + Arrays.toString(weights) + " mle: " + lik);
        }
    }

    public int classify(float[] x) {
        double logit = .0;
        for (int i=0; i<weights.length;i++)  {
            logit += weights[i] * x[i];
        }
        if (sigmoid(logit)>0.5){
            return 1;
        }else {
            return 1;
        }
    }

    public static class Instance {
        public float label;
        public float[] x;

        public Instance(float label, float[] x) {
            this.label = label;
            this.x = x;
        }
    }

    public static List<Instance> readDataSet(String file, String user_name) throws FileNotFoundException {
        List<Instance> dataset = new ArrayList<Instance>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(file));
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] columns = line.split(",");
                if(columns[0].equals(user_name)) {
                    // skip first column and last column is the label

                    float[] data = new float[columns.length-6];
                    for (int i=5; i<columns.length-1; i++) {
                        int k=i-5;
                        data[k] = Float.parseFloat(columns[i]);
                    }
                    float label = Float.parseFloat(columns[columns.length-1]);
                    Instance instance = new Instance(label, data);
                    dataset.add(instance);
                }
            }
        } finally {
            if (scanner != null)
                scanner.close();
        }
        dataset.remove(0);
        return dataset;
    }

    /*
    public static void main(String... args) throws FileNotFoundException {
        List<Instance> instances = readDataSet("User_db.csv",user_name);
        Logistic logistic = new Logistic(8);
        logistic.train(instances);
        int[] x = {2, 1, 1, 0, 1};
        System.out.println("prob(1|x) = " + logistic.classify(x));

        int[] x2 = {1, 0, 1, 0, 0};
        System.out.println("prob(1|x2) = " + logistic.classify(x2));

    }
   */

}