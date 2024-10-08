package com.studyCycle.StudyCycle.Controller;

import com.studyCycle.StudyCycle.Payload.AddressModel;
import com.studyCycle.StudyCycle.Repository.UserRepository;
import com.studyCycle.StudyCycle.Service.AddressService;
import com.studyCycle.StudyCycle.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {


    @Autowired
  private  AddressService addressService;




@PostMapping("/saveaddress")
@PreAuthorize("hasRole('User')")
    public  ResponseEntity<String>  saveAddress(@RequestParam double latitude,@RequestParam double longitude){
    Address savedAddress = addressService.saveAddress(latitude, longitude);
    return new ResponseEntity<>("Address saved successfully", HttpStatus.CREATED);

    }
@GetMapping("/getAlluseraddress")
@PreAuthorize("hasRole('User')")
public List<AddressModel> getAddress(){

    return addressService. getAddress();

}
    @PutMapping("/setdefaultAddress/{id}")
    @PreAuthorize("hasRole('User')")
    public void setdefaultAddress(@PathVariable("id") Long id){
    addressService.setDefaultAddress(id);
    }
}
