package asish.kdu.services;

import asish.kdu.entities.Tyre;
import asish.kdu.enums.TyreBrand;
import asish.kdu.exceptions.TyreException;
import org.springframework.stereotype.Component;

/**
 * Component annotation ensures Bean of TyreService is
 * created for Injection.
 */
@Component
public class TyreService {
    /**
     * Generates and returns appropriate Tyre object.
     * @return {@link  asish.kdu.entities.Tyre Tyre} object
     * @param brand Enum of TyreBrand
     * @throws TyreException When invalid value is passed
     */
    public Tyre generateTyre(TyreBrand brand) throws TyreException {
        if (brand.equals(TyreBrand.BRIDGESTONE)) {
            return new Tyre(TyreBrand.BRIDGESTONE, 150);
        } else if (brand.equals(TyreBrand.MRF)) {
            return new Tyre(TyreBrand.MRF, 120);
        }
        throw new TyreException("Invalid tyre brand", new IllegalArgumentException());
    }
}