package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.INotifierOrderUseCase;
import br.ada.customer.crud.usecases.IOrderPayUseCase;

import br.ada.customer.crud.usecases.IPayNotifierUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

public class OrderPayUseCaseImpl implements IOrderPayUseCase {

    private OrderRepository orderRepository;
    private INotifierOrderUseCase notifierUseCase;


    public OrderPayUseCaseImpl(OrderRepository orderRepository, INotifierOrderUseCase notifierUseCase) {
        this.orderRepository = orderRepository;
        this.notifierUseCase = notifierUseCase;

    }

    @Override
    public void pay(Order order) {
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
            throw new RuntimeException("Pedido em aberto. Aguardar fechamento do pedido para realizar o pagamento");
        }

        order.setStatus(OrderStatus.PAID);
        orderRepository.update(order);
        notifierUseCase.payOrder(order);

    }
}
