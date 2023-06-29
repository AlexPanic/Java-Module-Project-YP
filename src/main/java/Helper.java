// by AlexPanic
// Вспомогательные методы
public class Helper {
    // подставить нужную словоформу из типовых трех в зависимости от числа
    // formLike1Moneta - для чисел типа 1 монета, 21 монета, 1001 монета
    // formLike2Moneti - для чисел типа 2 монеты, 94 монеты, 1003 монеты
    // formLike5Monet - для чисел типа 5 монет, 18 монет, 9999 монет
    public static String getWordForm(double sum, String formLike1Moneta,  String formLike2Moneti,  String formLike5Monet) {
        // определяем по остатку от деления на 100
        int N = Math.abs((int) Math.floor(sum)) % 100;
        // и остатку от деления на 10
        int N1 = N % 10;
        // sum от 11 до 19 всегда монет
        // если последняя цифра от 2 до 4 то монеты
        // иначе если последняя цифра 1 то монета в противном случае - монет
        return N > 10 && N < 20 ? formLike5Monet : (N1 > 1 && N1 < 5 ? formLike2Moneti : (N1 == 1 ? formLike1Moneta : formLike5Monet));
    }
}
