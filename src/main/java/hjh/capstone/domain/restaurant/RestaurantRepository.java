package hjh.capstone.domain.restaurant;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository
{
    Restaurant save(Restaurant restaurant);

    Optional<Restaurant> findById(Long restId);

    List<Restaurant> findAll();

    void deleteById(Long restId);


}
