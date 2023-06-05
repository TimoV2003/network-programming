package Minigames.Gwent;

class Card {
    private String name;
    private int strength;
    private String ability;
    private String faction;

    public Card(String name, int strength, String faction, String ability) {
        this.name = name;
        this.strength = strength;
        this.faction = faction;
        this.ability = ability;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public String getFaction() {
        return faction;
    }

    public String getAbility() {
        return ability;
    }
}
