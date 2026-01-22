import java.util.*;

class ItemNotFoundException extends Exception{
    public ItemNotFoundException(String message){
        super(message);
    }
}

class AlreadyBorrowedException extends Exception{
    public AlreadyBorrowedException(String message){
        super(message);
    }
}
class LimitExceededException extends Exception{
    public LimitExceededException(String message){
        super(message);
    }
}
class UnauthorizedException extends Exception{
    public UnauthorizedException(String message){
        super(message);
    }
}

abstract class User{
    private String name;
    private String membershipId;
    List<LibraryItem> borrowedItems;

    public User(String name, String membershipId){
        this.name=name;
        this.membershipId=membershipId;
        borrowedItems=new ArrayList<>();
    }

    public void addBorrowedItem(LibraryItem item){
        borrowedItems.add(item);
    }

    public List<LibraryItem> getBorrowedItems(){
        return borrowedItems;
    }

    public void removeBorrowedItem(LibraryItem item){
        borrowedItems.remove(item);
    }

    public String getName(){return name;}
    public String getMembershipId(){return membershipId;}
}

class Member extends User{
    private static final int borrowLimit = 3;
    public Member(String name,String membershipId){
        super(name,membershipId);
    }

    public boolean canBorrowMore(){
        return getBorrowedItems().size() < borrowLimit;
    }
    public void borrow(LibraryItem item) throws AlreadyBorrowedException,LimitExceededException{
       if(!canBorrowMore()){
           throw new LimitExceededException("Borrow Limit is exceeded...");
       }
       item.borrowItem();
       addBorrowedItem(item);
    }
    public void returnItem(LibraryItem item){
        item.setIsBorrowed();
        removeBorrowedItem(item);
    }
}
class Librarian extends User{
    private String employeeId;
    private List<LibraryItem> inventoryList;
    Librarian(String name,String membershipId,String empId){
        super(name,membershipId);
        this.employeeId=empId;
        this.inventoryList = new ArrayList<>();
    }
    public String getEmployeeId(){
        return this.employeeId;
    }
}


// 1. ABSTRACTION: Use an abstract class so no one can create a generic "Item"
abstract class LibraryItem {
    // 2. ENCAPSULATION: Use private fields to protect data
    private String id;
    private String title;
    private boolean isBorrowed;
    private final int borrowLimit = 3;

    public LibraryItem(String id, String title) {
        this.id = id;
        this.title = title;
    }

    // Getters (No setter for ID to keep it immutable)
    public String getId() { return id; }
    public String getTitle() { return title; }
    public int getBorrowLimit() { return borrowLimit; }
    public boolean getIsBorrowed() { return isBorrowed; }
    public void setIsBorrowed(){
        this.isBorrowed = !isBorrowed;
    }

    // Abstract method: Every item must define how it displays its details
    public abstract void displayDetails();

    public  boolean isAvailable() {
        return !isBorrowed;
    }
    public void borrowItem() throws AlreadyBorrowedException{
        if(isBorrowed){
            throw new AlreadyBorrowedException("Item is already Borrowed...");
        }
        isBorrowed=true;
        System.out.println("Successfully Borrowed "+getTitle());
    }
}

class LibraryManager{
    private List<LibraryItem> inventoryList;

    public  LibraryManager (){
        inventoryList=new ArrayList<>();
    }
    public void addItem(LibraryItem item,User user) throws UnauthorizedException{
        if(user instanceof Librarian){
            inventoryList.add(item);
        }
        else{
            throw new UnauthorizedException("You are not authorized to add the Book..");
        }
    }

    public void removeItem(LibraryItem item,User user) throws UnauthorizedException{
        if(user instanceof Librarian){
            inventoryList.remove(item);
        }
        else{
            throw new UnauthorizedException("You are not authorized to remove the Book..");
        }
    }

    public void displayAllBooks(){
        for(LibraryItem l : inventoryList){
            l.displayDetails();
        }
    }

    public List<LibraryItem> searchByTitle(String title){
        List<LibraryItem> templist = new ArrayList<>();
        Iterator<LibraryItem> l = inventoryList.iterator();
        while(l.hasNext()){
            if(Objects.equals(l.next().getTitle().toLowerCase(),title.toLowerCase())){
                templist.add(l.next());
            }
        }
        return templist;
    }

    public LibraryItem searchById(String id) throws ItemNotFoundException{
        for (LibraryItem item : inventoryList) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        throw  new ItemNotFoundException("Item is Not found ...");
    }
}



// 3. INHERITANCE: Book "is-a" LibraryItem
class Book extends LibraryItem {
    private String author;

    public Book(String id, String title, String author) {
        super(id, title); // Calls the parent constructor
        this.author = author;
    }

    @Override
    public void displayDetails() {
        // TODO: Print ID, Title, and Author
        System.out.println("[Book] ID: " + getId() + " | Title: " + getTitle() + " | Author: " + author);
    }
}


// 4. POLYMORPHISM: The Runner class
public class LibrarySystem {
    public static void main(String[] args) {

        // Manually creating 10 distinct Book objects
        Book b1  = new Book("Java001", "Effective Java", "Joshua Bloch");
        Book b2  = new Book("Java002", "Clean Code", "Robert C. Martin");
        Book b3  = new Book("Java003", "Design Patterns", "Erich Gamma");
        Book b4  = new Book("Java004", "Head First Java", "Kathy Sierra");
        Book b5  = new Book("Java005", "Java Concurrency", "Brian Goetz");
        Book b6  = new Book("Java006", "Test Driven Development", "Kent Beck");
        Book b7  = new Book("Java007", "Refactoring", "Martin Fowler");
        Book b8  = new Book("Java008", "The Pragmatic Programmer", "Andrew Hunt");
        Book b9  = new Book("Java009", "Introduction to Algorithms", "Thomas Cormen");
        Book b10 = new Book("Java010", "Deep Learning with Java", "Wei-Meng Lee");


        Librarian l = new Librarian("Alice","alice2003","2003");
        Member m = new Member("Bob","bob2005");
        LibraryManager manager = new LibraryManager();

            try{
                manager.addItem(b1,l);
                manager.addItem(b2,l);
                manager.addItem(b3,l);
                manager.addItem(b4,l);
                manager.addItem(b5,l);
                manager.addItem(b6,l);
                manager.addItem(b7,l);
                manager.addItem(b8,l);
                manager.addItem(b9,l);
                manager.addItem(b10,l);
            } catch (UnauthorizedException e) {
                System.out.println("Error :"+e);
            }

            System.out.println("Borrowing Books...");
            try {
                m.borrow(b1);
                m.borrow(b2);
                m.borrow(b2);
            } catch (LimitExceededException e) {
                throw new RuntimeException(e);
            } catch (AlreadyBorrowedException e) {
                throw new RuntimeException(e);
            }
    }
}