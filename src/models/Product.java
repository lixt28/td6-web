package models;

public record Product(
    int id,
    String name,
    String owner,
    float bid
) {

    public int getId() {
        return id;
    }

    public float getBid() {
        return bid;
    }
}
