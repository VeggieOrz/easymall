package easymall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller
public class ShowPageController {
 
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
    
        return page;
    }
}