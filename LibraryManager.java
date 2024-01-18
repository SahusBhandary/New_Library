import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class LibraryManager {
    public static void main(String[] args) {
        // Variable Declaration
        BookRepository bookRepository = new BookRepository();
        ReturnStack returnStack = new ReturnStack();
        boolean done = false;
        Scanner input = new Scanner(System.in);
        String manageChoice = "";
        String bookRepositoryChoice = "";
        String shelfSortChoice = "";
        String returnStackChoice = "";
        int shelf;
        
        
        while (!done){
            
            // Menu
            System.out.println("Menu:");
            System.out.println();
            System.out.println("\t(B) - Manage Book Repository");
            System.out.println("\t\t(C) - Checkout Book");
            System.out.println("\t\t(N) - Add New Book");
            System.out.println("\t\t(R) - Remove Book");
            System.out.println("\t\t(P) - Print Repository");
            System.out.println("\t\t(S) - Sort Shelf");
            System.out.println("\t\t\t(I) - ISBN Number");
            System.out.println("\t\t\t(N) - Name");
            System.out.println("\t\t\t(A) - Author");
            System.out.println("\t\t\t(G) - Genre");
            System.out.println("\t\t\t(Y) - Year");
            System.out.println("\t\t\t(C) - Condition");
            System.out.println("\t(R) - Manage Return Stack");
            System.out.println("\t\t(R) - Return Book");
            System.out.println("\t\t(L) - See Last Return");
            System.out.println("\t\t(C) - Check In Last Return");
            System.out.println("\t\t(P) - Print Return Stack");
            System.out.println("\t(Q) - Quit");

            System.out.print("Please select what to manage: ");
            manageChoice = input.nextLine();

            // switch for choice
            switch (manageChoice){
                case "B":
                    System.out.print("Please select an option: ");
                    bookRepositoryChoice = input.nextLine();
                    // switch for book repo
                    switch(bookRepositoryChoice){
                        case "C":
                        try{
                        System.out.print("Please provide a library ID: ");
                        long userId = input.nextLong();
                        input.nextLine();
                        System.out.print("Please provide an ISBN Number: ");
                        long isbnNumber = input.nextLong();
                        input.nextLine();
                        System.out.print("Please Provide a return date: ");
                        String returnDate = input.nextLine();
                        String[] token = returnDate.split("/");
                        int month = Integer.parseInt(token[0]);
                        int day = Integer.parseInt(token[1]);
                        int year = Integer.parseInt(token[2]);
                        Date newDate = new Date(day, month, year);
                        bookRepository.checkOutBook(isbnNumber, userId, newDate);
                        System.out.println("Loading...");
                        String checkOutName = bookRepository.returnName(isbnNumber);
                        System.out.println(checkOutName + " has been checked out by " + userId + " and must be returned by " + returnDate.toString());
                        }
                        catch(InvalidISBNException ex){
                            System.out.println("Invalid ISBN Exception");
                        }
                        catch(InvalidUserIDException ex){
                            System.out.println("Invalid User ID Exception");
                        }
                        catch(BookAlreadyCheckedOutException ex){
                            System.out.println("Book already checked out");
                        }
                        catch(BookDoesNotExistException ex){
                            System.out.println("Book Does not exist!");
                        }
                        break;
                        case "N":
                        System.out.print ("Please provide an ISBN: ");
                        long ISBN = input.nextLong();
                        input.nextLine();
                        
                        System.out.print("Please provide a name: ");
                        String name = input.nextLine();
                        
                        System.out.print("Please provide an author: ");
                        String author = input.nextLine();
                        
                        System.out.print("Please provide a genre: ");
                        String genre = input.nextLine();
                        
                        System.out.print("Please provide a condition: ");
                        String condition = input.nextLine();
                        BookCondition.bookCondition bookCondition = null;
                        switch(condition){
                            case "New":
                            bookCondition = BookCondition.bookCondition.NEW;
                            break;
                            case "Good":
                            bookCondition = BookCondition.bookCondition.GOOD;
                            break;
                            case "Bad":
                            bookCondition = BookCondition.bookCondition.BAD;
                            break;
                            case "Replace":
                            bookCondition = BookCondition.bookCondition.REPLACE;
                            break;
                            default:
                            System.out.println("Invalid Book Condition");
                            break;
                        }
                        try{
                        bookRepository.addBook(ISBN, name, author, genre, bookCondition);
                        System.out.println("Loading...");
                        System.out.println(name + " has been successfully added to the repository!");
                        }
                        catch(BookAlreadyExistsException ex){
                            System.out.println("Book Already Exists");
                        }
                        catch(InvalidISBNException ex){
                            System.out.println("Invalid ISBN Number");
                        }
                        break;
                        case "R":
                        long removeISBN;
                        System.out.print("Please Provide an ISBN: ");
                        removeISBN = input.nextLong();
                        input.nextLine();
                        try{
                        String removedName = bookRepository.returnName(removeISBN);
                        bookRepository.removeBook(removeISBN);
                        System.out.println("Loading...");
                        System.out.println(removedName + " has been successfully removed from the repository!");
                        }
                        catch (InvalidISBNException ex){
                            System.out.println("Invalid ISBN Exception");
                        }
                        catch(BookDoesNotExistException ex){
                            System.out.println("Book Does Not Exist Exception");
                        }
                        break;
                        case "P":
                        System.out.print ("Select a shelf: ");
                        int index = input.nextInt();
                        input.nextLine();
                        bookRepository.PrintShelf(index);
                        break;
                        case "S":
                        try{
                        System.out.print("Please select a shelf: ");
                        shelf = input.nextInt();
                        input.nextLine();
                        System.out.print("Please select a sorting criteria: ");
                        shelfSortChoice = input.nextLine();
                        bookRepository.sortShelf(shelf, shelfSortChoice);
                        System.out.println("Loading...");
                        System.out.println("Shelf " + shelf + " has been sorted by " + shelfSortChoice);
                        // switch (shelfSortChoice){
                        //     case "I":
                            
                        //     break;
                        //     case "N":
                        //     break;
                        //     case "A":
                        //     break;
                        //     case "G":
                        //     break;
                        //     case "Y":
                        //     break;
                        //     case "C":
                        //     break;
                        // }
                        }
                        catch(InvalidSortCriteriaException ex){
                            System.out.println("Invalid Sort Criteria");
                        }
                        catch (InputMismatchException ex){
                            System.out.println("Wrong Input!");
                        }
                        break;
                    }
                    break;
                case "R":
                    
                    System.out.print("Please select an option: ");
                    returnStackChoice = input.nextLine();
                    switch (returnStackChoice){
                        case "R":
                        System.out.print("Please provide the ISBN of the book that is being returned: ");
                        long returnISBN = input.nextLong();
                        System.out.print("Please provide your user ID: ");
                        long userId = input.nextLong();
                        input.nextLine();
                        System.out.print("Please provide your current date: ");
                        String returnDate = input.nextLine();
                        String[] token = returnDate.split("/");
                        int month = Integer.parseInt(token[0]);
                        int day = Integer.parseInt(token[1]);
                        int year = Integer.parseInt(token[2]);
                        Date newDate = new Date(day, month, year);
                        try{
                        if (returnStack.pushLog(returnISBN, userId, newDate, bookRepository)){
                            System.out.println("Loading...");
                            System.out.println(bookRepository.returnName(returnISBN) + " has been returned on time!");
                        }
                        else{
                            System.out.println("Loading...");
                            System.out.println(bookRepository.returnName(returnISBN) + " has been returned LATE!. Checking everything in.");
                            while (!(returnStack.isEmpty())){
                                returnStack.popLog();
                            }
                        }
                        
                        }
                        catch(InvalidISBNException ex){
                            System.out.println("Invalid ISBN Exception");
                        }
                        catch(InvalidReturnDateException ex){
                            System.out.println("Invalid Return date");
                        }
                        catch(BookNotCheckedOutException ex){
                            System.out.println("Book Not Checked Out");
                        }
                        catch(InvalidUserIDException ex){
                            System.out.println("Invalid User ID");
                        }
                        catch(BookCheckedOutBySomeoneElseException ex){
                            System.out.println("Book checked out by someone else");
                        }
                        catch(BookDoesNotExistException ex){
                            System.out.println("Book does not exist");
                        }
                        break;
                        case "L":
                        try{
                        long peekISBN = returnStack.peek();
                        System.out.println(bookRepository.returnName(peekISBN) + " is the next book to be checked in.");
                        }
                        catch(InvalidISBNException ex){
                            System.out.println("Invalid ISBN Exception");
                        }
                        catch(BookDoesNotExistException ex){
                            System.out.println("Book does not exist");
                        }
                        catch(EmptyStackException ex){
                            System.out.println("No books to return");
                        }
                        break;
                        case "C":
                        if (returnStack.isEmpty()){
                            System.out.println("No books to return");
                        }
                        else{
                            try{
                            
                            ReturnLog newLog = returnStack.popLog();
                            long checkInISBN = newLog.getISBN();
                            long checkInUserID = newLog.getUserID();
                            bookRepository.checkInBook(checkInISBN, checkInUserID);
                            System.out.println("Loading...");
                            System.out.println(bookRepository.returnName(newLog.getISBN()) + " has been checked in!");
                            }
                            catch(InvalidISBNException ex){
                                System.out.println("Invalid ISBN Exception");
                            }
                            catch(BookDoesNotExistException ex){
                                System.out.println("Book does not exist");
                            }
                            catch(EmptyStackException ex){
                                System.out.println("No books to return");
                            }
                            
                        }
                        break;
                        case "P":
                        System.out.println("Loading...");
                        returnStack.printReturnStack();
                        break;
                        default:
                        System.out.println("Incorrect Choice, try again");
                        break;
                        
                    }
                    break;
                case "Q":
                    done = true;
                    System.out.println("Thank you for using the library manager!");
                    break;
                default:
                    System.out.println("Incorrect choice try again!");
            
            }
        }
    }
}
