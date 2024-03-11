package order.states;

import order.Order;

public class AcceptedState extends OrderState {
    public AcceptedState(Order order) {
        super(order);
    }

    @Override
    public void showInfo() {
        System.out.println("\nЗаказ: Id " + order.getId());
        System.out.println("Статус готовности заказа: (Принят)");

        super.showInfo();
    }

    @Override
    public String getReadyState() {
        return "ACCEPTED";
    }

    @Override
    public void getProcessed() {
        order.changeState(new InProcessState(order));
    }

    @Override
    public void getReady() {
    }

    @Override
    public void getAccepted() {
        order.changeState(new AcceptedState(order));
    }
}
