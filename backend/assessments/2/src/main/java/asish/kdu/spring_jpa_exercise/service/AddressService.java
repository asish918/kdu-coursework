package asish.kdu.spring_jpa_exercise.service;

import asish.kdu.spring_jpa_exercise.dtos.AddressDTO;
import asish.kdu.spring_jpa_exercise.dtos.mapper.AddressMapper;
import asish.kdu.spring_jpa_exercise.entity.Address;
import asish.kdu.spring_jpa_exercise.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void addAddress(AddressDTO addressDTO) {
        Address address = AddressMapper.dtoToEntity(addressDTO);
        addressRepository.save(address);
    }
}
