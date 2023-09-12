package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.model.Product;
import br.ada.customer.crud.usecases.IOrderItemUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemUseCaseImpl implements IOrderItemUseCase {

    private OrderRepository repository;

    public OrderItemUseCaseImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderItem addItem(Order order, Product product, BigDecimal price, Integer amount) {
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new RuntimeException("Pedido não pode ser alterado.");
        }
        OrderItem addedItem = new OrderItem();
        List<OrderItem> orderItemList = order.getItems();
        addedItem.setProduct(product);
        addedItem.setSaleValue(price);
        addedItem.setAmount(amount);
        orderItemList.add(addedItem);
        order.setItems(orderItemList);

        repository.save(order);
        return addedItem;
    }

    @Override
    public OrderItem changeAmount(Order order, Product product, Integer amount) {
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new RuntimeException("Pedido não pode ser alterado.");
        }

        OrderItem ItemToChangeAmount = null;
        for (OrderItem item : order.getItems()) {
            if (item.getProduct().equals(product)) {
                ItemToChangeAmount = item;
                break;
            }
        }

        ItemToChangeAmount.setAmount(amount);

        repository.save(order);

        return ItemToChangeAmount;
    }



    @Override
    public void removeItem(Order order, Product product) {
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new RuntimeException("Pedido não pode ser alterado.");
        }

        OrderItem ItemToRemove = null;
        for (OrderItem item : order.getItems()) {
            if (item.getProduct().equals(product)) {
                ItemToRemove = item;
                break;
            }
        }

        order.getItems().remove(ItemToRemove);

        repository.save(order);

    }
}

