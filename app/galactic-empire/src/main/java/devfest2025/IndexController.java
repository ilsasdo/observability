package devfest2025;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final String cloneFactoryUrl;

    public IndexController(@Value("${clone-factory.url}") String cloneFactoryUrl) {
        this.cloneFactoryUrl = cloneFactoryUrl;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "/order-clones", method = RequestMethod.POST)
    public String orderClones(Model model) {
        Clone clone = restTemplate.getForEntity(cloneFactoryUrl + "/build-clone", Clone.class).getBody();
        if (clone == null) {
            log.error("Clone could not be built");
            model.addAttribute("error", "No clone built!");
            return "redirect:index";
        }
        log.info("Clone built: {}", clone.getName());
        model.addAttribute("name", clone.getName());
        return "redirect:index";
    }

    @RequestMapping(value = "/order-more-clones", method = RequestMethod.POST)
    public String moreClones(Model model) {
        Clone clone = restTemplate.getForEntity(cloneFactoryUrl + "/build-clone", Clone.class).getBody();
        if (clone == null) {
            log.error("Clone could not be built");
            model.addAttribute("error", "No clone built!");
            return "redirect:index";
        }
        log.info("Clone built: {}", clone.getName());
        model.addAttribute("name", clone.getName());
        return "redirect:index";
    }

    public static class Clone {

        private String name;

        public Clone(String name) {
            this.name = name;
        }

        public Clone() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
