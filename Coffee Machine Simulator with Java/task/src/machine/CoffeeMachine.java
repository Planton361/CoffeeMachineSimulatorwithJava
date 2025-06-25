package machine;

import java.util.Scanner;

public class CoffeeMachine {
    static Coffee espresso;
    static Coffee latte;
    static Coffee cappuccino;

    static Scanner scanner = new Scanner(System.in);
    static int waterStorage = 400;
    static int milkStorage = 540;
    static int coffeeBeansStorage = 120;
    static int cupsStorage = 9;
    static int cashStorage = 550;
    static boolean isMachineOn = true;
    static int coffeesMade = 0;

    public static void main(String[] args) {
        createCoffeeTypes();
        while (isMachineOn) {
            getAction();
        }
    }

    private static void createCoffeeTypes() {
        espresso = new Coffee(250, 0, 16, 4);
        latte = new Coffee(350, 75, 20, 7);
        cappuccino = new Coffee(200, 100, 12, 6);
    }

    private static void getAction() {
        System.out.println("Write action (buy, fill, take, clean, remaining, exit): ");
        String action = scanner.nextLine();
        if (coffeesMade < 10 || action.equals("clean")) {
            switch (action) {
                case "buy":
                    buyCoffee();
                    break;
                case "fill":
                    fillMachine();
                    break;
                case "take":
                    getMoneyTaken();
                    break;
                case "remaining":
                    printMachineStats();
                    break;
                case "clean":
                    cleanMachine();
                    break;
                case "exit":
                    isMachineOn = false;
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        } else {
            System.out.println("I need cleaning!");
        }
    }

    private static void cleanMachine() {
        coffeesMade = 0;
        System.out.println("I have been cleaned!");
    }

    private static void getMoneyTaken() {
        System.out.printf("I gave you $%d\n", cashStorage);
        cashStorage = 0;
    }

    private static void fillMachine() {
        System.out.println("Write how many ml of water you want to add:");
        waterStorage += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        milkStorage += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeBeansStorage += scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        cupsStorage += scanner.nextInt();

    }

    private static void buyCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String action = scanner.nextLine();
        switch (action) {
            case "1":
                checkIsEnough(espresso);
                break;
            case "2":
                checkIsEnough(latte);
                break;
            case "3":
                checkIsEnough(cappuccino);
                break;
            case "back":
                break;
            default:
                System.out.println("Wrong input");
        }
    }

    private static void checkIsEnough(Coffee coffee) {
        if (waterStorage < coffee.getAmountWater()) {
            System.out.println("Sorry, not enough water!");
        } else if (milkStorage < coffee.getAmountMilk()) {
            System.out.println("Sorry, not enough milk!");
        } else if (coffeeBeansStorage < coffee.getAmountCoffeeBeans()) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (cupsStorage < 1) {
            System.out.println("Sorry, not enough cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            waterStorage-=coffee.getAmountWater();
            milkStorage-=coffee.getAmountMilk();
            coffeeBeansStorage-=coffee.getAmountCoffeeBeans();
            cupsStorage--;
            cashStorage+=coffee.getCost();
            coffeesMade++;
        }
    }

    private static void printMachineStats() {
        System.out.printf("""
                The coffee machine has:
                %s ml of water
                %s ml of milk
                %s g of coffee beans
                %s disposable cups
                $%s of money
                
                """, waterStorage, milkStorage, coffeeBeansStorage, cupsStorage, cashStorage);
    }
}