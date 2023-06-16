package ua.ithillel.reflect.drink.model;

import java.util.List;

public class Drink {
    private String name;
    private String instruction;
    private List<String> ingredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "name='" + name + '\'' +
                ", instruction='" + instruction + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
