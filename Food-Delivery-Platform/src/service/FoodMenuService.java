package service;

import model.FoodMenu;
import repository.FoodMenuRespository;

public class FoodMenuService {
    private FoodMenuRespository foodMenuRespository;

    public FoodMenuService(FoodMenuRespository foodMenuRespository) {
        this.foodMenuRespository = foodMenuRespository;
    }

    public void addMenuByRestaurantId(String restaurantId, FoodMenu foodMenu) {

    }
}
