package enemy;

public abstract class Enemy {
    protected String name;
    protected int hp;

    public Enemy(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public void status() {
        IO.println("%s (HP: %s)".formatted(name, hp));
    }

    public abstract void attack();
}
