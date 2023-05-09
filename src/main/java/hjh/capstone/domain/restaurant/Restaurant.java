package hjh.capstone.domain.restaurant;

import hjh.capstone.domain.restaurant.menu.Menu;
import hjh.capstone.domain.wait.Wait;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Restaurant
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restId;
    private String restName;
    private String neighborhood;
    private String cuisine;
    private Double rating;
    private String image;
    private Double latitude;
    private Double longitude;
    private Double distance; // 단위: km

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "rest_id")
    private List<Menu> menus;

    @OneToMany(mappedBy = "restaurant", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Wait> waits;

    public Restaurant()
    {
    }

    public Restaurant(String restName, String neighborhood, String cuisine, Double rating, String image,
                      Double latitude,
                      Double longitude, Double distance)
    {
        this.restName = restName;
        this.neighborhood = neighborhood;
        this.cuisine = cuisine;
        this.rating = rating;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }
}
