package order.states;

import order.Order;

public class InProcessState extends OrderState {
    public InProcessState(Order order) {
        super(order);
    }

    @Override
    public void showInfo() {
        System.out.println("\nЗАКАЗ: Id " + order.getId());
        System.out.println("Статус готовности заказа: (Готовится)");

        super.showInfo();
    }

    @Override
    public String getReadyState() {
        return "INPROCESS";
    }

    @Override
    public void getProcessed() {
    }

    @Override
    public void getReady() {
        order.changeState(new ReadyState(order));
    }

    @Override
    public void getAccepted() {
        order.changeState(new AcceptedState(order));
    }
}
