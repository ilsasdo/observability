package devfest2025;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CloneFactoryService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CloneFactoryService.class);
    private final CloneRepository cloneRepository;

    public CloneFactoryService(CloneRepository cloneRepository) {
        this.cloneRepository = cloneRepository;
    }

    @Transactional
    @WithSpan("creating-a-new-clone")
    public Clone createClone() {
        try {
            log.info("Working hard to create a clone");
            Thread.sleep(1_000); // ah-ah! the Rebels were here slowing us down!
            Clone clone = new Clone();
            clone.setName(RandomStringUtils.randomAlphabetic(10));
            clone.setPlanet("Coruscant");

            log.info("New clone is now ready");
            Clone savedClone = cloneRepository.save(clone);
            return savedClone;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
