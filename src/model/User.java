package model;

public class User implements Comparable<User> {
    public String name;
    public String password;
    public int score;

    public User(String name, String password, int score) {
        this.name = name;
        this.password = password;
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("%-15s%d",name,score);
    }

    @Override
    public int compareTo(User o) {
        return o.score - this.score;
    }
}
