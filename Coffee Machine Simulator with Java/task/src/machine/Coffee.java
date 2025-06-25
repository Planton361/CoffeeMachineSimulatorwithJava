package machine;

public class Coffee {
    private final int amountWater;
    private final int amountMilk;
    private final int amountCoffeeBeans;
    private final int cost;

    public Coffee(int amountWater, int amountMilk, int amountCoffeeBeans, int cost){
        this.amountWater = amountWater;
        this.amountMilk = amountMilk;
        this.amountCoffeeBeans = amountCoffeeBeans;
        this.cost = cost;
    }

    public int getAmountWater() {
        return amountWater;
    }

    public int getAmountMilk() {
        return amountMilk;
    }

    public int getAmountCoffeeBeans() {
        return amountCoffeeBeans;
    }
    public int getCost(){
        return cost;
    }
}
