package hjh.capstone.data;

import hjh.capstone.domain.restaurant.Restaurant;
import hjh.capstone.domain.restaurant.RestaurantRepository;
import hjh.capstone.domain.restaurant.menu.Menu;
import hjh.capstone.domain.wait.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner
{
    private final RestaurantRepository restaurantRepository;
    private final EntityManager em;

    @Autowired
    public DataLoader(RestaurantRepository restaurantRepository, EntityManager em)
    {
        this.restaurantRepository = restaurantRepository;
        this.em = em;
    }

    LocalDateTime now = LocalDateTime.now();
    String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    LocalDateTime startTime = LocalDateTime.parse(formattedDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


    @Override
    @Transactional
    public void run(String[] args) throws Exception
    {
        Long count = em.createQuery("select count(*) from Restaurant", Long.class)
                .getSingleResult();

        if (count == 0)
        {
//            restaurantRepository.save(new Restaurant("밥이랑", "강남", "한식", 4.5, "image1.jpg", 37.12345, 127.12345, 2.5));
//            restaurantRepository.save(new Restaurant("Pizza Heaven", "홍대", "양식", 4.0, "image2.jpg", 37.54321, 126.98765, 3.8));
//            restaurantRepository.save(new Restaurant("마키야", "신촌", "일식", 3.5, "image3.jpg", 37.55555, 126.93611, 1.2));
//            restaurantRepository.save(new Restaurant("카레집", "건대", "인도 요리", 4.3, "image4.jpg", 37.54033, 127.06914, 1.8));
//            restaurantRepository.save(new Restaurant("덮밥집", "잠실", "일식", 3.8, "image5.jpg", 37.51122, 127.09892, 4.5));
//            restaurantRepository.save(new Restaurant("햄버거 집", "성수", "양식", 4.7, "image6.jpg", 37.54252, 127.04445, 2.2));
//            restaurantRepository.save(new Restaurant("돼지국밥", "노량진", "한식", 3.9, "image7.jpg", 37.51233, 126.94298, 1.5));
//            restaurantRepository.save(new Restaurant("샌드위치", "이태원", "양식", 4.1, "image8.jpg", 37.53451, 126.99415, 3.0));

            // 레스토랑 1
            Restaurant restaurant1 = new Restaurant("밥이랑", "강남", "한식", 4.5, "/images/image1.jpg", 37.12345, 127.12345, 2.5);
            List<Menu> menus1 = new ArrayList<>();
            List<Wait> waits1 = new ArrayList<>();
            menus1.add(new Menu("김치찌개", 8000, "image1-1.jpg", restaurant1));
            menus1.add(new Menu("된장찌개", 8000, "image1-2.jpg", restaurant1));
            menus1.add(new Menu("불고기", 12000, "image1-3.jpg", restaurant1));
            menus1.add(new Menu("제육볶음", 10000, "image1-4.jpg", restaurant1));
            menus1.add(new Menu("김밥", 3000, "image1-5.jpg", restaurant1));
            restaurant1.setMenus(menus1);
            restaurantRepository.save(restaurant1);

//            waits1.add(new Wait(1, startTime, restaurant1));
//            restaurant1.setWaits(waits1);
//            restaurantRepository.save(restaurant1);

            // 레스토랑 2
            Restaurant restaurant2 = new Restaurant("Pizza Heaven", "홍대", "양식", 4.0, "/images/image2.jpg", 37.54321, 126.98765, 3.8);
            List<Menu> menus2 = new ArrayList<>();
            menus2.add(new Menu("페퍼로니 피자", 15000, "image2-1.jpg", restaurant2));
            menus2.add(new Menu("치즈 피자", 14000, "image2-2.jpg", restaurant2));
            menus2.add(new Menu("불고기 피자", 17000, "image2-3.jpg", restaurant2));
            menus2.add(new Menu("크림 파스타", 12000, "image2-4.jpg", restaurant2));
            menus2.add(new Menu("샐러드", 8000, "image2-5.jpg", restaurant2));
            restaurant2.setMenus(menus2);
            restaurantRepository.save(restaurant2);

            // 레스토랑 3
            Restaurant restaurant3 = new Restaurant("마키야", "신촌", "일식", 3.5, "/images/image3.jpg", 37.55555, 126.93611, 1.2);
            List<Menu> menus3 = new ArrayList<>();
            menus3.add(new Menu("초밥 세트", 25000, "image3-1.jpg", restaurant3));
            menus3.add(new Menu("덮밥", 8000, "image3-2.jpg", restaurant3));
            menus3.add(new Menu("우동", 9000, "image3-3.jpg", restaurant3));
            menus3.add(new Menu("가츠동", 12000, "image3-4.jpg", restaurant3));
            menus3.add(new Menu("라멘", 10000, "image3-5.jpg", restaurant3));
            restaurant3.setMenus(menus3);
            restaurantRepository.save(restaurant3);

            // 레스토랑 4
            Restaurant restaurant4 = new Restaurant("카레집", "건대", "인도 요리", 4.3, "/images/image4.jpg", 37.54033, 127.06914, 1.8);
            List<Menu> menus4 = new ArrayList<>();
            menus4.add(new Menu("치킨 카레", 9000, "image4-1.jpg", restaurant4));
            menus4.add(new Menu("양채 카레", 10000, "image4-2.jpg", restaurant4));
            menus4.add(new Menu("순두부 카레", 8000, "image4-3.jpg", restaurant4));
            menus4.add(new Menu("커리 라이스", 7000, "image4-4.jpg", restaurant4));
            menus4.add(new Menu("나시고랭", 5000, "image4-5.jpg", restaurant4));
            restaurant4.setMenus(menus4);
            restaurantRepository.save(restaurant4);

            // 레스토랑 5
            Restaurant restaurant5 = new Restaurant("덮밥집", "잠실", "일식", 3.8, "/images/image5.jpg", 37.51122, 127.09892, 4.5);
            List<Menu> menus5 = new ArrayList<>();
            menus5.add(new Menu("카츠동", 8000, "image5-1.jpg", restaurant5));
            menus5.add(new Menu("돈까스 덮밥", 7500, "image5-2.jpg", restaurant5));
            menus5.add(new Menu("소바", 6000, "image5-3.jpg", restaurant5));
            menus5.add(new Menu("회덮밥", 12000, "image5-4.jpg", restaurant5));
            menus5.add(new Menu("일식 우동", 6000, "image5-5.jpg", restaurant5));
            restaurant5.setMenus(menus5);
            restaurantRepository.save(restaurant5);

            // 레스토랑 6
            Restaurant restaurant6 = new Restaurant("햄버거 집", "성수", "양식", 4.7, "/images/image6.jpg", 37.54252, 127.04445, 2.2);
            List<Menu> menus6 = new ArrayList<>();
            menus6.add(new Menu("치즈버거", 7000, "image6-1.jpg", restaurant6));
            menus6.add(new Menu("불고기버거", 8000, "image6-2.jpg", restaurant6));
            menus6.add(new Menu("치킨버거", 7500, "image6-3.jpg", restaurant6));
            menus6.add(new Menu("감자튀김", 3000, "image6-4.jpg", restaurant6));
            menus6.add(new Menu("음료", 2000, "image6-5.jpg", restaurant6));
            restaurant6.setMenus(menus6);
            restaurantRepository.save(restaurant6);

            // 레스토랑 7
            Restaurant restaurant7 = new Restaurant("돼지국밥", "노량진", "한식", 3.9, "/images/image7.jpg", 37.51233, 126.94298, 1.5);
            List<Menu> menus7 = new ArrayList<>();
            menus7.add(new Menu("돼지국밥", 8000, "image7-1.jpg", restaurant7));
            menus7.add(new Menu("순대국밥", 8000, "image7-2.jpg", restaurant7));
            menus7.add(new Menu("김치찜", 15000, "image7-3.jpg", restaurant7));
            menus7.add(new Menu("곱창전골", 20000, "image7-4.jpg", restaurant7));
            menus7.add(new Menu("물회", 25000, "image7-5.jpg", restaurant7));
            restaurant7.setMenus(menus7);
            restaurantRepository.save(restaurant7);

            // 레스토랑 8
            Restaurant restaurant8 = new Restaurant("샌드위치", "이태원", "양식", 4.1, "/images/image8.jpg", 37.53451, 126.99415, 3.0);
            List<Menu> menus8 = new ArrayList<>();
            menus8.add(new Menu("터키 샌드위치", 8000, "image8-1.jpg", restaurant8));
            menus8.add(new Menu("이탈리안 샌드위치", 10000, "image8-2.jpg", restaurant8));
            menus8.add(new Menu("치즈버거", 12000, "image8-3.jpg", restaurant8));
            menus8.add(new Menu("치킨 스테이크 샌드위치", 13000, "image8-4.jpg", restaurant8));
            menus8.add(new Menu("샐러드", 8000, "image8-5.jpg", restaurant8));
            restaurant8.setMenus(menus8);
            restaurantRepository.save(restaurant8);

            // 레스토랑 9
            Restaurant restaurant9 = new Restaurant("감바스 알 아히요", "홍대", "양식", 4.3, "/images/image9.jpg", 37.55555, 126.93611, 2.7);
            List<Menu> menus9 = new ArrayList<>();
            menus9.add(new Menu("감바스 알 아히요", 15000, "image9-1.jpg", restaurant9));
            menus9.add(new Menu("스테이크", 25000, "image9-2.jpg", restaurant9));
            menus9.add(new Menu("새우 버터 갈릭 파스타", 12000, "image9-3.jpg", restaurant9));
            menus9.add(new Menu("시저 샐러드", 8000, "image9-4.jpg", restaurant9));
            menus9.add(new Menu("감바스 알 아히요 샐러드", 12000, "image9-5.jpg", restaurant9));
            restaurant9.setMenus(menus9);
            restaurantRepository.save(restaurant9);

            // 레스토랑 10
            Restaurant restaurant10 = new Restaurant("할매국밥", "건대", "한식", 4.1, "/images/image10.jpg", 37.55555, 126.93611, 1.8);
            List<Menu> menus10 = new ArrayList<>();
            menus10.add(new Menu("국밥", 8000, "image10-1.jpg", restaurant10));
            menus10.add(new Menu("뼈해장국", 9000, "image10-2.jpg", restaurant10));
            menus10.add(new Menu("순대국밥", 10000, "image10-3.jpg", restaurant10));
            menus10.add(new Menu("칼국수", 8000, "image10-4.jpg", restaurant10));
            menus10.add(new Menu("묵밥", 7000, "image10-5.jpg", restaurant10));
            restaurant10.setMenus(menus10);
            restaurantRepository.save(restaurant10);
        }
//        List<Member> members = memberService.findMembers();
//        System.out.println("members = " + members);
//        List<Restaurant> restaurants = restaurantService.findAll();
//        System.out.println("restaurants = " + restaurants);
//        System.out.println("count = " + count);
    }
}
