import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class HW4 {

    public static void main(String[] args) {
        //Task1
        /*
        Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который вернет “перевернутый” список.
        */
        System.out.println("Task1");
        LinkedList<String> lList = randomLinList(11, 8);
        System.out.println(lList.toString());
        palindromList(lList);
        System.out.println(lList.toString());
        System.out.println();
        //Task2
        /*
        Реализуйте очередь с помощью LinkedList со следующими методами:
        enqueue() - помещает элемент в конец очереди,
        dequeue() - возвращает первый элемент из очереди и удаляет его,
        first() - возвращает первый элемент из очереди, не удаляя.
        */
        System.out.println("Task2");
        enqueue(lList, "enqueue");
        System.out.println(lList.toString());
        String removed = dequeue(lList);
        System.out.println(removed);
        System.out.println(lList.toString());
        String element = first(lList);
        System.out.println(element);
        System.out.println();
        //Task4
        /*
        * В калькулятор добавьте возможность отменить последнюю операцию.
        */
        System.out.println("Task2");

        System.out.println();

    }

    public static String first (LinkedList<String> lList){
        return lList.getFirst();
    }

    public static void enqueue (LinkedList<String> lList, String object){
        lList.add(object);
    }

    public static String dequeue (LinkedList<String> lList){
        return lList.remove();
    }


    public static LinkedList<String> randomLinList (int len, int range ) {
        Random random = new Random();
        LinkedList<String> arr = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            String element = "";
            int lenRange = random.nextInt(range + 1) + 1;
            for(int j = 1; j < lenRange; j++) {
                boolean isSpace = true;
                while (isSpace) {
                    String tmpStr = Character.toString((char) random.nextInt(30) + 97);
                    if (!tmpStr.equals("") && !tmpStr.equals(" ")) {
                        element = element + tmpStr;
                        isSpace = false;
                    }
                }
            }
            arr.add(element);
        }
        return arr;
    }

    public static void palindromList (LinkedList<String> lList){
        int len = lList.size();
        int range = len / 2;
        for(int i = 0; i < range; i++){
            String tmp = lList.get(i);
            lList.set(i, lList.get(len - i - 1));
            lList.set(len - i - 1, tmp);
        }


    }

}