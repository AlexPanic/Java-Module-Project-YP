import java.util.Scanner;
public class Calc {
    private String[] items = {};
    private double summa = 0.0;

    void addItem(String item)
    {
        String[] array = new String[this.items.length+1];
        System.arraycopy(this.items, 0, array, 0, this.items.length);
        array[this.items.length] = item;
        this.items = array;
    }
    boolean getName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название блюда (или слово завершить):");
        String item = scanner.next();
        String stopWord = "завершить";
        if (item.equalsIgnoreCase(stopWord)) {
            return false;
        }
        this.addItem(item);
        return true;
    }
    void getPrice() {
        // для выхода из бесконечного цикла
        boolean success=false;
        // сырой ввод
        String input;
        Scanner scanner = new Scanner(System.in);
        // пока не решим что все получилось
        while (!success) {
            System.out.println("Введите стоимость блюда (руб,коп):");
            // ввод строки, разрешим писать копейки через запятую
            input = scanner.next().replace(",", ".");
            // проверка на число
            try {
                // число с плавающей точкой из строки
                double d = Float.parseFloat(input);
                // округлением превратим в число с двумя знаками после точки
                this.summa += Math.ceil(d*100)/100;
                // получилось - можно выходить из цикла
                success = true;
            }
            // проверка на число не прошла - цикл продолжается
            catch (NumberFormatException e) {
                System.out.println("Пожалуйста, только цифры (руб,коп)");
            }
        }
    }
    void showResult() {
        System.out.println("Summa = " + this.summa);
    }

    Calc() {
        while (getName()) {
            getPrice();
        }
        showResult();
    }
}
