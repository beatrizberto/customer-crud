package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.INotifierOrderUseCase;
import br.ada.customer.crud.usecases.IPayNotifierUseCase;

public class OrderEmailNotifierImpl implements INotifierOrderUseCase {

    private SendEmail sendEmail;

    public OrderEmailNotifierImpl(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public void pendingPayment(Order order) {
        sendEmail.send("comunicado@ada.com.br", order.getCustomer().getEmail(),"Aguardando pagamento.");

    }

    @Override
    public void payOrder(Order order) {
        sendEmail.send("comunicado@ada.com.br", order.getCustomer().getEmail(),"O pagamento do seu pedido foi realizado");

    }

    @Override
    public void shipping(Order order) {
        sendEmail.send("comunicado@ada.com.br", order.getCustomer().getEmail(), "Pedido enviado.");

    }
}


