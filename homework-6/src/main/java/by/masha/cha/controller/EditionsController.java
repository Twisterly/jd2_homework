package by.masha.cha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EditionsController {

    @RequestMapping(value = { "/editions" }, method = RequestMethod.GET)
    public String versionsPage() {
        return "editionsPage";
    }
}
