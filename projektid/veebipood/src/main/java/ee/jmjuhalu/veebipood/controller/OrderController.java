package ee.jmjuhalu.veebipood.controller;

import ee.jmjuhalu.veebipood.dto.OrderRowDto;
import ee.jmjuhalu.veebipood.entity.Order;
import ee.jmjuhalu.veebipood.entity.OrderRow;
import ee.jmjuhalu.veebipood.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

//localhost: 8080
//application.properties server.port=8090

public class OrderController {
    @Autowired

    private ee.jmjuhalu.veebipood.repository.OrderRepository orderRepository;
    private OrderService orderService;


    @GetMapping("orders")
    public List<Order> getOrders(){

        return orderRepository.findAll();
    }

    @DeleteMapping("orderss/{id}")
    public List<Order> deleteOrders(@PathVariable Long id){
        orderRepository.deleteById(id); //kustutan
        return orderRepository.findAll(); //uuenenud seis
    }

    //person --> atentimise tokenist. parcelmachine -->omnivast
    //localhost:8080/orders?personId=1
    @PostMapping("orders")
    public Order addOrders(@RequestParam Long personId,
                                 @RequestParam(required = false) String parcelMachine,
                                 @RequestBody List<OrderRowDto> orderRows) {
        return orderService.saveOrder(personId, parcelMachine, orderRows); //siin salvesatab
        //return orderRepository.findAll(); //siin on uuenenud seis
    }
}
