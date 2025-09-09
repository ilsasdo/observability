package devfest2025;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class SlowCloneFactory {

    private final CloneRepository cloneRepository;

    public SlowCloneFactory(CloneRepository cloneRepository) {
        this.cloneRepository = cloneRepository;
    }

    public Clone createClone() {
        try {
            Thread.sleep(1);
            Clone clone = new Clone();
            clone.setName(RandomStringUtils.randomAlphabetic(10));
            clone.setPlanet("Coruscant");
            return cloneRepository.save(clone);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
