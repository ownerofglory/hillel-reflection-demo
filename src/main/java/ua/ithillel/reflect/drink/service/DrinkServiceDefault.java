package ua.ithillel.reflect.drink.service;

import ua.ithillel.reflect.drink.client.DrinkClient;
import ua.ithillel.reflect.drink.exception.DrinkClientException;
import ua.ithillel.reflect.drink.model.Drink;
import ua.ithillel.reflect.drink.model.response.Cocktail;
import ua.ithillel.reflect.drink.model.response.CocktailResponse;

import java.util.ArrayList;
import java.util.List;

public class DrinkServiceDefault implements DrinkService {
    private DrinkClient drinkClient;

    public DrinkServiceDefault(DrinkClient drinkClient) {
        this.drinkClient = drinkClient;
    }

    @Override
    public Drink getDrinkByName(String name) throws DrinkClientException {
        CocktailResponse byName = drinkClient.getByName(name.toLowerCase());


        return cocktailResponseToDrink(byName);
    }

    private Drink cocktailResponseToDrink(CocktailResponse cocktailResponse) throws DrinkClientException {
        List<Cocktail> drinks = cocktailResponse.getDrinks();
        if (drinks == null || drinks.size() == 0) {
            throw new DrinkClientException("Ne recipe found");
        }

        Cocktail cocktail = drinks.get(0);

        Drink drink = new Drink();

        List<String> ingredients = new ArrayList<>();

        drink.setName(cocktail.getStrDrink());
        drink.setInstruction(cocktail.getStrInstructions());
        drink.setIngredients(ingredients);

        if (cocktail.getStrIngredient1() != null) ingredients.add(cocktail.getStrIngredient1());
        if (cocktail.getStrIngredient2() != null) ingredients.add(cocktail.getStrIngredient2());
        if (cocktail.getStrIngredient3() != null) ingredients.add(cocktail.getStrIngredient3());
        if (cocktail.getStrIngredient4() != null) ingredients.add(cocktail.getStrIngredient4());
        if (cocktail.getStrIngredient5() != null) ingredients.add(cocktail.getStrIngredient5());
        if (cocktail.getStrIngredient6() != null) ingredients.add(cocktail.getStrIngredient6());
        if (cocktail.getStrIngredient7() != null) ingredients.add(cocktail.getStrIngredient7());
        if (cocktail.getStrIngredient8() != null) ingredients.add(cocktail.getStrIngredient8());
        if (cocktail.getStrIngredient9() != null) ingredients.add(cocktail.getStrIngredient9());
        if (cocktail.getStrIngredient10() != null) ingredients.add(cocktail.getStrIngredient10());


        return drink;
    }
}
