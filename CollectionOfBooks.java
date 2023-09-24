// --== CS400 File Header Information ==--
// Name: Zhonghao Liu
// Email: zliu882@wisc.edu
// Notes to Grader: This is the core back end program. Imports data from BookList.txt

import java.util.ArrayList;
import java.lang.NullPointerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Integer;

/**
 * This is a collection of books
 */
public class CollectionOfBooks {
    private ArrayList<Book> library; // collection of books
    private int numBooks; // number of books
    private int numID; // a tool for assigning IDs for books
    private Map titleMap; // hashtable map for title-ID pairs
    private Map authorMap; // hashtable map for author-ID pairs
    private Map pressMap; // hashtable map for press-ID pairs

    /**
     * Create a collection of books
     */
    public CollectionOfBooks() {
        library = new ArrayList<Book>(); // initiate collection
        numBooks = 0; // start from 0 books
        numID = -1; // Book ID starts from 0, then 1,2,etc. numID++ before assigning a new ID.
        titleMap = new Map(); // initiate titleMap
        authorMap = new Map(); // initiate authorMap
        pressMap = new Map(); // initiate pressMap
        loadDatabase(); // load from book list
    }

    /**
     * Insert books from BookList.txt to library
     */
    private void loadDatabase() {
        try {
            File database = new File("BookList.txt");
            Scanner inFile = new Scanner(database);
            
            // the lines are formatted as title/author/press/pages/description/---
            int lineCount = 1;
            String title = "";
            String author = "";
            String press = "";
            int pages = 0;
            String description = "";
    
            while (inFile.hasNextLine()) {
                if (lineCount % 6 == 1) {
                    title = inFile.nextLine();
                    lineCount++;
                }
                if (lineCount % 6 == 2) {
                    author = inFile.nextLine();
                    lineCount++;
                }
                if (lineCount % 6 == 3) {
                    press = inFile.nextLine();
                    lineCount++;
                }
                if (lineCount % 6 == 4) {
                    pages = Integer.parseInt(inFile.nextLine());
                    lineCount++;
                }
                if (lineCount % 6 == 5) {
                    description = inFile.nextLine();
                    lineCount++;
                }
                // once it reaches the --- line, the program has plenty of information to create a Book
                if (lineCount % 6 == 0) {
                    try {
                        addBook(title, author, press, pages, description);
                        numBooks++;
                    } catch (Exception e2) {
                        throw new NullPointerException("database error");
                    }
                    // clear info and get ready for the next book
                    title = null;
                    author = null;
                    press = null;
                    pages = 0;
                    description = null;
                    lineCount++;
                    inFile.nextLine(); // skip a line
                }
            }
        } catch (FileNotFoundException ef) {
            System.out.println(ef.getMessage());
        }
    }

    /**
     * Add a book based on given info
     * 
     * @param title // title of the book
     * @param author // author of the book
     * @param press // press of the book
     * @param pages // pages of the book
     * @param description // description of the book
     * @return ID of the book
     */
    public int addBook(String title, String author, String press, int pages, String description) {
        // exclude invalid cases
        if (title == null || title.isEmpty()
            || author == null || author.isEmpty()
            || press == null || press.isEmpty()
            || pages <= 0 
            || description == null || description.isEmpty()) {
            throw new NullPointerException("add book failed");
        }

        // move to the next ID and add a book
        numID++;
        numBooks++;
        int newBookID = numID;
        library.add(new Book(newBookID, title, author, press, pages, description));
        
        // add pairs to the hashtables
        String newBookIDString = Integer.toString(newBookID);
        titleMap.addPair(title.toLowerCase().trim(), newBookIDString);
        authorMap.addPair(author.toLowerCase().trim(), newBookIDString);
        pressMap.addPair(press.toLowerCase().trim(), newBookIDString);
        return newBookID;
    }

    /**
     * Check whether an ID is valid
     * 
     * @param id given ID
     * @return true if it's valid, false if not
     */
    public boolean validID(int id) {
        // if it's larger than largest assigned ID or less than 0 then invalid
        if (id > numID || id < 0) {
            return false;
        }

        // check whether this ID exists
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getID() == id) {
                return true;
            }
        }

        // if it does not exist, return false
        return false;
    }

    /**
     * Return a book according to its ID
     * 
     * @param id the ID of the book
     * @return the corresponding book
     */
    public Book getBookByID(int id) {
        if (!validID(id)) {
            throw new NullPointerException("ID not valid");
        }

        // library uses continuous id assignment starting with 0
        return (library.get(id));
    }

    /**
     * Return a book according to its title
     * 
     * @param title the title of the book
     * @return the correponding book
     */
    public ArrayList<Book> getBookByTitle(String title) {
        ArrayList<Book> arrBook = new ArrayList<Book>();

        try {
            Scanner sc = new Scanner(titleMap.keyToValue(title));
            // The map returns a String with several IDs separated by space
            // so we need to record them one by one
            while (sc.hasNextInt()) {
                int newInt = sc.nextInt();
                arrBook.add(getBookByID(newInt));
            }
            
            // no search result
            if (arrBook.size() == 0) {
                throw new NullPointerException("get book by title error");
            }

            return arrBook;
        } catch (Exception e) {
            //
        }

        throw new NullPointerException("get book by title error");
    }

    /**
     * Return a book according to its author
     * 
     * @param author the author of the book
     * @return the corresponding book
     */
    public ArrayList<Book> getBookByAuthor(String author) {
        ArrayList<Book> arrBook = new ArrayList<Book>();

        try {
            Scanner sc = new Scanner(authorMap.keyToValue(author));
            // The map returns a String with several IDs separated by space
            // so we need to record them one by one
            while (sc.hasNextInt()) {
                int newInt = sc.nextInt();
                arrBook.add(getBookByID(newInt));
            }
            
            // no search result
            if (arrBook.size() == 0) {
                throw new NullPointerException("get book by title error");
            }

            return arrBook;
        } catch (Exception e) {
            //
        }

        throw new NullPointerException("get book by author error");
    }

    /**
     * Return a book according to its press
     * 
     * @param press the press of the book
     * @return the corresponding book
     */
    public ArrayList<Book> getBookByPress(String press) {
        ArrayList<Book> arrBook = new ArrayList<Book>();

        try {
            Scanner sc = new Scanner(pressMap.keyToValue(press));
            // The map returns a String with several IDs separated by space
            // so we need to record them one by one
            while (sc.hasNextInt()) {
                int newInt = sc.nextInt();
                arrBook.add(getBookByID(newInt));
            }
            
            // no search result
            if (arrBook.size() == 0) {
                throw new NullPointerException("get book by title error");
            }

            return arrBook;
        } catch (Exception e) {
            //
        }

        throw new NullPointerException("get book by press error");
    }

    /**
     * Get a full list of books
     * 
     * @return the full list of books
     */
    public String getBookList() {
        String toReturn = "";
        for (int i = 0; i < library.size(); i++) {
            toReturn += (library.get(i).getFullInfo() + "\n\n");
        }
        return toReturn;
    }

    /**
     * Return the number of books
     * 
     * @return the number of books
     */
    public int size() {
        return numBooks;
    }
}