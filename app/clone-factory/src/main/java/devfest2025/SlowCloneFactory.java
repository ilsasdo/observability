package devfest2025;

import devfest2025.IndexController.Clone;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class SlowCloneFactory {

    public Clone createClone() {
        try {
            Thread.sleep(1000);
            return new Clone(RandomStringUtils.randomAlphabetic(10));
        } catch (InterruptedException e) {
            return new Clone("Error");
        }
    }

}
