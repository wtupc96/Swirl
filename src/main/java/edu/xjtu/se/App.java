package edu.xjtu.se;

import org.nd4j.linalg.dataset.DataSet;

/**
 * @author wtupc96
 */
public class App {
    public static void main(String[] args) {
        DataSet dataSet = CsvLoader.loadCsv("D:\\rb.csv", 1, ',', 24150, 6, 2);
        NeuralNet net = new NeuralNet();
        net.splitDataSet(dataSet, false, 0.8);
        net.normalize();
        net.configure();
        net.establishNet();
        net.evaluate();
    }
}
