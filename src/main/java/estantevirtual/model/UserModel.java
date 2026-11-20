package main.java.estantevirtual.model;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private Long id;
    private String name;
    private int level;
    private int xp;
    private List<UserMediaModel> shelf;

    public UserModel (Long id, String name) {
        this.id = id;
        this.name = name;
        this.level = 1;
        this.xp = 0;
        this.shelf = new ArrayList<>();
    }

    public void addXp (int amount) {
        xp += amount;
        while (xp >= level * 10) {
            xp -= level * 10;
            level ++;
        }
    }

    public int getLevel () { return level; }

    public List<UserMediaModel> getShelf () { return shelf; }

    public void addToShelf (UserMediaModel userMediaModel) { shelf.add(userMediaModel); }

    //Verificação deve ser feita no service
    public void removeFromShelf (UserMediaModel userMediaModel) { shelf.remove(userMediaModel); }

    public Long getId () { return id; }

    public String getName () { return name; }

    public void setName (String newName) { this.name = newName; }
}
