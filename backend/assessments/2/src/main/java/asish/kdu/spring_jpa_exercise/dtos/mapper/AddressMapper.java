package asish.kdu.spring_jpa_exercise.dtos.mapper;

import asish.kdu.spring_jpa_exercise.dtos.AddressDTO;
import asish.kdu.spring_jpa_exercise.entity.Address;

public class AddressMapper {
    private AddressMapper() {

    }

    public static Address dtoToEntity(AddressDTO addressDTO) {
        Address address = new Address();

        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setStreet(addressDTO.getStreet());
        address.setPostalCode(addressDTO.getPostalCode());

        return address;
    }

    public static AddressDTO entityToDto(Address address) {
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setCity(address.getCity());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setState(address.getState());
        addressDTO.setPostalCode(address.getPostalCode());

        return addressDTO;
    }
}
