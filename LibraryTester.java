// --== CS400 File Header Information ==--
// Name: Zhonghao Liu
// Email: zliu882@wisc.edu
// Notes to Grader: This is the tester with JUnit
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryTester {
    // Test adding a book. If it's unsuccessful, the size won't change.
    @Test
    public void testAddABook() {
	CollectionOfBooks cob = new CollectionOfBooks();
        int size = cob.size();
        String title = "title";
        String author = "author";
        String press = "press";
        int pages = 100;
        String description = "description";

        for (int i = 0; i < 10; i++) {
            title = title + "a";
            author = author + "b";
            press = press + "c";
            pages++;
            description = description + "d";
            cob.addBook(title, author, press, pages, description);
        }
        
        assertEquals(size + 10, cob.size());
    }

    // Test searching a book by title. There should be 2 books with this name.
    // Notice that all keys in the hashtable are lower case, so I did so in this tester
    @Test
    public void testSearchByTitle() {
	CollectionOfBooks cob = new CollectionOfBooks();
        ArrayList<Book> arrBooks = cob.getBookByTitle("introductory to java");
        assertEquals(2, arrBooks.size());
        for (int i = 0; i < arrBooks.size();i++) {
            assertEquals(arrBooks.get(i).getTitle(), "Introductory to Java");
        }
    }

    // Test searching a book by author. There should be 2 books by this author.
    @Test
    public void testSearchByAuthor() {
	CollectionOfBooks cob = new CollectionOfBooks();
        ArrayList<Book> arrBooks = cob.getBookByAuthor("john doe");
        assertEquals(2, arrBooks.size());
        for (int i = 0; i < arrBooks.size();i++) {
            assertEquals(arrBooks.get(i).getAuthor(), "John Doe");
        }
    }  
    
    // Test searching a book by press. There should be 2 books by this press.
    @Test
    public void testSearchByPress() {
	CollectionOfBooks cob = new CollectionOfBooks();
        ArrayList<Book> arrBooks = cob.getBookByPress("uw press");
        assertEquals(2, arrBooks.size());
        for (int i = 0; i < arrBooks.size();i++) {
            assertEquals(arrBooks.get(i).getPress(), "UW Press");
        }
    }

    // Test searching a book by ID. There should be only 1 book by this ID.
    @Test
    public void testSearchByID() {
	CollectionOfBooks cob = new CollectionOfBooks();
        Book thisBook = cob.getBookByID(0);
        assertEquals(thisBook.getTitle(), "Introductory to Java");
        assertEquals(thisBook.getAuthor(), "John Doe");
        assertEquals(thisBook.getPress(), "UW Press");
    }
}
