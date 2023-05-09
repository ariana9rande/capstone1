package hjh.capstone.domain.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository
{
    private final EntityManager em;
    private final List<Restaurant> restaurantList = new ArrayList<>();

    @Autowired
    public RestaurantRepositoryImpl(EntityManager em)
    {
        this.em = em;
    }

    @Override
    public Restaurant save(Restaurant restaurant)
    {
        em.persist(restaurant);
        return restaurant;
    }

    @Override
    public Optional<Restaurant> findById(Long restId)
    {
        if (restId == null)
        {
            return Optional.empty();
        }
        Restaurant restaurant = em.find(Restaurant.class, restId);
        return Optional.ofNullable(restaurant);
    }

    @Override
    public List<Restaurant> findAll()
    {
        return em.createQuery("select r from Restaurant r", Restaurant.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long restId)
    {
        Restaurant restaurant = em.find(Restaurant.class, restId);
        if (restaurant != null)
        {
            em.remove(restaurant);
        }
    }
}



