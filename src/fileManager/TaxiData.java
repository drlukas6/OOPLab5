package fileManager;


import java.util.Date;

public class TaxiData {
    private int rowNumber;
    private String medallion;
    private String hack_license;
    private String pickup_datetime;
    private String dropoff_datetime;
    private String trip_time_in_secs;
    private String trip_distance;
    private String pickup_longitude;
    private String pickup_latitude;
    private String dropoff_longitude;
    private String dropoff_latitude;
    private String payment_type;
    private String fare_amount;
    private String surecharge;
    private String mta_tax;
    private String tip_amount;
    private String tolls_amount;
    private String total_amount;

    public TaxiData(int rowNumber, String medallion, String hack_license, String pickup_datetime, String dropoff_datetime, String trip_time_in_secs, String trip_distance, String pickup_longitude, String pickup_latitude, String dropoff_longitude, String dropoff_latitude, String payment_type, String fare_amount, String surecharge, String mta_tax, String tip_amount, String tolls_amount, String total_amount) {
        this.rowNumber = rowNumber;
        this.medallion = medallion;
        this.hack_license = hack_license;
        this.pickup_datetime = pickup_datetime;
        this.dropoff_datetime = dropoff_datetime;
        this.trip_time_in_secs = trip_time_in_secs;
        this.trip_distance = trip_distance;
        this.pickup_longitude = pickup_longitude;
        this.pickup_latitude = pickup_latitude;
        this.dropoff_longitude = dropoff_longitude;
        this.dropoff_latitude = dropoff_latitude;
        this.payment_type = payment_type;
        this.fare_amount = fare_amount;
        this.surecharge = surecharge;
        this.mta_tax = mta_tax;
        this.tip_amount = tip_amount;
        this.tolls_amount = tolls_amount;
        this.total_amount = total_amount;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getMedallion() {
        return medallion;
    }

    public void setMedallion(String medallion) {
        this.medallion = medallion;
    }

    public String getHack_license() {
        return hack_license;
    }

    public void setHack_license(String hack_license) {
        this.hack_license = hack_license;
    }

    public String getPickup_datetime() {
        return pickup_datetime;
    }

    public void setPickup_datetime(String pickup_datetime) {
        this.pickup_datetime = pickup_datetime;
    }

    public String getDropoff_datetime() {
        return dropoff_datetime;
    }

    public void setDropoff_datetime(String dropoff_datetime) {
        this.dropoff_datetime = dropoff_datetime;
    }

    public String getTrip_time_in_secs() {
        return trip_time_in_secs;
    }

    public void setTrip_time_in_secs(String trip_time_in_secs) {
        this.trip_time_in_secs = trip_time_in_secs;
    }

    public String getTrip_distance() {
        return trip_distance;
    }

    public void setTrip_distance(String trip_distance) {
        this.trip_distance = trip_distance;
    }

    public String getPickup_longitude() {
        return pickup_longitude;
    }

    public void setPickup_longitude(String pickup_longitude) {
        this.pickup_longitude = pickup_longitude;
    }

    public String getPickup_latitude() {
        return pickup_latitude;
    }

    public void setPickup_latitude(String pickup_latitude) {
        this.pickup_latitude = pickup_latitude;
    }

    public String getDropoff_longitude() {
        return dropoff_longitude;
    }

    public void setDropoff_longitude(String dropoff_longitude) {
        this.dropoff_longitude = dropoff_longitude;
    }

    public String getDropoff_latitude() {
        return dropoff_latitude;
    }

    public void setDropoff_latitude(String dropoff_latitude) {
        this.dropoff_latitude = dropoff_latitude;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getFare_amount() {
        return fare_amount;
    }

    public void setFare_amount(String fare_amount) {
        this.fare_amount = fare_amount;
    }

    public String getSurecharge() {
        return surecharge;
    }

    public void setSurecharge(String surecharge) {
        this.surecharge = surecharge;
    }

    public String getMta_tax() {
        return mta_tax;
    }

    public void setMta_tax(String mta_tax) {
        this.mta_tax = mta_tax;
    }

    public String getTip_amount() {
        return tip_amount;
    }

    public void setTip_amount(String tip_amount) {
        this.tip_amount = tip_amount;
    }

    public String getTolls_amount() {
        return tolls_amount;
    }

    public void setTolls_amount(String tolls_amount) {
        this.tolls_amount = tolls_amount;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }
}
