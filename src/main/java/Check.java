import java.util.Scanner;
// Класс чека
public class Check {
    // позиции в чеке
    private String[] items = {};
    // общаяя сумма чека
    private double summa = 0.0;
    // количество гостей
    private int guestsNumber = 0;
    // статусы добавления позиции в чек
    private static final int addStatusStop = 0;// закончили добавлять
    private static final int addStatusSuccess = 1;// успешно добавили

    // добавление позиции в чек
    private int addItem(String item)
    {
        String[] array = new String[this.items.length+1];
        System.arraycopy(this.items, 0, array, 0, this.items.length);
        array[this.items.length] = item;
        this.items = array;
        // успешно добавили
        return addStatusSuccess;
    }

    // запуск добавления позиций в чек
    public void addItems() {
        int i;
        // запрашиваем название, пока не будет сигнала остановиться
        while ((i = getItemName())!=addStatusStop) {
            // успешно добавили название
            if (i == addStatusSuccess) {
                // запрашиваем цену
                getPrice();
            }
        }
    }

    // запрос наименования позиции
    int getItemName() {
        String stopWord = "завершить";
        System.out.println(this.items.length>0 ? "Желаете добавить еще? Введите название товара или слово "+stopWord : "Введите название товара");
        Scanner scanner = new Scanner(System.in);
        String item = scanner.next();
        // сигналом к остановке служит совпадение названия со стоп словом
        // в противном случае - добавляем позицию в чек
        return item.equalsIgnoreCase(stopWord) ?  addStatusStop : this.addItem(item);
    }

    // запрос цены позиции
    void getPrice() {
        boolean success=false;
        String input;
        Scanner scanner = new Scanner(System.in);
        while (!success) {
            System.out.println("Введите стоимость товара "+ this.items[this.items.length-1] +" (руб,коп):");
            // ввод строки, разрешим писать копейки через запятую
            input = scanner.next().replace(",", ".");
            // проверка на число
            try {
                // число с плавающей точкой из строки
                double d = Float.parseFloat(input);
                if (d>0) {
                    // округлением превратим в число с двумя знаками после точки
                    this.summa += Math.ceil(d * 100) / 100;
                    // получилось - можно выходить из цикла
                    success = true;
                    System.out.println("Добавлено.");
                }
            }
            catch (NumberFormatException e) {
                // проверка на число не прошла, успеха нет - цикл продолжается
            }
        }
    }

    // запросим количество гостей
    public void setGuests() {
        String input;
        Scanner scanner = new Scanner(System.in);
        while (this.guestsNumber ==0) {
            int maxPeople = 10;
            System.out.println("На сколько человек делим счет (2-"+ maxPeople +")?");
            try {
                input = scanner.next();
                int peopleInt = Integer.parseInt(input);
                if ((peopleInt>=1) && (peopleInt<= maxPeople)) {
                    this.guestsNumber = peopleInt;
                }
            }
            catch (NumberFormatException e) {
                // количество гостей все еще 0 - запрос повторится
            }
        }
    }

    // выведем результат
    void showResult() {
        System.out.println("Добавленные товары:");
        // выведем каждую добавленную позицию
        for (String item : this.items) {
            System.out.println(item);
        }
        System.out.println("--------------------");
        // сколько должен заплатить каждый гость
        double eachSumma = this.summa/this.guestsNumber;
        System.out.println("С каждого гостя: " + String.format("%.2f", eachSumma) + " " + Helper.getWordForm(eachSumma, "рубль", "рубля", "рублей"));
    }
}
