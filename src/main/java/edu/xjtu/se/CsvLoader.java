package edu.xjtu.se;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

import java.io.File;
import java.io.IOException;

/**
 * @author wtupc96
 */

public class CsvLoader {
    public static DataSet loadCsv(String path, int skipNumLines, char delimiter, int batchSize, int label, int numOutput) {
        RecordReader reader = new CSVRecordReader(skipNumLines, delimiter);
        try {
            reader.initialize(new FileSplit(new File(path)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DataSetIterator iterator = new RecordReaderDataSetIterator(reader, batchSize, label, numOutput);

        return iterator.next();
    }

    public static DataSet loadCsv(String path, int label, int numOutput) {
        return loadCsv(path, 0, ',', 200, label, numOutput);
    }
}
