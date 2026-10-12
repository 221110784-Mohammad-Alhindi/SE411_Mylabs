
package edu.psu.se411.lab09_inventory;

public class ElectronicDevice implements Item {

    private String id;
    private String name;
    private String category;

    public ElectronicDevice(String id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "ElectronicDevice [id=" + id + ", name=" + name
                + ", category=" + category + "]";
    }
}
