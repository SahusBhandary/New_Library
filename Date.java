public class Date {
    // field declaration
    private int day;
    private int month;
    private int year;

    // constructors
    public Date(){}

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // getters and setters
    public int getDay(){
        return day;
    }

    public void setDay(int day){
        this.day = day;
    }

    public int getMonth(){
        return month;
    }

    public void setMonth(int month){
        this.month = month;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }

    public static int compare(Date d1, Date d2){
        // Compare Years
        if (d1.year > d2.year){
            return 1;
        }
        else if (d1.year < d2.year){
            return -1;
        }

        // compare months
        if (d1.month > d2.month){
            return 1;
        }
        else if (d1.month < d2.month){
            return -1;
        }

        // compare days
        if (d1.day > d2.day){
            return 1;
        }
        else if (d1.day < d2.day){
            return -1;
        }

        // else return 0 because they are equal
        return 0;
    }

    // returns true if the date is valid
    public boolean isValid(){
        if (day < 1 || day > 31){
            return false;
        }
        if (month < 1 || month > 12){
            return false;
        }
        if (year < 0){
            return false;
        }

        return true;
    }

    // toString
    public String toString(){
        return month + "/" + day + "/" + year;
    }
}

