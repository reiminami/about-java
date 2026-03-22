package enemy;

public class Slime extends Enemy {
    public Slime(String name, int hp) {
        super(name, hp);
    }

    @Override
    public void attack() {
        IO.println(name + ": take this!");
    }
}
