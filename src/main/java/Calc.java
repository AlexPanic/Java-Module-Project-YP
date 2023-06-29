import java.util.Scanner;
public class Calc {
    private String[] items = {};
    private double summa = 0.0;
    private int people = 0;

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

    String getWordForm(double sum, String formLike1Moneta,  String formLike2Moneti,  String formLike5Monet) {
        int N = Math.abs((int) Math.floor(sum)) % 100;
        int N1 = N % 10;
        return N > 10 && N < 20 ? formLike5Monet : (N1 > 1 && N1 < 5 ? formLike2Moneti : (N1 == 1 ? formLike1Moneta : formLike5Monet));
    }

    void showResult() {
        System.out.println("Добавленные товары:");
        for (String item : this.items) {
            System.out.println(" " + item);
        }
        System.out.println("--------------------");
        double eachSumma = this.summa/this.people;
        System.out.println("С каждого гостя: " + String.format("%.2f", eachSumma) + " " + this.getWordForm(eachSumma, "рубль", "рубля", "рублей"));
    }

    public void parseBill() {
        int i;
        while ((i = getName())!=0) {
            if (i==1) {
                getPrice();
            }
        }
        showResult();
    }

    public void getPeople() {
        String input;
        Scanner scanner = new Scanner(System.in);
        while (this.people==0) {
            int maxPeople = 6;
            System.out.println("На сколько человек делим счет (2-"+ maxPeople +")?");
            try {
                input = scanner.next();
                int peopleInt = Integer.parseInt(input);
                if ((peopleInt>=1) && (peopleInt<= maxPeople)) {
                    this.people = peopleInt;
                }
            }
            catch (NumberFormatException e) {
                //...
            }
        }
    }

}
