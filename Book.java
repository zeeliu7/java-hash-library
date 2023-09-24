import java.lang.NullPointerException;

/**
 * This stores the info of a book
 */
public class Book {
    private int id; // ID of the book
    private String title; // title of the book
    private String author; // author of the book
    private String press; // press of the book
    private int pages; // pages of the book
    private String description; // description of the book

    /**
     * Creating a new book
     * 
     * @param id ID of the book
     * @param title title of the book
     * @param author author of the book
     * @param press press of the book
     * @param pages pages of the book
     * @param description description of the book
     */
    public Book (int id, String title, String author, String press, int pages, String description) {
        // exclude invalid cases
        if (title == null || author == null || press == null || description == null) {
            throw new NullPointerException("book creation failed");
        }

        // load the info
        this.id = id;
        this.title = title;
        this.author = author;
        this.press = press;
        this.pages = pages;
        this.description = description;
    }

    /**
     * Return the ID of the book
     * 
     * @return the ID of the book
     */
    public int getID() {
        return id;
    }

    /**
     * Return the title of the book
     * 
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Return the author of the book
     * 
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Return the press of the book
     * 
     * @return the press of the book
     */
    public String getPress() {
        return press;
    }

    /**
     * Return the pages of the book
     * 
     * @return the pages of the book
     */
    public int getPages() {
        return pages;
    }

    /**
     * Return the description of the book
     * 
     * @return the description of the book
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return all info of the book
     * 
     * @return all info of the book
     */
    public String getFullInfo() {
        return ("ID: " + id + "\n"
                + "Title:" + title + "\n"
                + "Author: " + author + "\n"
                + "Press: " + press + "\n"
                + "Pages: " + pages + "\n"
                + "Description: " + description
        );
    }
}
