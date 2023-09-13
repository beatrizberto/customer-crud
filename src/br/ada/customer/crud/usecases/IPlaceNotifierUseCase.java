package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;

public interface IPlaceNotifierUseCase {

    void notify(Order order);
}
