package edu.xjtu.se;

import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wtupc96
 */
public class NeuralNet {
    private static final int seed = 8;
    private DataSet allOfData;
    private DataSet trainSet;
    private DataSet testSet;
    private MultiLayerConfiguration configuration;
    private MultiLayerNetwork network;
    private static final Logger logger = LoggerFactory.getLogger(NeuralNet.class);

    public void splitDataSet(DataSet allData, boolean needShuffle, double alpha) {
        allOfData = allData;
        if (needShuffle) {
            allOfData.shuffle();
        }
        SplitTestAndTrain testAndTrain = allOfData.splitTestAndTrain(alpha);

        trainSet = testAndTrain.getTrain();
        testSet = testAndTrain.getTest();
    }

    public void normalize() {
        DataNormalization normalization = new NormalizerStandardize();
        normalization.fit(trainSet);
        normalization.transform(trainSet);
        normalization.transform(testSet);
    }

    public void configure() {
        configuration = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .iterations(1500)
                .activation(Activation.TANH)
                .weightInit(WeightInit.XAVIER)
                .learningRate(1e-4)
                .regularization(true)
                .l2(1e-4)
                .list()
                .layer(0, new DenseLayer.Builder().nIn(6).nOut(6).build())
                .layer(1, new DenseLayer.Builder().nIn(6).nOut(6).build())
                .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .activation(Activation.SOFTMAX)
                        .nIn(6).nOut(2).build())
                .backprop(true)
                .pretrain(false)
                .build();
    }

    public void establishNet() {
        network = new MultiLayerNetwork(configuration);
        network.init();
        network.setListeners(new ScoreIterationListener(20));
        network.fit(trainSet);
    }

    public void evaluate() {
        Evaluation evaluation = new Evaluation(2);
        INDArray output = network.output(testSet.getFeatureMatrix());
        evaluation.eval(testSet.getLabels(), output);
        logger.info(evaluation.stats());
    }
}
