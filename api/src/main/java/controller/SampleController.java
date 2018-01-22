package controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chpy on 18/1/15.
 */

@RestController
@RequestMapping("/")
public class SampleController {

    @RequestMapping()
    public String home() {
        return "aa";
    }
}
