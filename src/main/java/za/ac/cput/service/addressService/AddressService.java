//Joshua Mokwebo
//Student No 222191562
//GitHubRepository:My_commic_city_project

package za.ac.cput.service.addressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.BillingAddress;
import za.ac.cput.domain.ShippingAddress;
import za.ac.cput.repository.addressRepository.AddressRepository;
import za.ac.cput.repository.addressRepository.BillingAddressRepository;
import za.ac.cput.repository.addressRepository.ShippingAddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService  implements IAddressService{

    private AddressRepository addressRepository;



    @Autowired
    AddressService (AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    @Override
    public Address create(Address address) {

        if(address == null)
            throw new IllegalArgumentException("address cannot be null");


        if (address != null) {
            System.out.println("checking if existing address exists");
            // AddressId addressId = new AddressId(address.getStreet(), address.getPostalCode());
            Optional<Address> existingAddress = addressRepository.findById(address.getId());
            if (existingAddress.isPresent()) {
                System.out.println("found address");
                address = existingAddress.get();
            } else {
                System.out.println("saving new address");
                address = addressRepository.save(address);
                System.out.println("Saved");
            }
        }
        return address;

    }


    @Override
    public Address read(Long aLong) {
        if (aLong < 0L)
            throw new IllegalArgumentException("id cannot be negative or zero");
        return addressRepository.findById(aLong).orElse(null);
    }

    @Override
    public Address update(Address address) {
        if (address == null)
            throw new IllegalArgumentException("address cannot be null");
        return addressRepository.save(address);
    }

    @Override
    public boolean delete(Long aLong) {
        if (aLong<0L)
            throw new IllegalArgumentException("id cannot be negative or zero");
        addressRepository.deleteById(aLong);
        return addressRepository.existsById(aLong);
    }

    @Override
    public List<Address> getall() {
        return addressRepository.findAll();
    }
}
