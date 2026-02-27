package ee.jmjuhalu.veebipood.service;

import ee.jmjuhalu.veebipood.dto.OrderRowDto;
import ee.jmjuhalu.veebipood.entity.Order;
import ee.jmjuhalu.veebipood.entity.OrderRow;
import ee.jmjuhalu.veebipood.entity.Person;
import ee.jmjuhalu.veebipood.entity.Product;
import ee.jmjuhalu.veebipood.repository.OrderRepository;
import ee.jmjuhalu.veebipood.repository.PersonRepository;
import ee.jmjuhalu.veebipood.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
public class OrderService {

    //@Autowired --> dependency injection
    //@RequiredArgConstruction --> Dependency injection

    //tagataustal tõmmatakse sisse tema mälukohaga et ma saaksin ligipääsu sellele mis ,,siin,, sees toimub

    private OrderRepository orderRepository;
    private PersonRepository personRepository;
    private ProductRepository productRepository;

    public Order saveOrder(Long personId,
                           String parcelMachine,
                           List<OrderRowDto> orderRows){
        Order order = new Order();
        order.setCreated(new Date());
        order.setParcelMachine(parcelMachine);
        //order.setOrderRows(orderRows);
        Person person = personRepository.findById(personId).orElseThrow();//kui isikut ei leia --> exception
        order.setPerson(person);
        order.setTotal(calculateOrderTotal(orderRows, order));
        return orderRepository.save(order);
    }

    private double calculateOrderTotal(List<OrderRowDto> orderRows, Order order) {
        double total = 0;
        List<OrderRow> orderRowsInOrder = new ArrayList<>();
        for (OrderRowDto orderRow : orderRows) {
            Product product = productRepository.findById(orderRow.productId()).orElseThrow();
            total += product.getPrice() * orderRow.quantity();

            OrderRow orderRowInOrder = new OrderRow();
            orderRowInOrder.setProduct(product);
            orderRowInOrder.setQuantity(orderRow.quantity());
            orderRowsInOrder.add(orderRowInOrder);

        }
        order.setOrderRows(orderRowsInOrder);
        return total;
    }

}
