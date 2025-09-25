package devfest2025;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SlowCloneFactory {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SlowCloneFactory.class);
    private final CloneRepository cloneRepository;

    public SlowCloneFactory(CloneRepository cloneRepository) {
        this.cloneRepository = cloneRepository;
    }

    @WithSpan("creating-a-new-clone")
    public Clone createClone() {
        try {
            log.info("Working hard to create a clone");
            Thread.sleep(1); // ah-ah! the Rebels were here slowing us down!
            Clone clone = new Clone();
            clone.setName(RandomStringUtils.randomAlphabetic(10));
            clone.setPlanet("Coruscant");

            log.info("New clone is now ready");
            return cloneRepository.save(clone);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
