import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        PaymentType[] paymentTypes = PaymentType.values();
        while (true) {
            System.out.println("Elige un medio de pago:");
            System.out.println("0: Salir");
            for (int i = 0; i < paymentTypes.length; i++) {
                System.out.println((i + 1) + ": " + paymentTypes[i]);
            }

            System.out.println((paymentTypes.length + 1) + ": Elegir aleatoriamente 5 veces");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Inténtalo de nuevo.");
                scanner.nextLine();
                continue;
            }
            
            if (choice == 0) {
                break;
            } else if (choice == paymentTypes.length + 1) {
                for (int i = 0; i <5 ; i++){
                    pagarRandom(paymentTypes);
                }

            } else {
                pagar(choice,paymentTypes);
            }
        }

    }
    public static void pagarRandom( PaymentType[] paymentTypes){
        Random random = new Random();
        int randomChoice = random.nextInt(paymentTypes.length);
        pagar(randomChoice, paymentTypes);
    }
    public static void pagar(int i, PaymentType[] paymentTypes ){
        ConcreteFactory factory = new ConcreteFactory();
        try{
            IPay payment = factory.factoryMethod(paymentTypes[i - 1]);
            payment.pay();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Número por fuera de las opciones disponibles. Inténtalo de nuevo.");
        }
    }
}