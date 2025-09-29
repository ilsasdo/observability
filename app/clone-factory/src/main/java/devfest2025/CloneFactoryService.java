package devfest2025;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CloneFactoryService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CloneFactoryService.class);
    private final CloneRepository cloneRepository;

    private final io.opentelemetry.api.metrics.LongCounter savedClonesCounter;

    public CloneFactoryService(CloneRepository cloneRepository) {
        this.cloneRepository = cloneRepository;
        io.opentelemetry.api.metrics.Meter meter = GlobalOpenTelemetry.getMeter("clone-factory");
        this.savedClonesCounter = meter.counterBuilder("saved_clones")
            .setDescription("Number of clones saved")
            .build();
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

            if (Math.random() < 0.3) {
                throw new RuntimeException("Clone creation failed");
            }

            log.info("New clone is now ready");
            Clone savedClone = cloneRepository.save(clone);
            savedClonesCounter.add(1);
            return savedClone;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
