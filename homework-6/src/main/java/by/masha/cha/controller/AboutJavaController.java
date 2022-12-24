package by.masha.cha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AboutJavaController {

    @RequestMapping(value = { "/about-java" }, method = RequestMethod.GET)
    public String aboutUsPage(Model model) {
        model.addAttribute("about", " is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.");
        return "aboutJavaPage";
    }
}

