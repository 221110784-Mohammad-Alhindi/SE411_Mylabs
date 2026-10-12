
package edu.psu.se411.lab09_inventory;

public class SearchById implements SearchStrategy<Item> {

    private String id;

    public SearchById(String id) {
        this.id = id;
    }

    @Override
    public boolean matches(Item item) {
        return item.getId().equalsIgnoreCase(id);
    }
}
