package devfest2025;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    private final RabbitTemplate rabbitTemplate;
    private final SlowCloneFactory slowCloneFactory;

    public IndexController(RabbitTemplate rabbitTemplate, SlowCloneFactory slowCloneFactory) {
        this.rabbitTemplate = rabbitTemplate;
        this.slowCloneFactory = slowCloneFactory;
    }

    @GetMapping("/build-clone")
    public Clone buildClone() {
        rabbitTemplate.convertAndSend(CloneFactory.topicExchangeName, "foo.bar.baz", "Yes Emperor, we are working!");
        return slowMethod1();
    }

    private Clone slowMethod1() {
        try {
            Thread.sleep(1);
            return slowCloneFactory.createClone();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
