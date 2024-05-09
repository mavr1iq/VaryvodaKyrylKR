package org.example.kr.model;

public class Type {
    private final int id;
    private final String name;
    private final int typeId;
    public Type(int id, String name, int typeId) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public int getTypeId() { return typeId; }
}
