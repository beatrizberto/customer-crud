package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.INotifierOrderUseCase;
import br.ada.customer.crud.usecases.IOrderPlaceUseCase;
import br.ada.customer.crud.usecases.IPlaceNotifierUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.math.BigDecimal;

public class OrderPlaceUseCaseImpl implements IOrderPlaceUseCase {

    private OrderRepository orderRepository;
    private INotifierOrderUseCase notifierUseCase;

    public OrderPlaceUseCaseImpl(OrderRepository orderRepository, INotifierOrderUseCase notifierUseCase) {
        this.orderRepository = orderRepository;
        this.notifierUseCase = notifierUseCase;
    }



    @Override
    public void placeOrder(Order order) {

        BigDecimal sum = BigDecimal.ZERO;

        if (order.getStatus() != OrderStatus.OPEN) {
            throw new RuntimeException("Abra novo pedido");
        }

        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new RuntimeException("Não tem item.");
        }

        for (OrderItem item : order.getItems()) {
            sum = sum.add(item.getSaleValue());
        }

        if (sum.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("Soma dos produtos igual ou menor que 0");
        }


        order.setStatus(OrderStatus.PENDING_PAYMENT);
        orderRepository.update(order);
        notifierUseCase.pendingPayment(order);



    }

}

