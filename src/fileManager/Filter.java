package fileManager;

public class Filter {
    private boolean skipRecords;
    private boolean leaveRecords;
    private boolean filterByPaymentType;
    private boolean limitDistance;
    private int howManyToSkip;
    private int howManyToLeave;
    private int paymentType;

    public Filter(){
        skipRecords = false;
        leaveRecords = false;
        filterByPaymentType = false;
        limitDistance = false;
        howManyToLeave = 0;
        howManyToLeave = 0;
        paymentType = 0;
    }


    public boolean isSkipRecords() {
        return skipRecords;
    }

    public void setSkipRecords(boolean skipRecords) {
        this.skipRecords = skipRecords;
    }

    public boolean isLeaveRecords() {
        return leaveRecords;
    }

    public void setLeaveRecords(boolean leaveRecords) {
        this.leaveRecords = leaveRecords;
    }

    public boolean isFilterByPaymentType() {
        return filterByPaymentType;
    }

    public void setFilterByPaymentType(boolean filterByPaymentType) {
        this.filterByPaymentType = filterByPaymentType;
    }

    public boolean isLimitDistance() {
        return limitDistance;
    }

    public void setLimitDistance(boolean limitDistance) {
        this.limitDistance = limitDistance;
    }

    public int getHowManyToSkip() {
        return howManyToSkip;
    }

    public void setHowManyToSkip(int howManyToSkip) {
        this.howManyToSkip = howManyToSkip;
    }

    public int getHowManyToLeave() {
        return howManyToLeave;
    }

    public void setHowManyToLeave(int howManyToLeave) {
        this.howManyToLeave = howManyToLeave;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }
}
