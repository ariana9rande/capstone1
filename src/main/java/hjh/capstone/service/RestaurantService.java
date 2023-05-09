package hjh.capstone.service;

import hjh.capstone.domain.restaurant.Restaurant;
import hjh.capstone.domain.restaurant.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantService
{

    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> findAll()
    {
        return restaurantRepository.findAll();
    }

    public Restaurant findRestaurant(Long restId)
    {
        return restaurantRepository.findById(restId)
                .orElseThrow(() -> new IllegalArgumentException("해당 레스토랑이 존재하지 않습니다. id=" + restId));
    }

    @Transactional
    public Restaurant save(Restaurant restaurant)
    {
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public Restaurant update(Long restId, Restaurant restaurant)
    {
        Restaurant updatedRestaurant = restaurantRepository.findById(restId)
                .orElseThrow(() -> new IllegalArgumentException("해당 레스토랑이 존재하지 않습니다. id=" + restId));

        updatedRestaurant.setRestName(restaurant.getRestName());
        updatedRestaurant.setNeighborhood(restaurant.getNeighborhood());
        updatedRestaurant.setCuisine(restaurant.getCuisine());
        updatedRestaurant.setRating(restaurant.getRating());
        updatedRestaurant.setImage(restaurant.getImage());
        updatedRestaurant.setLatitude(restaurant.getLatitude());
        updatedRestaurant.setLongitude(restaurant.getLongitude());
        updatedRestaurant.setDistance(restaurant.getDistance());

        return updatedRestaurant;
    }

    @Transactional
    public void delete(Long restId)
    {
        restaurantRepository.deleteById(restId);
    }


}
