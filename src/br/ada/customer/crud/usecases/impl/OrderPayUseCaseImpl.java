package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.IOrderPayUseCase;

import br.ada.customer.crud.usecases.IPayNotifierUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

public class OrderPayUseCaseImpl implements IOrderPayUseCase {

    private OrderRepository repository;
    private IPayNotifierUseCase notifierUseCase;


    public OrderPayUseCaseImpl(OrderRepository repository, IPayNotifierUseCase notifierUseCase) {
        this.repository = repository;
        this.notifierUseCase = notifierUseCase;
    }

    @Override
    public void pay(Order order) {
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
            throw new RuntimeException("Pedido em aberto. Aguardar fechamento do pedido para realizar o pagamento");
        }

        order.setStatus(OrderStatus.PAID);
        repository.save(order);
        notifierUseCase.notify(order);

    }
}
