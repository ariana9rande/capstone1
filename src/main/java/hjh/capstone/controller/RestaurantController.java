package hjh.capstone.controller;

import hjh.capstone.domain.restaurant.Restaurant;
import hjh.capstone.domain.wait.Wait;
import hjh.capstone.service.RestaurantService;
import hjh.capstone.service.WaitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RestaurantController
{
    private final RestaurantService restaurantService;
    private final WaitService waitService;

    public RestaurantController(RestaurantService restaurantService, WaitService waitService)
    {
        this.restaurantService = restaurantService;
        this.waitService = waitService;
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

//    @GetMapping("/{restId}/waiting")
//    public String waitPage(@PathVariable Long restId, Model model)
//    {
//        Restaurant restaurant = restaurantService.findRestaurant(restId);
//        model.addAttribute("restaurant", restaurant);
//        return "waiting/waiting";
//    }

    @GetMapping("/{restId}/waiting{number}")
    public String waitingPage(@PathVariable Long restId, @PathVariable String number, Model model)
    {
        Restaurant restaurant = restaurantService.findRestaurant(restId);
        model.addAttribute("restaurant", restaurant);

        long waitCount = waitService.getWaitCount(restId);
        model.addAttribute("waitCount", waitCount);

        long waitTime = waitService.getWaitTime(restId);
        model.addAttribute("waitTime", waitTime);

        return "waiting/waiting" + number;
    }

    @GetMapping("/{restId}/createWait")
    public String createWait(@PathVariable Long restId, @RequestParam(value = "memberId", required = false) Long memberId, Model model, HttpSession session)
    {
        if(memberId == null)
        {
            model.addAttribute("loginMessage", "로그인 후 이용해주세요.");
            return "members/login";
        }

        Restaurant restaurant = restaurantService.findRestaurant(restId);
        model.addAttribute("restaurant", restaurant);

        Wait wait = waitService.createNewWait(restId, memberId);
        model.addAttribute("waitNumber", wait.getWaitNumber());
        return "waiting/waiting2";
    }


    @GetMapping("/restaurants")
    public String memberList(Model model)
    {
        List<Restaurant> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants", restaurants);
        return "restaurants";
    }
}
