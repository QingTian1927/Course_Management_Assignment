package coursemanager.util;
import java.util.Scanner;

public final class Validation {
    private Validation() {}

    public static boolean isNonNegative(int x) {
        return x >= 0;
    }

    public static boolean isNonNegative(double x) {
        return x >= 0;
    }

    public static boolean isBooleanInt(int x) {
        return x == 0 || x == 1;
    }

    public static int getInteger(int min, int max){
        Scanner sc = new Scanner(System.in);
        while (true){
            try {
                int num = sc.nextInt();
                if (num > max || num < min){
                    throw new NumberFormatException(); 
                }
                return num;
            }catch (NumberFormatException e){
                System.out.println("Input invalid. Please input integer");
            }
        }
    }


    public static String getString(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            if(!s.isEmpty()){
                return s;
            }
            System.out.println("Input invalid. Please input string");
        }
    }

    public static double getDouble(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                double num = sc.nextDouble();
                return num;
            }catch (NumberFormatException e){
                System.out.println("Input invalid. Please input double");
            }
        }
    }

    public static int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static double parseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
