package order.states;

import food.Dish;
import order.Order;

import static enums.PaymentStatus.NOTPAID;

abstract public class OrderState {
    protected Order order;

    public OrderState(Order order) {
        this.order = order;
    }

    public void showInfo() {
        System.out.print("Статус оплаты заказа: ");
        if (order.getPaymentStatus() == NOTPAID) {
            System.out.println("Не оплачен");
        } else {
            System.out.println("Оплачен");
        }

        System.out.println("Стоимость заказа: " + order.getTotalPrice() + " $");
        System.out.println();

        System.out.println("Состав заказа:");
        for (Dish dish : order.getDishes()) {
            System.out.println("Блюдо: " + dish.getName());
            System.out.println("Цена: " + dish.getPrice() + " $");
            System.out.println();
        }
    }

    abstract public String getReadyState();

    abstract public void getProcessed();

    abstract public void getReady();

    abstract public void getAccepted();
}
