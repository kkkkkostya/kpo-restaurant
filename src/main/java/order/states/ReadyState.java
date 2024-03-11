package order.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import order.Order;

public class ReadyState extends OrderState {
    public ReadyState(Order order) {
        super(order);
    }

    @Override
    public void showInfo() {
        System.out.println("\nЗАКАЗ: Id " + order.getId());
        System.out.println("Статус готовности заказа: (Готов)");

        super.showInfo();
    }

    @Override
    @JsonProperty("readyState")
    public String getReadyState() {
        return "READY";
    }

    @Override
    public void getProcessed() {
    }

    @Override
    public void getReady() {
    }

    @Override
    public void getAccepted() {

    }
}
