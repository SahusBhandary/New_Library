import java.util.EmptyStackException;

public class ReturnStack {
    // field declaration
    private ReturnLog topLog;
    // constructor
    public ReturnStack(){
        topLog = null;
    }
    // returns whether the stack is empty or not
    public boolean isEmpty(){
        return (topLog == null);
    }
    
    // pushes a new book into the repository and returns whether or not it was sucessful
    public boolean pushLog(long returnISBN, long returnUserID, Date returnDate, BookRepository bookRepoRef) throws InvalidISBNException, InvalidReturnDateException, BookNotCheckedOutException, InvalidUserIDException, BookCheckedOutBySomeoneElseException{
        // checks exceptions
        if (String.valueOf(returnISBN).length() > 13){
            throw new InvalidISBNException();
        }
        if(!(returnDate.isValid())){
            throw new InvalidReturnDateException();
        }
        if (String.valueOf(returnUserID).length() > 9){
            throw new InvalidUserIDException();
        }

        // pushes the new log
        ReturnLog newLog = new ReturnLog(returnISBN, returnUserID, returnDate, topLog);
        if (topLog == null){
            topLog = newLog;
        }
        else{
        newLog.setNextLog(topLog);
        topLog = newLog;
        }

        // finds index of current shelf using ISBN
        // Convert the long to a String
        String numberAsString = Long.toString(returnISBN);

        // Extract the first character
        char firstDigitChar = numberAsString.charAt(0);

        // Convert the character to an integer
        int firstDigit = Character.getNumericValue(firstDigitChar);
        
        Shelf currentShelf = new Shelf();
        Shelf[] shelfArray = BookRepository.getShelfArray();
        currentShelf = shelfArray[firstDigit];
        Book cursor = currentShelf.getHeadBook();
        // iterates to find the book with ISBN
        while (cursor.getISBN() != returnISBN && cursor != null){
            cursor = cursor.getNextBook();
        }
        if (cursor.isCheckedOut() == false){
            throw new BookNotCheckedOutException();
        }
        if (cursor.getCheckOutUserID() != returnUserID){
            throw new BookCheckedOutBySomeoneElseException();
        }

        // checks if it is late
        if (Date.compare(returnDate, cursor.getCheckOutDate()) > 0){
            return false;
        }
        else{
            return true;
        }
    }

    // pops a log from the stack
    public ReturnLog popLog() throws EmptyStackException{
        if (topLog == null){
            throw new EmptyStackException();
        }
        ReturnLog result = topLog;
        topLog = topLog.getNextLog();
        return result;
    }

    // shows top element
    public long peek() throws EmptyStackException{
        if (topLog == null){
            throw new EmptyStackException();
        }
        return topLog.getISBN();
    }

    // prints the return stack
    public void printReturnStack(){
        if (isEmpty()){
            System.out.println("No books to return");
            return;
        }
        else{
                System.out.printf("%-15s %-16s %-17s", "ISBN", "UserID", "Return Date");
                System.out.println();
                System.out.printf("%-91s","==================================================================================");
                System.out.println();
                ReturnLog cursor = topLog;
                while (cursor != null){
                    System.out.println(cursor.toString());
                    cursor = cursor.getNextLog();
                }
        }
    }

}
