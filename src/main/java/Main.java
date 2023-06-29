public class Main {
    public static void main(String[] args) {
        Check check = new Check();
        // количество гостей
        check.setGuests();
        // позиции в чеке
        check.addItems();
        // результат
        check.showResult();
    }

}