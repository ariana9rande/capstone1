package hjh.capstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController
{

//    @GetMapping("/")
//    public String index(Model model)
//    {
//        return "index";
//    }

    @GetMapping("/search")
    public String search(Model model)
    {
        return "search";
    }

}
