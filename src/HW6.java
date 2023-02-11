import java.util.*;

import static java.lang.Integer.parseInt;

public class HW6 {

    public static Scanner iScanner = new Scanner(System.in);

    public static void main(String[] args) {


         ArrayList<LapTop> lapTops = new ArrayList<>();
         lapTops.add(new LapTop("G125", "ASUS_8_256B", 8, 256, "Windows", "black", 54000));
         lapTops.add(new LapTop("G126", "ASUS_8_256W", 8, 256, "Windows", "white", 54000));
         lapTops.add(new LapTop("G127", "ASUS_8_256B", 8, 256, "Windows", "black", 54000));
         lapTops.add(new LapTop("G128", "ASUS_16_512G", 16, 512, "Windows", "grey", 72000));
         lapTops.add(new LapTop("G129", "ASUS_16_512G", 16, 512, "Windows", "grey", 72000));
         lapTops.add(new LapTop("G130", "ASUS_16_512G", 16, 512, "Windows", "grey", 72000));
         lapTops.add(new LapTop("G131", "HP8_512B", 8, 512, "Windows", "black", 68000));
         lapTops.add(new LapTop("G132", "HP_16_256B", 16, 256, "Windows", "black", 68000));
         lapTops.add(new LapTop("G133", "ACER_8_512W", 8, 512, "Linux", "white", 112000));
         lapTops.add(new LapTop("G134", "ACER32_1024W", 32, 1024, "Linux", "white", 160000));
         lapTops.add(new LapTop("G135", "ACER32_1024B", 32, 1024, "Linux", "black", 160000));

//         HashMap<String, LapTop> laptopTypes = setLaptop(lapTops); Тестировал hash — работает
         choseShow(lapTops);
         sortBy(lapTops);
         iScanner.close();
    }

    public static HashMap<String, LapTop> setLaptop(ArrayList<LapTop> aList){
        Map<String, LapTop> laptopMap = new HashMap();
        for(LapTop el: aList){
         laptopMap.put(el.getModel(),el);
        }
    return (HashMap<String, LapTop>) laptopMap;
    }

    public static  void choseShow(ArrayList<LapTop> lst){
        boolean work = true;
        while (work) {
            System.out.println("input filter\n1 — RAM\n2 — HDD\n3 — OS\n4 — color\n or stop");
            String inp = iScanner.next();
            Integer filter = null;
            String order = null;
            if (!inp.equals("stop")) {
                try {
                    filter = parseInt(inp);
                    if(filterTypes().containsKey(filter)){
                        order = filterTypes().get(filter);
                    }
                } catch (Exception e) {
                    System.out.println("wrong input");
                }
            } else {
                filter = 0;
                work = false;
            }
            System.out.println("filter by " + order);
            switch (filter){
                case 1:
                    for (LapTop el : lst) {
                        System.out.println(el.toStringRam());
                    }
                    break;
                case 2:
                    for (LapTop el : lst) {
                        System.out.println(el.toStringHDD());
                    }
                    break;
                case 3:
                    for (LapTop el : lst) {
                        System.out.println(el.toStringOs());
                    }
                    break;
                case 4:
                    for (LapTop el : lst) {
                        System.out.println(el.toStringColor());
                    }
                    break;
                default:
                    System.out.println("wrong input");
            }
        }
    }

    public static void sortBy(ArrayList<LapTop> laptopArray){
        boolean work = true;
        while (work) {
            System.out.println("input order\n1 — RAM\n2 — HDD\n3 — OS\n4 — color\n5 — model\n6 — price\nor stop");
            String inp = iScanner.next();
            Integer filter = null;
            String order = null;
            if (!inp.equals("stop")) {
                try {
                    filter = parseInt(inp);
                    if(filterTypes().containsKey(filter)){
                        order = filterTypes().get(filter);
                    }
                } catch (Exception e) {
                    System.out.println("wrong input");
                }
            } else {
                filter = 0;
                work = false;
            }
            System.out.println("filter by " + order);
            switch (filter){
                case 1:
                    laptopArray.sort(Comparator.comparing(LapTop::getRam));
                    break;
                case 2:
                    laptopArray.sort(Comparator.comparing(LapTop::getHd));
                    break;
                case 3:
                    laptopArray.sort(Comparator.comparing(LapTop::getOs));
                    break;
                case 4:
                    laptopArray.sort(Comparator.comparing(LapTop::getColor));
                    break;
                case 5:
                    laptopArray.sort(Comparator.comparing(LapTop::getModel));
                    break;
                case 6:
                    laptopArray.sort(Comparator.comparing(LapTop::getNPrice));
                    break;
            }
            printLaptops(laptopArray);
        }
    }

    public static Map<Integer, String> filterTypes(){
        Map<Integer, String> filters = new TreeMap<>();
        filters.put(1, "ram");
        filters.put(2, "hd");
        filters.put(3, "os");
        filters.put(4, "color");
        filters.put(5, "model");
        filters.put(6, "price");
        return filters;
    }

/* Как передать метод внутрь другого метода?
    попытка тут:
    public static void printLaptops(ArrayList<LapTop> lst, UnaryOperator<LapTop> toStr) {
        for (LapTop el : lst) {
            String elStr = String.valueOf(toStr.apply(el));
            System.out.println(elStr);
        }
        System.out.println("---------------");
    }
    в основном коде такой вызов дает ошибку:
    printLaptops(laptopArray, LapTop::toStringRam);
 */
    public static void printLaptops(ArrayList<LapTop> lst) {
        for (LapTop el : lst) {
            System.out.println(el.toString());
        }
        System.out.println("---------------");
    }

}
