package repository;

import java.util.HashMap;

import model.FoodMenu;
import model.MenuItem;

public class FoodMenuRespository {

    private HashMap<String, FoodMenu> foodMenuById;

    private HashMap<String, String> foodMenuByRestaurantId;

    private HashMap<String, MenuItem> menuItemById;
}
