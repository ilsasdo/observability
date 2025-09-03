package devfest2025;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    private final RabbitTemplate rabbitTemplate;

    public IndexController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/build-clone")
    public Clone buildClone() {
        rabbitTemplate.convertAndSend(CloneFactory.topicExchangeName, "foo.bar.baz", "Yes Emperor, we are working!");
        return new Clone(RandomStringUtils.randomAlphabetic(10));
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
