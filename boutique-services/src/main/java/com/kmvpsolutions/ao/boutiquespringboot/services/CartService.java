package com.kmvpsolutions.ao.boutiquespringboot.services;

import com.kmvpsolutions.ao.boutiquespringboot.commons.dtos.OrderDTO;
import com.kmvpsolutions.ao.boutiquespringboot.entities.Cart;
import com.kmvpsolutions.ao.boutiquespringboot.entities.Customer;
import com.kmvpsolutions.ao.boutiquespringboot.entities.Order;
import com.kmvpsolutions.ao.boutiquespringboot.commons.dtos.CartDTO;
import com.kmvpsolutions.ao.boutiquespringboot.enums.CartStatus;
import com.kmvpsolutions.ao.boutiquespringboot.repository.CartRepository;
import com.kmvpsolutions.ao.boutiquespringboot.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class  CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final OrderService orderService;

    public List<CartDTO> findAll() {
        log.debug("Request to get all carts");
        return this.cartRepository.findAll()
                .stream()
                .map(CartService::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CartDTO findById(Long id) {
        log.debug("Find a cart by id {}", id);
        return this.cartRepository.findById(id).map(CartService::mapToDTO)
                .orElseThrow(() -> new IllegalStateException("No cart  found"));
    }

    public CartDTO create(Long id) {
        if (this.getActiveCart(id) == null) {
            Customer customer = this.customerRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("The customer does not exit"));

            Cart cart = new Cart(null, customer, CartStatus.NEW);
            //TODO call an API client
            OrderDTO order = null;
                    //this.orderService.create(cart);
            cart.defineOrder(order.getId());

            return mapToDTO(this.cartRepository.save(cart));

        } else {
            throw new IllegalArgumentException("There is already an active cart");
        }
    }

    public CartDTO getActiveCart(Long id) {
        List<Cart> carts = this.cartRepository.findByStatusAndCustomerId(CartStatus.NEW, id);

        if (carts != null) {
            if (carts.size() == 1)
                return mapToDTO(carts.get(0));

            if (carts.size() > 1)
                throw new IllegalArgumentException("Many active carts detected!");
        }

        return null;
    }

    public List<CartDTO> findAllActiveCarts() {
        return this.cartRepository.findByStatus(CartStatus.NEW)
                .stream()
                .map(CartService::mapToDTO)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        log.debug("Request delete cart id: {}", id);
        this.cartRepository.deleteById(id);
    }

    private static CartDTO mapToDTO(Cart cart) {
        return new CartDTO(
                cart.getId(),
                cart.getOrderId(),
                null,
                cart.getStatus().name()
        );
    }
}
