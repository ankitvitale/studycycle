package com.studyCycle.StudyCycle.Service;


import com.studyCycle.StudyCycle.Configuration.JwtRequestFilter;
import com.studyCycle.StudyCycle.Payload.AddressModel;
import com.studyCycle.StudyCycle.Repository.AddressRepository;
import com.studyCycle.StudyCycle.Repository.UserRepository;
import com.studyCycle.StudyCycle.entity.Address;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    public Address saveAddress(double latitude, double longitude) {
        String email = JwtRequestFilter.CURRENT_USER;

        // Fetch the user from the database
        User currentUser = userRepository.findByEmail(email);

        Address address=new Address();
        address.setLatitude(latitude);
        address.setLongitude(longitude);
        address.setUser(currentUser);
        return addressRepository.save(address);

    }

    public List<AddressModel> getAddress() {
        List<Address> addresses = addressRepository.findAllByUser(userService.findUser(JwtRequestFilter.CURRENT_USER));
        return addresses.stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    private AddressModel convertToModel(Address address) {
        AddressModel model = new AddressModel();
        model.latitude = address.getLatitude();
        model.longitude = address.getLongitude();
        model.address_id=address.getId();
        return model;
    }

    public void setDefaultAddress(Long id) {
        Optional<Address> address= addressRepository.findById(id);
        if(address.isPresent()){
            Address address1=address.get();
            User user=userService.findUser(JwtRequestFilter.CURRENT_USER);
            if(user == address1.getUser()) {
                user.setDefultaddresse(address1);
                userRepository.save(user);
            }

        }
    }
}
