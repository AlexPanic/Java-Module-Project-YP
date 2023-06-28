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
    int getName() {
        String stopWord = "завершить";
        System.out.println(this.items.length>0 ? "Желаете добавить еще? Введите название или слово "+stopWord: "Введите название");
        Scanner scanner = new Scanner(System.in);
        String item = scanner.next();
        if (item.length()==0) {
            return -1;
        }
        if (item.equalsIgnoreCase(stopWord)) {
            return 0;
        }
        this.addItem(item);
        return 1;
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
                System.out.println("Добавлено.");
            }
            // проверка на число не прошла - цикл продолжается
            catch (NumberFormatException e) {
                System.out.println("Пожалуйста, только цифры (руб,коп)");
            }
        }
    }

    String getWordForm(double sum, String f1,  String f2,  String f5) {
        int N = Math.abs((int) Math.floor(sum)) % 100;
        int N1 = N % 10;
        return N > 10 && N < 20 ? f5 : (N1 > 1 && N1 < 5 ? f2 : (N1 == 1 ? f1 : f5));
    }

    void showResult() {
        System.out.println("Добавленные товары:");
        for (int i=0;i<this.items.length;i++) {
            System.out.println(" " + this.items[i]);
        }
        System.out.println("--------------------");
        double eachSumma = this.summa/Main.people;
        System.out.println("С каждого гостя: " + String.format("%.2f", eachSumma) + " " + this.getWordForm(eachSumma, "рубль", "рубля", "рублей"));
    }

    void parseBill() {
        int i;
        while ((i = getName())!=0) {
            if (i==1) {
                getPrice();
            }
        }
        showResult();
    }
}
