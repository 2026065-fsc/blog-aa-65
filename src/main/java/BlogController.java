import org.springframework.stereotype.Controller;

@Controller
public class BlogController {
    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    
}
