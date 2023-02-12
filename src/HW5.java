import java.lang.reflect.Array;
import java.util.*;

public class HW5 {
    public static void main (String[] args){

        /*
        1) Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может иметь несколько телефонов.
         */
        ArrayList<String> phoneList = new ArrayList<>();
        phoneList.add("Андрей Петров, +79605559988");
        phoneList.add("Андрей Петров, +79280000988");
        phoneList.add("Андрей Иванов, +79605788888");
        phoneList.add("Иван Петров, +79605559988");
        phoneList.add("Иван Петров, +79035889988");
        phoneList.add("Сергей Андреев, +78000000048");
        phoneList.add("Петр Романов, +7905333222");
        phoneList.add("Екатерина Габсбург, +7985500000");
        phoneList.add("Иван Габсбург, +7333500000");
        phoneList.add("Сергей Иванов, +7444500000");
        phoneList.add("Екатерина Андреева, +7707500000");

        HashMap<String, ArrayList<String>> phBook = phoneBook(phoneList);
        printPhone("Сергей Андреев", phBook);// поленился добавлять консольку и так работает
        // делать ключом в телефонной книге телефон — преступление =) поэтому так реализовано

        /*
        2) Пусть дан список сотрудников:Иван Иванов и т.д.
        Написать программу, которая найдет и выведет повторяющиеся имена
        с количеством повторений. Отсортировать по убыванию популярности.
         */
        System.out.println();
        HashMap<String, Integer> nameStat = emplNames(phoneList);
        LinkedHashMap<String, Integer> nameSort = empSort(nameStat);
        for(String key: nameSort.keySet()){
            System.out.println(key + " frequency: " + nameSort.get(key));
        }

        /*
        3) Реализовать алгоритм пирамидальной сортировки (HeapSort).
        */
        System.out.println();
        int len = 20;
        ArrayList<Integer> array = randomArrList(len, 200);
        ArrayList<Integer> sortArray = new ArrayList<>();
        System.out.println(array.toString());
        for (int i = 0; i < len; i++){
            heapGo(array, len - i);
            sortArray.add(i, array.remove(0));
        }
        System.out.println(sortArray.toString());
        /*
        4) *На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.
        */

        Integer[] setUp = new Integer[8];

        HashSet<Integer> freeBoard = hashBoard(board());
        HashSet<Integer>[] boardsArchive = new HashSet [9];

        int i = 0;
        while (i < 7){
            for(int j = i; j < 64; j++){
                boardsArchive[i] = (HashSet<Integer>) freeBoard.clone();
                if(freeBoard.contains(j)) {
                    setUp[i] = j;
                    removeAttack(setUp, freeBoard, i);
                    if (freeBoard.size() < 7 - i) {
                        freeBoard.clear();
                        freeBoard = (HashSet<Integer>) boardsArchive[i].clone();
                        freeBoard.remove(j);
                    } else {
                        i++;
                    }
                }
            }
            if(i < 7){
                i--;
                freeBoard = (HashSet<Integer>) boardsArchive[i].clone();
                freeBoard.remove(setUp[i+1]);
                freeBoard.remove(setUp[i]);
                boardsArchive[i].clear();

            }
        }
        System.out.println();
        String [] fields = new String[8];
        for (int k = 0; k < 8; k++){
            switch (coord(setUp[k])[1]){
                case 0: fields[k] = "a" + (coord(setUp[k])[0] + 1); break;
                case 1: fields[k] = "b" + (coord(setUp[k])[0] + 1); break;
                case 2: fields[k] = "c" + (coord(setUp[k])[0] + 1); break;
                case 3: fields[k] = "d" + (coord(setUp[k])[0] + 1); break;
                case 4: fields[k] = "e" + (coord(setUp[k])[0] + 1); break;
                case 5: fields[k] = "f" + (coord(setUp[k])[0] + 1); break;
                case 6: fields[k] = "g" + (coord(setUp[k])[0] + 1); break;
                case 7: fields[k] = "h" + (coord(setUp[k])[0] + 1); break;
            }
            System.out.print(fields[k].toString() + ", ");
        }
    }

    public static HashMap<String, ArrayList<String>> phoneBook(ArrayList<String> arList){
        HashMap<String, ArrayList<String>> phBook = new HashMap<>();
        for(String el: arList){
            String name = el.split(", ")[0];
            String phone = el.split(", ")[1];
            if(phBook.containsKey(name)){
                phBook.get(name).add(phone);
            }
            else{
                ArrayList<String> tmpList = new ArrayList<>();
                tmpList.add(phone);
                phBook.put(name, tmpList);
            }
        }
        return phBook;
    }

    public static void printPhone(String name, HashMap<String, ArrayList<String>> hashBook){
        System.out.print(name + "\nphones:\n");
        try {
            ArrayList<String> lst = hashBook.get(name);
            for (String x : lst) {
                System.out.println(x);
            }
        }
        catch (Exception e){
            System.out.println("no this person in phoneBook");
        }
    }

    public static HashMap<String, Integer> emplNames (ArrayList<String> arList){
        HashMap<String, Integer> statNames = new HashMap<>();
        for(String el: arList){
            String fullname = el.split(", ")[0];
            String name = fullname.split(" ")[0];
            if(statNames.containsKey(name)){
                statNames.put(name, statNames.get(name) + 1);
            }
            else{
                statNames.put(name, 1);
            }
        }
        return statNames;
    }

    public static LinkedHashMap<String, Integer> empSort (HashMap<String, Integer> empList){
        ArrayList <Map.Entry<String, Integer>> lst = new ArrayList<>(empList.entrySet());
        lst.sort(Map.Entry.comparingByValue());
        Collections.reverse(lst);
        LinkedHashMap<String, Integer> nameSort = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> el: lst){
            nameSort.put(el.getKey(), el.getValue());
        }
        return nameSort;
    }

    public static void swap (ArrayList<Integer> arr, int i, int j){
        int tmp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, tmp);
    }

    public static int parentIndex(int i){
        int j;
        if(i % 2==0){
            j = i / 2 -1;
        }
        else {
            j = (i - 1) / 2;
        }
        return j;
    }

    public static void heapGo(ArrayList<Integer> arr, int n){
        for (int i = n - 1; i > 0; i--){
            if(arr.get(i) < arr.get(parentIndex(i))){
                swap(arr, i, parentIndex(i));
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


    public static Integer[] coord (Integer num){
        Integer x = num % 8;
        Integer y = num / 8;
        return new Integer[] {x, y};
    }

    public static HashMap<Integer, Integer []> board() {
        HashMap<Integer, Integer []> chessBoard = new HashMap<>();
        for (Integer i = 0; i < 64; i++) {
            chessBoard.put(i, coord(i));
        }
        return chessBoard;
    }

    public static int reverseCoord (int[] coord){
        return coord[0] + coord[1] * 8;
    }

    public static HashSet<Integer> underAttack(Integer num){
        HashSet<Integer> attacked = new HashSet<>();
        for(Integer i = 0; i < 64; i++){
            if ((i == num) || (coord(i)[0] == coord(num)[0]) || (coord(i)[1] == coord(num)[1]) ||
                    Math.abs(coord(i)[0] - coord(num)[0]) == Math.abs(coord(i)[1] - coord(num)[1])) {
                attacked.add(i);
            }
        }
        return attacked;
    }

    public static HashSet<Integer> hashBoard (HashMap<Integer, Integer []> board){
        HashSet<Integer> hBoard = new HashSet<>();
        hBoard.addAll(board.keySet());
        return hBoard;
    }

    public static ArrayList<HashSet<Integer>> boardsInit (HashSet<Integer> hashBoard){
        ArrayList<HashSet<Integer>> boardsets = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            HashSet<Integer> tmp = new HashSet<>();
            for(Integer el: hashBoard){
                if(el >= i * 8 && el < (i + 1) * 8 ){
                    tmp.add(el);
                }
            }
            boardsets.add(tmp);
        }
        return boardsets;
    }

    public static void removeAttack (Integer[] setUp, HashSet<Integer> board, int k){
            board.removeAll(underAttack(setUp[k]));
        }


}
