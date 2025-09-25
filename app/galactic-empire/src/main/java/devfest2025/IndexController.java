package devfest2025;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
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

    private final CloneRepository cloneRepository;
    private static final Logger log = LoggerFactory.getLogger(IndexController.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final String cloneFactoryUrl;

    public IndexController(CloneRepository cloneRepository, @Value("${clone-factory.url}") String cloneFactoryUrl) {
        this.cloneRepository = cloneRepository;
        this.cloneFactoryUrl = cloneFactoryUrl;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/order-clones")
    public String orderClonesIndex(Model model) {
        return "index";
    }

    @GetMapping("/order-more-cloness")
    public String orderMoreClonesIndex(Model model) {
        return "index";
    }

    @RequestMapping(value = "/order-clones", method = RequestMethod.POST)
    public String orderClones(Model model) {
        Clone clone = restTemplate.getForEntity(cloneFactoryUrl + "/build-clone", Clone.class).getBody();
        if (clone == null) {
            log.error("Clone could not be built");
            model.addAttribute("error", "No clone built!");
            return "index";
        }
        log.info("Clone built: {}", clone.getName());
        model.addAttribute("name", clone.getName());
        model.addAttribute("count", cloneRepository.count());
        return "index";
    }

    @RequestMapping(value = "/order-more-clones", method = RequestMethod.POST)
    public String moreClones(Model model) {
        AtomicInteger errors = new AtomicInteger();
        AtomicInteger count = new AtomicInteger();
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            futures.add(Executors.newFixedThreadPool(5).submit(() -> {
                Clone clone = restTemplate.getForEntity(cloneFactoryUrl + "/build-clone", Clone.class).getBody();
                count.getAndIncrement();
            }));
        }

        futures.stream().forEach(f -> {
            try {
                f.get();
            } catch (Exception ignored) {
                errors.getAndIncrement();
            }
        });

        model.addAttribute("successCount", count.get());
        model.addAttribute("errorsCount", errors.get());

        model.addAttribute("count", cloneRepository.count());

        return "index";
    }

}
