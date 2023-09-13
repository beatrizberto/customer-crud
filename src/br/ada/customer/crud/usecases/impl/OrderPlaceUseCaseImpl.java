package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.IOrderPlaceUseCase;
import br.ada.customer.crud.usecases.IPayNotifierUseCase;
import br.ada.customer.crud.usecases.IPlaceNotifierUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

public class OrderPlaceUseCaseImpl implements IOrderPlaceUseCase {

    private OrderRepository repository;
    private IPlaceNotifierUseCase notifierUseCase;

    @Override
    public void placeOrder(Order order) {
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new RuntimeException("Abra novo pedido");
        } else if (order.getItems() !=) {
            
        }

    }
}
