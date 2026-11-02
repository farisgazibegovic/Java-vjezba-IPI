public class OperatoriPrimjer {
    public static void main(String[] args) {
        int a = 17, b = 5;

        int sum = a + b;
        int diff = a - b;
        int product = a * b;
        int quotient = a / b;
        int remainder = a % b;
        boolean isGreater = a > b;
        boolean bothPositive = (a > 0) && (b > 0);

        // Predviđanje za a = 17, b = 5:
        // sum = 22, diff = 12, product = 85
        // quotient = 3   -> int / int odbacuje decimale (ne zaokružuje!)
        // remainder = 2  -> 17 = 3 * 5 + 2, ostatak je 2

        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + diff);
        System.out.println("Product: " + product);
        System.out.println("Quotient: " + quotient);
        System.out.println("Remainder: " + remainder);
        System.out.println("Pravo dijeljenje: " + (double) a / b);   // 3.4
        System.out.println("Is a greater than b? " + isGreater);
        System.out.println("Are both positive? " + bothPositive);

        // Prioritet operatora
        System.out.println("a + b * 2 = " + (a + b * 2));        // 27 -> množenje ide prije sabiranja
        System.out.println("(a + b) * 2 = " + ((a + b) * 2));    // 44 -> zagrade idu prve
        System.out.println("a - b - 2 = " + (a - b - 2));        // 10 -> s lijeva na desno
        System.out.println("a - (b - 2) = " + (a - (b - 2)));    // 14

        // Zamka sa stringovima
        System.out.println("Bez zagrada: " + a + b);    // 175 -> spoji kao tekst "17" + "5"
        System.out.println("Sa zagradama: " + (a + b)); // 22  -> prvo sabere
    }
}