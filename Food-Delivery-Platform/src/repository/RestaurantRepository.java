package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Restaurant;

public class RestaurantRepository {
    private HashMap<String, Restaurant> restaurantDataById;
    private HashMap<String, List<String>> restaurantDataByCity;

    public RestaurantRepository() {
        this.restaurantDataById = new HashMap<>();
        this.restaurantDataByCity = new HashMap<>();
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurantDataById.put(restaurant.getRestaurantId(), restaurant);
        List<String> restaurantByCity = restaurantDataByCity.getOrDefault(
                restaurant.getRestaurantId(),
                new ArrayList<>()
        );
        restaurantByCity.add(restaurant.getRestaurantId());
        restaurantDataByCity.put(restaurant.getRestaurantAddress().getCity(), restaurantByCity);
    }

    public Restaurant getRestaurantById(String id) {
        return restaurantDataById.get(id);
    }

    public List<Restaurant> getAllRestaurant() {
        return new ArrayList<>(restaurantDataById.values());
    }

    public List<Restaurant> getRestaurantByCityName(String city) {
       List<Restaurant> list = new ArrayList<>();
       restaurantDataByCity.get(city).forEach(r -> list.add(restaurantDataById.get(r)));
       return list;
    }
}
