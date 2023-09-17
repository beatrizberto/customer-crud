package br.ada.customer.crud.factory;

import br.ada.customer.crud.integration.database.MemoryDatabase;
import br.ada.customer.crud.integration.email.OrderEmailNotifierImpl;
import br.ada.customer.crud.integration.email.SendEmail;
import br.ada.customer.crud.integration.memoryrepository.OrderEntityMerge;
import br.ada.customer.crud.integration.memoryrepository.OrderMemoryRepositoryImpl;
import br.ada.customer.crud.usecases.*;
import br.ada.customer.crud.usecases.impl.*;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import javax.swing.*;

public class OrderFactory {

    public static ICreateOrderUseCase createUseCase() {
        return new CreateOrderUseCaseImpl(
                createRepository(),
                CustomerFactory.createRepository()
        );
    }

    public static IOrderItemUseCase orderItemUseCase() {
        return new OrderItemUseCaseImpl(
                createRepository()
        );
    }

    public static IOrderPlaceUseCase placeOrderUseCase() {
        return new OrderPlaceUseCaseImpl(
                createRepository(),
                OrderFactory.createNotifierOrderSendEmail()
        );
    }

    public static IOrderPayUseCase payOrderUseCase() {
        return new OrderPayUseCaseImpl(
                createRepository(),
                OrderFactory.createNotifierOrderSendEmail()
        );
    }


    public static IOrderShippingUseCase shippingUseCase() {

        return new OrderShippingUseCaseImpl(
                createRepository(),
                OrderFactory.createNotifierOrderSendEmail()

        );
    }

    public static OrderRepository createRepository() {
        return new OrderMemoryRepositoryImpl(
                MemoryDatabase.getInstance(),
                new OrderEntityMerge(MemoryDatabase.getInstance())
        );
    }

    public static INotifierOrderUseCase createNotifierOrderSendEmail(){
        return new OrderEmailNotifierImpl(new SendEmail());
    }
}
