import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static Scanner iScanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("input number");
        try {
            int number = iScanner.nextInt();
            System.out.print("triangle: ");
            System.out.print(triangleN(number));
            System.out.println();
            System.out.print("factorial: ");
            System.out.print(factorM(number));
            System.out.println();
            int[] eratos = eratosfenTable(number + 1);
            int i = 0;
            System.out.print("1");
            while(eratos[i] != 0) {
                System.out.print(", ");
                System.out.print(eratos[i]);
                i++;
            }
        } catch (Exception ex){
            System.out.println("error");
        }
        System.out.println();
        System.out.println();
        System.out.println("Let's stat to calculate, dear Moles");
        calc();
        iScanner.close();

        String eqation = "2?+?5=69";
        solvEquat(eqation);
    }

    public static void calc()  {
        boolean stop = false;
        while (!stop) {
            Integer inp1 = null;
            Integer inp2 = null;
            boolean err = true;
            while(err) {
                System.out.print("input number 1 ");
                try {
                    inp1 = iScanner.nextInt();
                    err = false;
                } catch (Exception ex){
                    System.out.print("wrong input");
                    System.out.println();
                }}
            err = true;
            while(err) {
                System.out.print("input number 2 ");
                try {
                    inp2 = iScanner.nextInt();
                    err = false;
                } catch (Exception ex){
                    System.out.print("wrong input");
                    System.out.println();
                    iScanner.next();
                }}
            err = true;
            while(err) {
                System.out.print("input action or stop ");
                String act = iScanner.next();
                switch (act) {
                    case "+" -> {
                        System.out.println();
                        System.out.println(inp1 + inp2);
                        err = false;
                    }
                    case "-" -> {
                        System.out.println();
                        System.out.println(inp1 - inp2);
                        err = false;
                    }
                    case "*" -> {
                        System.out.println();
                        System.out.println(inp1 * inp2);
                        err = false;
                    }
                    case "/" -> {
                        System.out.println();
                        if (inp2 == 0) {
                            System.out.println("can't divide by null");
                            break;
                        }
                        System.out.println(inp1.floatValue() / inp2.floatValue());
                        err = false;
                    }
                    case "stop" -> {
                        System.out.println();
                        System.out.println("stop");
                        err = false;
                        stop = true;
                    }
                    default -> {
                        System.out.println("wrong input");
                        iScanner.next();
                    }
                }
            }
        }
    }

    public static int triangleN(Integer n){
        if(n >= 1){
        return ((n + 1) * n / 2);}
        else {return -1;}
    }

    public static BigInteger factorM(int n){
        BigInteger factor = BigInteger.valueOf(-1);
        if(n >= 1){
        factor = BigInteger.valueOf(1);
        for (int i = 1; i <= n ; i++) {
        factor = factor.multiply(BigInteger.valueOf(i));
        }}
        return factor;
    }

    public static int [] eratosfenTable(int n){
        boolean [] initialArray = new boolean [n+1];
        int [] eratosphen = new int[n];
        Arrays.fill(initialArray, Boolean.TRUE);
        initialArray[0] = false;
        initialArray[1] = false;
        for (int i = 2; i < n; i++) {
            if (initialArray[i]) {
                int j = 2;
                while (i * j <= n) {
                    initialArray[i * j] = false;
                    j++;
                }
            }
        }
        int j = 0;
        for (int k = 0; k < n ; k++) {
            if (initialArray[k]){
                eratosphen[j] = k;
                j++;
            }
        }
        return eratosphen;
    }

    public static void solvEquat(String equat){
        String [] arr1 = equat.split("\\+");
        String [] arr2 = arr1[1].split("=");
        String q = arr1[0];
        String w = arr2[0];
        String e = arr2[1];
        for (Integer i = 0; i < 10; i++) {
            for (Integer j = 0; j < 10; j++) {
                for (Integer k = 0; k < 10; k++) {
                    String qTemp = q.replace("?", i.toString());
                    String wTemp = w.replace("?", i.toString());
                    String eTemp = e.replace("?", i.toString());
                    if (checkEquat(qTemp, wTemp, eTemp)){
                        System.out.println(equat);
                        System.out.println(qTemp + "+" + wTemp + "=" + eTemp);
                        return;
                    }

                }

            }
        }
        System.out.println("no solution");
    }

    public static boolean checkEquat(String q, String w ,String e){
        Integer qn = Integer.parseInt(q);
        Integer wn = Integer.parseInt(w);
        Integer en = Integer.parseInt(e);
        return qn + wn == en;
    }
}


/*
1. ?????????????????? n-???? ???????????????????????? ??????????(?????????? ?????????? ???? 1 ???? n), n! (???????????????????????? ?????????? ???? 1 ???? n)
2. ?????????????? ?????? ?????????????? ?????????? ???? 1 ???? 1000
3. ?????????????????????? ?????????????? ?????????????????????? (???????????????????????? ???????????? 2 ?????????? ?? ???????????? ????????????????
(+ - / *). int a ; int b; String op (op!=???Stop???); (char != ???b???)
4.* +???????????? ?????????????????? ???????? q + w = e, q, w, e >= 0.
?????????????????? ?????????? ?????????? ???????? ???????????????? ???????????? ??????????????, ???????????????? 2? + ?5 = 69.
?????????????????? ???????????????????????? ?????????????????? ???? ?????????????? ??????????????????.
???????????????????? ???????? ???? ???????? ?????????????? ?????? ????????????????, ?????? ?????? ??????. 24 45 //24 + 45 = 69 (n=2)
 */