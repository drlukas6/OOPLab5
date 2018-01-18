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
    private Filter tripFilter = new Filter();
    private boolean isFilterPresent = false;
    private int rowCount = 1;
    private int iterationCount = 1;
    @Override
    public void run(){
        rowCount = 1;
        iterationCount = 1;
        allTaxiTrips.clear();
        try (Stream<String> stream = Files.lines(database.toPath())) {
                stream.forEach(this::taxiDataCreator);
                System.out.println("size: "+allTaxiTrips.size());
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

    private void taxiDataCreator(String line) {
        String[] datatoConvert = line.split(",");
        TaxiData toInput = new TaxiData(rowCount, datatoConvert[0], datatoConvert[1], datatoConvert[2], datatoConvert[3], datatoConvert[4], datatoConvert[5], datatoConvert[6], datatoConvert[7], datatoConvert[8], datatoConvert[9], datatoConvert[10], datatoConvert[11], datatoConvert[12], datatoConvert[13], datatoConvert[14], datatoConvert[15], datatoConvert[16]);
        boolean isValid = true;
        if(isFilterPresent){
            if(tripFilter.isLimitDistance()){
                if(tripFilter.getDistanceOperator()==0){
                    if(!(Double.valueOf(toInput.getTrip_distance())<tripFilter.getDistance())) isValid = false;
                }
                else {
                    if(!(Double.valueOf(toInput.getTrip_distance())>tripFilter.getDistance())) isValid = false;
                }
            }
            if(tripFilter.isFilterByPaymentType()){
                if(tripFilter.getPaymentType()==0){
                    if(!toInput.getPayment_type().equals("CSH")) isValid = false;
                }
                else if(tripFilter.getPaymentType()==1){
                    if(!toInput.getPayment_type().equals("CRD")) isValid = false;
                }
                else {
                    if(!toInput.getPayment_type().equals("UNK")) isValid = false;
                }
            }
            if(tripFilter.isSkipRecords()){
                if(iterationCount<tripFilter.getHowManyToSkip()) isValid = false;
            }
            if(tripFilter.isLeaveRecords()){
                if(iterationCount>tripFilter.getHowManyToLeave()) isValid = false;
            }
        }

        if(isValid){
            allTaxiTrips.add(toInput);
            rowCount++;
        }
        iterationCount++;
    }
    private int numberIncrimentor(int value){
        return value++;
    }

    public boolean isFilterPresent() {
        return isFilterPresent;
    }

    public void setFilterPresent(boolean filterPresent) {
        isFilterPresent = filterPresent;
    }

    public Filter getTripFilter() {
        return tripFilter;
    }

    public void setTripFilter(Filter tripFilter) {
        this.tripFilter = tripFilter;
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
