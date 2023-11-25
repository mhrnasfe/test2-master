package com.example.test.controller;

import com.example.test.dto.ChangingTheOrderByTheCustomer;
import com.example.test.entity.Order;
import com.example.test.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
//        Order order1 = orderService.createOrder(order);
        Order newOrder = orderService.createOrder(order);
//        return ResponseEntity.ok(orderService.createOrder(order));
        return new ResponseEntity<>(newOrder,HttpStatus.CREATED);
    }
    @PostMapping("/addFromCart/{customerId}")
    public ResponseEntity<Order>addOrderFromCart(@PathVariable Long customerId){
        Order order=orderService.addOrderFromCart(customerId);
        return new ResponseEntity<Order>(order,HttpStatus.ACCEPTED);
    }
    @GetMapping("/findAllOrder")
    public ResponseEntity<List<Order>> getAll() {
        List<Order>allOrder= orderService.viewOrder();
        return new ResponseEntity<List<Order>>(allOrder,HttpStatus.ACCEPTED);
    }
    @GetMapping("/findOrderByUserName/{FirstName}/{LastName}/{mobileNo}")
    public ResponseEntity<ArrayList<Order>> viewByUserName(@PathVariable("FirstName") String FirstName,
                                                           @PathVariable("LastName") String LastName,
                                                           @PathVariable("mobileNo") String mobileNo) {
        ArrayList<Order> orders = orderService.findOrderByUserName(FirstName, LastName, mobileNo);
        if (!orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/findOrder")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order byId = orderService.getById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(byId);
    }
    @PutMapping("/updatePhoneNumber")
    public ResponseEntity<Order> updatePhoneNumber(@RequestBody ChangingTheOrderByTheCustomer customer){
        Order update = orderService.update(customer);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(update);
    }
    @PutMapping("/updateOrder/{Id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order, @PathVariable Long id) {
        Order updateOrder= orderService.updateOrder(order, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateOrder);
    }
    @DeleteMapping("/removeOrder/{customerId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long customerId) {
        orderService.deleteOrder(customerId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

}
