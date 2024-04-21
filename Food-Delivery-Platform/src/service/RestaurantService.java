package service;

import java.util.List;

import model.Restaurant;
import repository.RestaurantRepository;

public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantService() {
        this.restaurantRepository = new RestaurantRepository();
    }

    public void addRestaurant(Restaurant restaurant) {
        if(restaurantRepository.getRestaurantById(restaurant.getRestaurantId()) == null) {
            throw new RuntimeException("Restaurant Id exist");
        }
        restaurantRepository.addRestaurant(restaurant);
    }

    public List<Restaurant> getRestaurantByCity(String city) {
        return restaurantRepository.getRestaurantByCityName(city);
    }

    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.getAllRestaurant();
    }

    public Restaurant getRestaurant(String id) {
        return restaurantRepository.getRestaurantById(id);
    }
}
