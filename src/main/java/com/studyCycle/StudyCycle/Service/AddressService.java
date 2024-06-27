package com.studyCycle.StudyCycle.Service;


import com.studyCycle.StudyCycle.Configuration.JwtRequestFilter;
import com.studyCycle.StudyCycle.Repository.AddressRepository;
import com.studyCycle.StudyCycle.Repository.UserRepository;
import com.studyCycle.StudyCycle.entity.Address;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;
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
}
