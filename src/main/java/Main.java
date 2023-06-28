import java.util.Scanner;
public class Main {
    static int people = 0;
    private static final int maxPeople = 6;
    static String errText = "Ошибка. Введите количество гостей от 2 до " + maxPeople + ".";
    public static void main(String[] args) {
        while (true) {
            if (getPeople()) {
                break;
            }
        }
        Calc calc = new Calc();
        calc.parseBill();
    }

    public static boolean getPeople() {
        System.out.println("На сколько человек делим счет?");
        Scanner scanner = new Scanner(System.in);
        int peopleIn = scanner.nextInt();
        if ((peopleIn<=1) || (peopleIn>maxPeople)) {
            System.out.println(errText);
            return false;
        } else {
            people = peopleIn;
            return true;
        }
    }
}