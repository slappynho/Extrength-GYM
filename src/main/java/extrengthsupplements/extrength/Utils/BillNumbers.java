package extrengthsupplements.extrength.Utils;

public class BillNumbers {

    private BillNumbers() {
    }
    public static int getRandomNumber (int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static String generateBillNumber() {
        String billNumber;
        billNumber = Integer.toString(getRandomNumber(100, 999));
        billNumber += "-" + getRandomNumber(100, 999);
        billNumber += "-" + getRandomNumber(100, 999);
        return billNumber;
    }

}
