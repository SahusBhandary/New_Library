 // Book class that is the node for shelf
 public class Book {
    // field declaration
        private String name;
        private String author;
        private String genre;
        private long ISBN;
        private long checkOutUserID;
        private int yearPublished;
        private Date checkOutDate;
        private Book nextBook;
        private boolean checkedOut;
        
        private BookCondition.bookCondition condition;

        // no arg constructor
        public Book(){}

        // arg constructor 
        public Book (long ISBN, String name, String author, String genre, BookCondition.bookCondition condition){
            this.ISBN = ISBN;
            this.name = name;
            this.author = author;
            this.genre = genre;
            this.condition = condition;
        }
        // arg constructor
        public Book(String name, String author, String genre, long ISBN, long checkOutUserID, int yearPublished, Date checkOutDate, Book nextBook, boolean checkedOut) {
            this.name = name;
            this.author = author;
            this.genre = genre;
            this.ISBN = ISBN;
            this.checkOutUserID = checkOutUserID;
            this.yearPublished = yearPublished;
            this.checkOutDate = checkOutDate;
            this.nextBook = nextBook;
            this.checkedOut = checkedOut;
        }

    // getters and setters
     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getAuthor() {
         return author;
     }

     public void setAuthor(String author) {
         this.author = author;
     }

     public String getGenre() {
         return genre;
     }

     public void setGenre(String genre) {
         this.genre = genre;
     }

     public long getISBN() {
         return ISBN;
     }

     public void setISBN(long ISBN) {
         this.ISBN = ISBN;
     }

     public long getCheckOutUserID() {
         return checkOutUserID;
     }

     public void setCheckOutUserID(long checkOutUserID) {
         this.checkOutUserID = checkOutUserID;
     }

     public int getYearPublished() {
         return yearPublished;
     }

     public void setYearPublished(int yearPublished) {
         this.yearPublished = yearPublished;
     }

     public Date getCheckOutDate() {
         return checkOutDate;
     }

     public void setCheckOutDate(Date checkOutDate) {
         this.checkOutDate = checkOutDate;
     }

     public Book getNextBook() {
         return nextBook;
     }

     public void setNextBook(Book nextBook) {
         this.nextBook = nextBook;
     }

     public boolean isCheckedOut() {
         return checkedOut;
     }

     public void setCheckedOut(boolean checkedOut) {
         this.checkedOut = checkedOut;
     }

     public BookCondition.bookCondition getCondition(){
        return condition;
     }

     // to string for the shelf to string method
     public String toString(){
        String checkOut;
        String date;
        if (isCheckedOut()){
            checkOut = "Y";
        }
        else{
            checkOut = "N";
        }
        if (checkOutDate == null){
            date = "N/A";
        }
        else{
            date = checkOutDate.toString();
        }
        // return "\t" + name + "\t" + checkOut + "\t" + date + "\t" + checkOutUserID;
        return String.format("%-15s %10s %20s %20s", name, checkOut, date, checkOutUserID);
     }

     

 }

