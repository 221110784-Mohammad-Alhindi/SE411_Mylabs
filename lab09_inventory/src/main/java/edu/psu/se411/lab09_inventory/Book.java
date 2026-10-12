
package edu.psu.se411.lab09_inventory;

public class Book implements Item {

    private String id;
    private String name;
    private String authorName;

    public Book(String id, String name, String authorName) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return authorName;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name
                + ", author=" + authorName + "]";
    }
}
