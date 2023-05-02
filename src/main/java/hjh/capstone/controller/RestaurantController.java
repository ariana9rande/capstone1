package hjh.capstone.controller;

import hjh.capstone.domain.restaurant.Restaurant;
import hjh.capstone.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RestaurantController
{
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService)
    {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/")
    public String indexRestaurantList(Model model)
    {
        List<Restaurant> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants", restaurants);
        return "index";
    }

    @GetMapping("/{restId}")
    public String restaurantPage(@PathVariable Long restId, Model model)
    {
        Restaurant restaurant = restaurantService.findRestaurant(restId);
        model.addAttribute("restaurant", restaurant);
        return "restaurant";
    }

    @GetMapping("/{restId}/waiting")
    public String waitPage(@PathVariable Long restId, Model model)
    {
        Restaurant restaurant = restaurantService.findRestaurant(restId);
        model.addAttribute("restaurant", restaurant);
        return "waiting";
    }

    @GetMapping("/restaurants")
    public String memberList(Model model)
    {
        List<Restaurant> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants", restaurants);
        return "restaurants";
    }
}
