package hjh.capstone.domain.restaurant.menu;

import hjh.capstone.domain.restaurant.Restaurant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Menu
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;
    private String menuName;
    private int price;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id")
    private Restaurant restaurant;

    public Menu(String name, int price, String image, Restaurant restaurant)
    {
        this.menuName = name;
        this.price = price;
        this.image = image;
        this.restaurant = restaurant;
    }

    public Menu()
    {

    }
}