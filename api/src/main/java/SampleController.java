import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chpy on 18/1/15.
 */

@Controller
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    public String home() {

        return "aaa";
    }
}
