package asish.kdu.services;

import asish.kdu.entities.Speaker;
import asish.kdu.enums.SpeakerBrand;
import asish.kdu.exceptions.SpeakerException;
import org.springframework.stereotype.Component;

/**
 * Component annotation ensures Bean of SpeakerService is
 * created for Injection.
 */
@Component
public class SpeakerService {

    /**
     * Generates and returns appropriate Speaker object.
     * @return {@link  asish.kdu.entities.Speaker Speaker} object
     * @param brand Enum of SpeakerBrand
     * @throws SpeakerException When invalid value is passed
     */
    public Speaker generateSpeaker(SpeakerBrand brand) throws SpeakerException {
        if (brand.equals(SpeakerBrand.SONY)) {
            return new Speaker(SpeakerBrand.SONY, 200);
        } else if (brand.equals(SpeakerBrand.BOSE)) {
            return new Speaker(SpeakerBrand.BOSE, 300);
        }
        throw new SpeakerException("Invalid speaker brand", new IllegalArgumentException());
    }
}