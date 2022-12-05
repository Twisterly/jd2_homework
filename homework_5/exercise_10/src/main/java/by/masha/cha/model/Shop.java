package by.masha.cha.model;

public class Shop {

    private Basket basket;

    public Shop(Basket basket) {
        this.basket = basket;
    }

    public String checkBasket() {
        return "Basket total price: " + basket.getSum();
    }
}