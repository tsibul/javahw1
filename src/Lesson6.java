import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;


public class Lesson6 {


    public static void main(String[] args) {
        /*
        1. Продумайте структуру класса Кот. Какие поля и методы будут актуальны для приложения, которое является
        а) информационной системой ветеринарной клиники
        б) архивом выставки котов
        в) информационной системой Театра кошек
        */

        /*
        Integer[] arr = {1, 3, 7, 2, 6, 4, 5, 3, 6, 3};
        HashSet<Integer> set1 = new HashSet<>(Arrays.asList(arr));
        Iterator<Integer> iterator = set1.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        //System.out.println(set1);
        LinkedHashSet<Integer> set2 = new LinkedHashSet<>(Arrays.asList(arr));
        Iterator<Integer> iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            System.out.print(iterator2.next() + " ");
        }
        System.out.println();
        TreeSet<Integer> set3 = new TreeSet<>(Arrays.asList(arr));
        Iterator<Integer> iterator3 = set3.iterator();
        while (iterator3.hasNext()) {
            System.out.print(iterator3.next() + " ");
        }

         */

        /*
        1. Напишите метод, который заполнит массив из 1000 элементов случайными
        цифрами от 0 до 24.
        Integer [] rList = randomArr(1000, 25);
        2. Создайте метод, в который передайте заполненный выше массив и с
        помощью Set вычислите процент уникальных значений в данном массиве и
        верните его в виде числа с плавающей запятой.
        Set<Integer> rset = unicNumber(rList);
        Integer len = rset.size();
        Float per = len.floatValue()/1000*100;
        System.out.println(per + "%");
        */
        LinkedList<Cat> lCats = new LinkedList<>();
        HashSet<Cat> cats = new HashSet<>();
        Cat cat1 = new Cat();
        lCats.add(cat1);
        cats.add(cat1);
        Cat cat2 = new Cat();
        lCats.add(cat2);
        cats.add(cat2);
        Cat cat3 = new Cat();
        lCats.add(cat3);
        cats.add(cat3);
        cats.add(new Cat("vaska", "green", "dvorteryer", LocalDate.now()));
        lCats.add(new Cat("vaska", "green", "dvorteryer", LocalDate.now()));
        for (Cat c: cats) {
            c.printCats();
        }
        System.out.println("==");
        for (Cat c: lCats) {
            c.printCats();
        }
        System.out.println();
    }

    public static Integer[] randomArr (int len, int range ) {
        Random random = new Random();
        Integer [] arr = new Integer[len];
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(range);
        }
        return arr;
    }

    public  static Set<Integer> unicNumber (Integer[] lList ){
        HashSet<Integer> unicSet = new HashSet<>(Arrays.asList(lList));
        return unicSet;
    }



}
