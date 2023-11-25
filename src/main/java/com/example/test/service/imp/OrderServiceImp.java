package com.example.test.service.imp;


import com.example.test.dto.ChangingTheOrderByTheCustomer;
import com.example.test.entity.Cart;
import com.example.test.entity.Customer;
import com.example.test.entity.Order;
import com.example.test.entity.Product;
import com.example.test.exeption.CustomerNotFoundException;
import com.example.test.exeption.OrderNotFound;
import com.example.test.repository.CustomerRepository;
import com.example.test.repository.OrderRepository;
import com.example.test.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CartServiceImpl cartServiceImpl;


    @Override
    public Order createOrder(Order order) {
        Optional<Order> order1= orderRepository.findById(order.getId());
        if (order1.isPresent()){
            throw new OrderNotFound("order not found");
        }else {
            Order saveOrder = new Order();
            saveOrder.setAddress(order.getAddress());
            saveOrder.setProducts(order.getProducts());
            saveOrder.setTotalPrice(order.getTotalPrice());
            return orderRepository.save(saveOrder);
        }
    }

    public Order addOrderFromCart(Long customerId) {
        Order order1=new Order ();
        Cart cart=new Cart();
        List<Cart> allCartDetails=cartServiceImpl.ViewAllCart();
        List<Product>getProducts=new ArrayList<>();

        for(Cart newCart:allCartDetails) {
            if(Objects.equals(newCart.getCustomer().getId(), customerId)) {
                getProducts.add(newCart.getProduct() );
                allCartDetails.remove(newCart.getProduct());
            }
        }
        System.out.println(getProducts);
        order1.setLocalDateTime(LocalDateTime.now());
        order1.setOrderStatus("ORDER PLACED");

        Optional<Customer> customer =customerRepository.findById(customerId);

        if(customer.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found with this Id" +customerId);
        }
        order1.setCustomer(customer.get());
        order1.setProducts(getProducts);

        //		cartService.deleteAllCart();
        return orderRepository.save(order1);
    }


    public List<Order> viewOrder() {
        return orderRepository.findAll();
    }

    public Order getById(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else
            throw new OrderNotFound("order not found");
    }

    public ArrayList<Order> findOrderByUserName(String FirstName, String lastName, String mobile) {
        List<Order> all = orderRepository.findAll();
        ArrayList<Order> orders = new ArrayList<>();
        for (Order order : all) {
            if (order.getCustomer().getFirstName().equals(FirstName) &&
                    order.getCustomer().getLastName().equals(lastName) &&
                    order.getCustomer().getPhoneNumber().equals(mobile)) ;
        }
        if (orders.isEmpty()) {
            throw new OrderNotFound("order not found");
        } else {
            return orders;
        }
    }

    public Order update(ChangingTheOrderByTheCustomer customer) {
//        Order order = orderRepository.findById(changeOrder.getId())
//                .orElseThrow(NotFoundException::new);
//        Customer customer = customerRepository.findById(order.getId())
//                .orElseThrow(NotFoundException::new);
//        order.setProducts(changeOrder.getProductName());
//        order.setCustomer(customer);
//        orderRepository.save(order);
        Order order = orderRepository.findById(customer.getId())
                .orElseThrow(() -> new OrderNotFound("order not found"));
        order.setAddress(customer.getAddress());
        order.setProducts(customer.getProductName());
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order, Long id) {
        ArrayList<Order> orders = (ArrayList<Order>) orderRepository.findAll();
        Order findOrder = new Order();
        int count = 0;
        for (Order find : orders) {
            if (Objects.equals(find.getCustomer().getId(), id)) {
                findOrder = find;
                count++;
            } else {
                throw new OrderNotFound("Order not found");
              }
            }
        return orderRepository.save(findOrder);
        }



    public void deleteOrder(Long customerId) {
        Order order = orderRepository.findById(customerId)
                        .orElseThrow(() -> new OrderNotFound("the order was not delete"));

        orderRepository.delete(order);
    }

}
