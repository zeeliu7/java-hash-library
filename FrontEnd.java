import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Exception;
import java.lang.Integer;

/**
 * This is the front end of the library search system
 */
public class FrontEnd {
    private static CollectionOfBooks cob; // collection of all books in library
    private static Scanner userInput; // ready to take in user's input

    /**
     * Setup cob and userInput
     */
    private static void setup() {
        cob = new CollectionOfBooks();
        userInput = new Scanner(System.in);
    }

    /**
     * Search a book by ID
     */
    private static void choiceOne() {
        System.out.println("Please enter book ID:");
        // As long as user does not input "stop", the searching process can infinitely go on
        while (userInput.hasNextLine()) {
            String userLine = userInput.nextLine();
            // if the user choose to stop, back to welcome page
            if (userLine.toLowerCase().trim().equals("stop")) {
                welcome();
            // search book by ID
            } else {
                int userBookID = Integer.parseInt(userLine);
                try {
                    Book searchResult = cob.getBookByID(userBookID);
                    // return search results
                    System.out.println("You are searching the book:\n");
                    System.out.println(searchResult.getFullInfo());
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
                System.out.println("Please enter book ID:");
            }
        }
    }

    /**
     * Search a book by title
     */
    private static void choiceTwo() {
        System.out.println("Please enter book title:");
        // As long as user does not input "stop", the searching process can infinitely go on
        while (userInput.hasNextLine()) {
            String userLine = userInput.nextLine();
            // if the user choose to stop, back to welcome page
            if (userLine.toLowerCase().trim().equals("stop")) {
                welcome();
            // search book by title
            } else {
                String userBookTitle = userLine.toLowerCase().trim();
                try {
                    ArrayList<Book> searchResult = cob.getBookByTitle(userBookTitle);
                    // traverse search results
                    System.out.println("You are searching the book(s):\n");
                    for (int i = 0; i < searchResult.size(); i++) {
                        System.out.println(searchResult.get(i).getFullInfo());
                        System.out.println("\n");
                    }
                } catch (Exception e2) {
                    System.out.println(e2.getMessage());
                }
                System.out.println("Please enter book title:");
            }
        }
    }

    /**
     * Search a book by author
     */
    private static void choiceThree() {
        System.out.println("Please enter book author:");
        // As long as user does not input "stop", the searching process can infinitely go on
        while (userInput.hasNextLine()) {
            String userLine = userInput.nextLine();
            // if the user choose to stop, back to welcome page
            if (userLine.toLowerCase().trim().equals("stop")) {
                welcome();
            // search book by author
            } else {
                String userBookAuthor = userLine.toLowerCase().trim();
                try {
                    ArrayList<Book> searchResult = cob.getBookByAuthor(userBookAuthor);
                    // traverse search results
                    System.out.println("You are searching the book(s):\n");
                    for (int i = 0; i < searchResult.size(); i++) {
                        System.out.println(searchResult.get(i).getFullInfo());
                        System.out.println("\n");
                    }
                } catch (Exception e3) {
                    System.out.println(e3.getMessage());
                }
                System.out.println("Please enter book author:");
            }
        }
    }

    /**
     * Search a book by press
     */
    private static void choiceFour() {
        System.out.println("Please enter book press:");
        // As long as user does not input "stop", the searching process can infinitely go on
        while (userInput.hasNextLine()) {
            String userLine = userInput.nextLine();
            // if the user choose to stop, back to welcome page
            if (userLine.toLowerCase().trim().equals("stop")) {
                welcome();
            // search book by press
            } else {
                String userBookPress = userLine.toLowerCase().trim();
                try {
                    ArrayList<Book> searchResult = cob.getBookByPress(userBookPress);
                    // traverse search results
                    System.out.println("You are searching the book(s):\n");
                    for (int i = 0; i < searchResult.size(); i++) {
                        System.out.println(searchResult.get(i).getFullInfo());
                        System.out.println("\n");
                    }
                } catch (Exception e4) {
                    System.out.println(e4.getMessage());
                }
                System.out.println("Please enter book press:");
            }
        }
    }

    /**
     * Show a full list of books generated by the CollectionOfBooks
     */
    private static void choiceFive() {
        System.out.println("Full book list:");
        System.out.println(cob.getBookList());
        welcome();
    }

    private static void welcome() {
        System.out.println("Welcome to UW Library! How can I help you?");
        System.out.println("1 - Search a book by ID");
        System.out.println("2 - Search a book by title");
        System.out.println("3 - Search a book by author");
        System.out.println("4 - Search a book by press");
        System.out.println("5 - Check the full list of books");
        System.out.println("6 - Exit");
        System.out.println("Once you selected and finished a service, you can type \"stop\" to stop the service");

        // Ask user to make a valid choice
        // (Sorry this interpretation looks silly but it's safe)
        int choice;
        while (true) {
            String choiceString = userInput.nextLine();
            if (choiceString.equals("1")) {
                choice = 1;
                break;
            }
            if (choiceString.equals("2")) {
                choice = 2;
                break;
            }
            if (choiceString.equals("3")) {
                choice = 3;
                break;
            }
            if (choiceString.equals("4")) {
                choice = 4;
                break;
            }
            if (choiceString.equals("5")) {
                choice = 5;
                break;
            }
            if (choiceString.equals("6")) {
                choice = 6;
                break;
            }
            System.out.println("Please choose again (1-6)");
        }

        // go to corresponding methods according to choice
        if (choice == 1) {
            choiceOne();
        }
        if (choice == 2) {
            choiceTwo();
        }
        if (choice == 3) {
            choiceThree();
        }
        if (choice == 4) {
            choiceFour();
        }
        if (choice == 5) {
            choiceFive();
        }
        if (choice == 6) {
            System.out.println("Thank you for visiting UW Library! Have a nice day!");
            System.exit(0); // terminate the program
        }
    }



    public static void main (String[] args) {
        setup();
        welcome();
        userInput.close();
    }
}