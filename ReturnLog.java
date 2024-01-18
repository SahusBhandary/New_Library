public class ReturnLog {
    // field declaration
    private long ISBN;
    private long userID;
    private Date returnDate;
    private ReturnLog nextLog;

    // constructors
    public ReturnLog(){}

    public ReturnLog(long ISBN, long userID, Date returnDate, ReturnLog nextLog){
        this.ISBN = ISBN;
        this.userID = userID;
        this.returnDate = returnDate;
        this.nextLog = nextLog;
    }

    // getters and setters
    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public ReturnLog getNextLog() {
        return nextLog;
    }

    public void setNextLog(ReturnLog nextLog) {
        this.nextLog = nextLog;
    }

    // to String method
    public String toString(){
        //return ISBN + "\t\t" + userID + "\t\t" + returnDate.toString();
        return String.format("%-15s %-15s %-15s", ISBN, userID, returnDate.toString());
    }


}
