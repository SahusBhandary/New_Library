// linked list of shelfs
public class BookRepository {
    // variable delcaration
    private final static Shelf[] shelfArray = new Shelf[10];
    public BookRepository(){
        for (int i = 0; i < shelfArray.length; i++){
            shelfArray[i] = new Shelf();
        }
    }

    // getter for shelf array to be used in other classes
    public static Shelf[] getShelfArray(){
        return shelfArray;
    }
    // checks in a book to the repository
    public void checkInBook(long checkedInISBN, long checkInUserID) throws BookDoesNotExistException{
        // finds the index of where to add the book
        // Convert the long to a String
        String numberAsString = Long.toString(checkedInISBN);

        // Extract the first character
        char firstDigitChar = numberAsString.charAt(0);

        // Convert the character to an integer
        int firstDigit = Character.getNumericValue(firstDigitChar);
        
        // selects the shelf of the index
        Shelf currentShelf = new Shelf();
        currentShelf = shelfArray[firstDigit];
        Book cursor = currentShelf.getHeadBook();
        // interates through the list until the book is found
        while (cursor.getISBN() != checkedInISBN && cursor != null){
            cursor = cursor.getNextBook();
        }
        // if null the book does not exist
        if (cursor == null){
            throw new BookDoesNotExistException();
        }
        else{
            // if the user ids do not match, the book was not returned properly
            if (cursor.getCheckOutUserID() != checkInUserID){
                System.out.println("Your user ID does not match the checked out user id");
            }
            // else the book is checked in
            else{
                cursor.setCheckedOut(false);
            }
        }
        cursor.setCheckOutDate(null);
        cursor.setCheckOutUserID(0);
        cursor.setCheckedOut(false);
    }

    // checks out a book from the repository
    public void checkOutBook(long checkedOutISBN, long checkOutUserID, Date dueDate) throws InvalidISBNException, InvalidUserIDException, BookAlreadyCheckedOutException{
        // find the index of the isbn number
        // Convert the long to a String
        String numberAsString = Long.toString(checkedOutISBN);

        // Extract the first character
        char firstDigitChar = numberAsString.charAt(0);

        // Convert the character to an integer
        int firstDigit = Character.getNumericValue(firstDigitChar);
        // checks if the userID and ISBN are valid numbers
        String lengthOfISBN = String.valueOf(checkedOutISBN);
        if (lengthOfISBN.length() > 13){
            throw new InvalidISBNException();
        }
        String lengthOfUserID = String.valueOf(checkOutUserID);
        if (lengthOfUserID.length() > 10){
            throw new InvalidUserIDException();
        }
    
        // iterates until it finds the book
        Shelf currentShelf = new Shelf();
        currentShelf = shelfArray[firstDigit];
        Book cursor = currentShelf.getHeadBook();
        while (cursor.getISBN() != checkedOutISBN && cursor != null){
            cursor = cursor.getNextBook();
        }
        if (cursor.isCheckedOut() == true){
            throw new BookAlreadyCheckedOutException();
        }

        // checks out the book
        cursor.setCheckedOut(true);
        cursor.setCheckOutDate(dueDate);
        cursor.setCheckOutUserID(checkOutUserID);
    }

    // adds a book to the repository
    public void addBook(long addISBN, String addName, String addAuthor, String addGenre, BookCondition.bookCondition addCondition) throws BookAlreadyExistsException, InvalidISBNException{
        // checks if the ISBN is valid
        if (String.valueOf(addISBN).length() > 13){
            throw new InvalidISBNException();
        }
        // finds index of first digit of ISBN
        // Convert the long to a String
        String numberAsString = Long.toString(addISBN);

        // Extract the first character
        char firstDigitChar = numberAsString.charAt(0);

        // Convert the character to an integer
        int firstDigit = Character.getNumericValue(firstDigitChar);
        // iterates to find if book already exists
        Shelf currentShelf;
        currentShelf = shelfArray[firstDigit];
        Book newBook = new Book(addISBN,addName, addAuthor, addGenre, addCondition);
        Book cursor = currentShelf.getHeadBook();
        while (cursor != null){
            if (cursor.getISBN() == addISBN){
                throw new BookAlreadyExistsException();
            }
            cursor = cursor.getNextBook();
        }
        // adds book to the shelf
        currentShelf.addBook(newBook);
        currentShelf.sort(ShelfSortCriteria.shelfSortCriteria.NAME);
    }

    // removes book from the repository
    public void removeBook(long removeISBN) throws InvalidISBNException, BookDoesNotExistException{
        // finds index
        // Convert the long to a String
        String numberAsString = Long.toString(removeISBN);

        // Extract the first character
        char firstDigitChar = numberAsString.charAt(0);

        // Convert the character to an integer
        int firstDigit = Character.getNumericValue(firstDigitChar);
        // checks for valid ISBN
        int lengthOfISBN = (String.valueOf(removeISBN).length());
        if (lengthOfISBN > 13){
            throw new InvalidISBNException();
        }
        
        // removes the book
        Shelf currentShelf = new Shelf();
        currentShelf = shelfArray[firstDigit];
        currentShelf.removeBook(String.valueOf(removeISBN));
    }

    // sorts the shelf based on criteria
    public void sortShelf(int shelfInd, String sortCriteria) throws InvalidSortCriteriaException{
        Shelf currentShelf = new Shelf();
        currentShelf = shelfArray[shelfInd];
        // sorts based on crietria using the sorting method from shelf
        switch(sortCriteria){
            case "I":
            currentShelf.sort(ShelfSortCriteria.shelfSortCriteria.ISBN_NUMBER);
            break;
            case "N":
            currentShelf.sort(ShelfSortCriteria.shelfSortCriteria.NAME);
            break;
            case "A":
            currentShelf.sort(ShelfSortCriteria.shelfSortCriteria.AUTHOR);
            break;
            case "G":
            currentShelf.sort(ShelfSortCriteria.shelfSortCriteria.GENRE);
            break;
            case "Y":
            currentShelf.sort(ShelfSortCriteria.shelfSortCriteria.YEAR);
            break;
            case "C":
            currentShelf.sort(ShelfSortCriteria.shelfSortCriteria.CONDITION);
            break;
            default:
            throw new InvalidSortCriteriaException();
        }
    }

    // returns the name of the book given the isbn by iterating through shelves until the book is found
    public String returnName(long ISBN) throws BookDoesNotExistException, InvalidISBNException{
        if (String.valueOf(ISBN).length() > 13){
            throw new InvalidISBNException();
        }
        // Convert the long to a String
        String numberAsString = Long.toString(ISBN);

        // Extract the first character
        char firstDigitChar = numberAsString.charAt(0);

        // Convert the character to an integer
        int firstDigit = Character.getNumericValue(firstDigitChar);
        
        Shelf currentShelf = new Shelf();
        currentShelf = shelfArray[firstDigit];
        Book cursor = currentShelf.getHeadBook();
        while (cursor != null && cursor.getISBN() != ISBN ){
            cursor = cursor.getNextBook();
        }
        if (cursor == null){
            throw new BookDoesNotExistException();
        }
        return cursor.getName();
    }

    // print method
    public void PrintShelf(int index){
        Shelf currentShelf = shelfArray[index];
        if (currentShelf == null){
            System.out.println("The shelf Is currently Empty!");
            return;
        }
        
        System.out.printf("%-20s %-22s %-20s %-20s", "Name", "Checked Out", "Check Out Date", "Check Out User ID");
        System.out.println();
        System.out.printf("%-91s","==================================================================================");
        System.out.println();
        System.out.print(currentShelf.toString());
        
    }





}
