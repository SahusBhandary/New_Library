public class Shelf {
    // field declaration
    private Book headBook;
    private Book tailBook;
    private int length;

    // constructor
    public Shelf(){}

    // gettters and setters
    public Book getHeadBook(){
        return headBook;
    }

    public void setHeadBook(Book headBook){
        this.headBook = headBook;
    }

    public Book getTailBook(){
        return tailBook;
    }

    public void setTailBook(Book tailBook){
        this.tailBook = tailBook;
    }

    // adds a book to the shelf
    public void addBook(Book addend) throws BookAlreadyExistsException{
        if (headBook == null){
            headBook = addend;
            tailBook = addend;
        }
        else {
            // checks whether or not the book already exists
            Book current = headBook;
            while (current != null) {
                if (current.getISBN() == addend.getISBN()) {
                    throw new BookAlreadyExistsException();
                }
                current = current.getNextBook();
            }
            tailBook.setNextBook(addend);
            tailBook = addend;
        }
        length++;
    }

    // removes a book from the stack
    public void removeBook(String removedISBN) throws InvalidISBNException, BookDoesNotExistException{
        // builds a new String from the ISBN
        String[] token = removedISBN.split("-");
        StringBuilder ISBN = new StringBuilder();
        for (int i = 0; i < token.length; i++){
            ISBN.append(token[i]);
        }
        if (token.length > 13){
            throw new InvalidISBNException();
        }
        
        String finalISBN = ISBN.toString();

        Book current = headBook;
        Book prevPtr = null;

        // removes the book by checking head and tail cases
        try{
        while (current != null){
            if (current.getISBN() == (Long.parseLong(finalISBN)) && current == headBook){
                headBook = headBook.getNextBook();
                break;
            }
            else if (current.getISBN() == (Long.parseLong(finalISBN)) && current == tailBook){
                prevPtr.setNextBook(current.getNextBook());
                tailBook = current.getNextBook();
                break;
            }
            else if (current.getISBN() == (Long.parseLong(finalISBN))){
                prevPtr.setNextBook(current.getNextBook());
                break;
            }

            prevPtr = current;
            current = current.getNextBook();

        }
        // if no book was found then throw exception
        if(current == null){
            throw new BookDoesNotExistException();
        }
        length--;
    }
    catch(NumberFormatException e){
        throw new InvalidISBNException();
    }

    }

    // sorts based on criteria
    public void sort(ShelfSortCriteria.shelfSortCriteria sortCriteria)  {
        Book prevPtr = null;
        Book cursor = headBook;
        Book next = cursor.getNextBook();
        switch (sortCriteria){
            case ISBN_NUMBER:
                // swaps every time to maintain the condition
                while (next != null){
                    if (cursor.getISBN() > next.getISBN()){
                        swap(prevPtr, cursor, next);
                    }
                    prevPtr = cursor;
                    cursor = next;
                    next = next.getNextBook();
                }
                break;
            case NAME:
                while (next != null){
                    if (cursor.getName().compareTo(next.getName()) > 0){
                        swap(prevPtr, cursor, next);
                    }
                    prevPtr = cursor;
                    cursor = next;
                    next = next.getNextBook();
                }
                break;
            case AUTHOR:
                while (next != null){
                    if (cursor.getAuthor().compareTo(next.getAuthor()) > 0){
                        swap(prevPtr, cursor, next);
                    }
                    prevPtr = cursor;
                    cursor = next;
                    next = next.getNextBook();
                }
                break;
            case GENRE:
                while (next != null){
                    if (cursor.getGenre().compareTo(next.getGenre()) > 0){
                        swap(prevPtr, cursor, next);
                    }
                    prevPtr = cursor;
                    cursor = next;
                    next = next.getNextBook();
                }
                break;
            case YEAR:
                while (next != null){
                    if (cursor.getYearPublished() > next.getYearPublished()){
                        swap(prevPtr, cursor, next);
                    }
                    prevPtr = cursor;
                    cursor = next;
                    next = next.getNextBook();
                }
                break;
            default:
                System.out.println("Incorrect Choice, try again!");
                break;
        }
    }

    // swaps two adjacted cursors
    public void swap(Book prev, Book curr, Book next){
        curr.setNextBook(next.getNextBook());
        next.setNextBook(curr);
        if (prev != null){
            prev.setNextBook(next);
        }
        else{
            this.headBook = next;
        }

    }
    // returns if empty
    public boolean isEmpty(){
        return(length==0);
    }

    // to String method
    public String toString(){
        String result = "";
        Book cursor = headBook;
        while (cursor != null){
            result += cursor.toString() + "\n";
            cursor = cursor.getNextBook();
        }
        return result;
    }
    


}
