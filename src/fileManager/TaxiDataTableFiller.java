package fileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class TaxiDataTableFiller extends Thread implements Comparable<Integer> {
    private List<TaxiData> allTaxiTrips;
    private File database;
    Filter tripFilter = new Filter();
    @Override
    public void run(){
        int rowNumber = 1;

        try (Stream<String> stream = Files.lines(database.toPath())) {
            stream.forEach(trip -> {
                taxiDataCreator(trip,rowNumber);
                numberIncrimentor(rowNumber);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int compareTo(Integer o) {
        return 0;
    }

    public TaxiDataTableFiller(){
        allTaxiTrips = new LinkedList<>();
    }

    private void taxiDataCreator(String line,int lineNumber) {
        String[] datatoConvert = line.split(",");
        try {
            TaxiData toInput = new TaxiData(lineNumber, datatoConvert[0], datatoConvert[1], datatoConvert[2], datatoConvert[3], datatoConvert[4], datatoConvert[5], datatoConvert[6], datatoConvert[7], datatoConvert[8], datatoConvert[9], datatoConvert[10], datatoConvert[11], datatoConvert[12], datatoConvert[13], datatoConvert[14], datatoConvert[15], datatoConvert[16]);
            allTaxiTrips.add(toInput);
        } catch(Exception e){
            throw e;
        }
    }
    private int numberIncrimentor(int value){
        return value++;
    }

    public List<TaxiData> getAllTaxiTrips() {
        return allTaxiTrips;
    }

    public void setPath(String path){
        try{
            database = new File(path);
            run();
        } catch(Exception e){
            throw e;
        }
    }
}
