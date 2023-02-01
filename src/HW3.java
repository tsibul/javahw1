import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class HW3 {

    public static void main (String[] args)  {


        //Task 1
            /*
            Реализовать алгоритм сортировки слиянием
             */
        System.out.println("Task 1");
        int len = 20;
        int range = 100;
        ArrayList<Integer> array = randomArrList(len, range);
        System.out.println(array.toString());
        mergeSort(array);
        System.out.println(array.toString());
        System.out.println();

        //Task 2
            /*
            Пусть дан произвольный список целых чисел, удалить из него четные числа
             */
        System.out.println("Task 2");
        len = 30;
        range = 100;
        LinkedList<Integer> liArr = randomLinList(len, range);
        System.out.println(liArr.toString());
        removeEven(liArr);
        System.out.println(liArr.toString());
        System.out.println();

        //Task2
            /*
            Задан целочисленный список ArrayList. Найти минимальное,
            максимальное и среднее ариф из этого списка.
             */
        System.out.println("Task 3");
        ArrayList<Integer> array2 = randomArrList(len, range);
        System.out.println(array2.toString());
        task3(array2);
    }

    public static void mergeArr (ArrayList<Integer> arr, Integer left,Integer mid,Integer right){
        int it1 = 0;
        int it2 = 0;
        Integer [] result = new Integer[right - left];
        while (left + it1 < mid && mid + it2 < right){
            if (arr.get(left + it1) < arr.get(mid + it2)){
                result[it1 + it2] = arr.get(left + it1);
                it1 ++;
            }
            else{
                result[it1 + it2] = arr.get(mid + it2);
                it2 ++;
            }
        }
        while (left + it1 < mid){
            result[it1 + it2] = arr.get(left + it1);
            it1 ++;
        }
        while (mid + it2 < right){
            result[it1 + it2] = arr.get(mid + it2);
            it2 ++;
        }
        for(int i = 0; i < it1 + it2; i++){
            arr.set(left + i, result[i]);
        }
    }

    public static void mergeSort(ArrayList<Integer> arr){
        int len = arr.size();
        for (int i = 1; i < len; i *= 2){
            for (int j = 0; j < len - i; j += i * 2){
                int right = j + i * 2;
                if(right > len){
                    right = len;
                }
                mergeArr(arr, j, i + j, right);
            }
        }
    }

    public static void task3 (ArrayList<Integer> arr){
        Integer max = arr.get(0);
        Integer min = arr.get(0);
        Float ave = (float) 0;
        for (Integer i: arr) {
            if(i > max){
                max = i;
            }
            else if(i < min){
                min = i;
            }
            ave += i.floatValue();
        }
        ave /= arr.size();
        System.out.println("max: " + max.toString());
        System.out.println("min: " + min.toString());
        System.out.println("average: " + ave.toString());
    }

    public  static void removeEven(LinkedList<Integer> arr){
        int i = 0;
        while(i < arr.size()){
            if(arr.get(i) % 2 == 0){
                arr.remove(i);
            }
            else {
                i++;
            }
        }
    }

    public static ArrayList<Integer> randomArrList (int len, int range ) {
        Random random = new Random();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < len; i++) {
            arr.add(random.nextInt(range));
        }
        return arr;
    }

    public static LinkedList<Integer> randomLinList (int len, int range ) {
        Random random = new Random();
        LinkedList<Integer> arr = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            arr.add(random.nextInt(range));
        }
        return arr;
    }


}
