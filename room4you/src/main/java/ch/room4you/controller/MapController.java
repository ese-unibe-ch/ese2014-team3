package ch.room4you.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

@Controller
public class MapController {

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String map(Model model) {
        return "map";
    }

}
