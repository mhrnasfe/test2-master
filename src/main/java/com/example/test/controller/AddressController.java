package com.example.test.controller;

import com.example.test.entity.Address;
import com.example.test.exeption.AllReadyExist;
import com.example.test.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity<Address> add(@RequestBody Address address) throws AllReadyExist {
        return ResponseEntity.ok(addressService.addAddress(address));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Address>> getAll() {
        return ResponseEntity.ok(addressService.all());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Address>> getAddressById(@PathVariable Long id){
        Optional<Address> address =  addressService.getAddressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAddress(@RequestBody Address add, @PathVariable Long id) {
        Address address = addressService.updateAddress(add, id);
        return new ResponseEntity<>("customer is updated successfully", HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<Address> updateAddress(@Valid @RequestBody Address address){
        Address updatedAddress=addressService.updateAddress(address);
        return new ResponseEntity<Address>(updatedAddress,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
