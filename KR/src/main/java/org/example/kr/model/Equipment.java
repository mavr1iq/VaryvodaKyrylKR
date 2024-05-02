package org.example.kr.model;

public class Equipment {
    private final int id;
    private final String name;
    private final String description;
    private final double price;
    private final String type;
    public Equipment(int id, String name, String description,String type, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getType() { return type; }
}
