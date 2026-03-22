package classobj;

public record Item(String name) {
    public Item {
        IO.println("Item: " + name);
    }
}
