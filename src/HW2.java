import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.*;

import org.json.JSONArray;
import org.json.JSONObject;


public class HW2 {

    public static class Student{
        String name;
        String country;
        String city;
        String age;
    }

    public static class Result{
        String surname;
        String subject;
        int mark;
    }

    public static HashMap resFields (){
        HashMap <String, String> res = new HashMap<>();
        res.put("предмет", " по предмету ");
        res.put("оценка", " получил ");
        res.put("фамилия", "Студент ");
        return res;
    }

    public static Scanner iScanner = new Scanner(System.in);

    static Charset charset = StandardCharsets.UTF_8;


    public static void main (String[] args) throws IOException, IllegalAccessException {
        ///task 1
            /*
            1) Дана строка sql-запроса "select * from students where ". Сформируйте часть WHERE этого запроса, используя StringBuilder.
            Данные для фильтрации приведены ниже в виде json строки.
            Если значение null, то параметр не должен попадать в запрос.
            Параметры для фильтрации: String str = "{'name':'Ivanov', 'country':'Russia', 'city':'Moscow', 'age':'null'}";
            Результат "select * from students where “name” = “Ivanov” and “country”=”Russia” …
             */
        /*
        System.out.println("Task 1");
        String fileName = "sql_rq.json";
        StringBuilder sqlRq = sqlReq(fileName, 0);
        System.out.println(sqlRq);
        System.out.println();
         */
        /// task2
        System.out.println("Task 2");
        Logger iLogger = Log("logbubble.txt");
        System.out.println("input array length");
        int len = iScanner.nextInt();
        System.out.println("input array range");
        int range = iScanner.nextInt();
        Integer [] array = randomArr(len, range);
        System.out.println(Arrays.toString(array));
        bubbleSort(array, iLogger);
        System.out.println(Arrays.toString(array));
            /*
            2) Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.
             */
        /// task3
            /*
            3) ** Дана json строка (можно сохранить в файл и читать из файла)
            [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},
            {"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
            {"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
            Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].
            Пример вывода:
            Студент Иванов получил 5 по предмету Математика.
            Студент Петрова получил 4 по предмету Информатика.
            Студент Краснов получил 5 по предмету Физика.
             */
        String resFile = "students.json";
        JSONArray stList = arrayFromJsonFile(resFile);
        StringBuilder studRes = txtFromStudents(stList);
        System.out.println(studRes);
        /// task4
            /*
            4) К калькулятору из предыдущего дз добавить логирование.
            */
        Logger calcLogger = Log("logcalc.txt");
        calc(calcLogger);
        iScanner.close();

    }

    public static Logger Log (String logfile) throws IOException {
        Logger iLogger = Logger.getLogger(HW2.class.getName());
        iLogger.setUseParentHandlers(false);
        String pathProject = System.getProperty("user.dir");
        String pathFile = pathProject.concat("\\src\\" + logfile);
        FileHandler fh = new FileHandler(pathFile);
        iLogger.addHandler(fh);
        SimpleFormatter txt = new SimpleFormatter();
        fh.setFormatter(txt);
        return iLogger;
    }

    public  static void bubbleSort (Integer[] list, Logger iLogger){
        int n = list.length;
        Integer i = 0;
        boolean changed = true;
        while (changed){
            changed = false;
            for (Integer j = 0; j < n - i - 1; j++){
                if(list[j] > list[j + 1]){
                    int tmp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = tmp;
                    iLogger.log(Level.INFO,  "swap element with index " + j.toString());
                    changed = true;
                }
            }
            i++;
            iLogger.log(Level.INFO, "finished step " + i.toString());
        }
    }
    public static Integer[] randomArr (int len, int range ) {
        Random random = new Random();
        Integer [] arr = new Integer[len];
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(range);
        }
        return arr;
    }

    public static StringBuilder txtFromStudents(JSONArray list){
        StringBuilder txtRes = new StringBuilder();
        for (Object stResult: list) {
            Map<String, Object> studentResult = ((JSONObject) stResult).toMap();
            studentResult = sortByKeys(studentResult);
            for (String i: studentResult.keySet()){
                txtRes.append(resFields().get(i)).append(studentResult.get(i));
            }
            txtRes.append("\n");
        }
        return txtRes;
    }

    public static StringBuilder sqlReq(String sql_req, int number) throws IOException, IllegalAccessException {
        StringBuilder req = new StringBuilder("select * from students where ");
        Student cur_student = studentsParse(sql_req).get(number);
        req.append(studentToString(cur_student));
        return req;
    }

    public static StringBuilder studentToString(Student student) throws IllegalAccessException {
        StringBuilder txt = new StringBuilder();
        Field [] stFields = student.getClass().getDeclaredFields();
        for(Field field: stFields){
            field.setAccessible(true);
            Object value = field.get(student);
            if (!value.equals("null")) {
                String key = field.getName();
                txt.append(" and \"").append(key).append("\" = \"").append(value).append("\"");
            }
        }
        return txt;
    }

    public static JSONArray arrayFromJsonFile(String file) throws IOException {
        String pathProject = System.getProperty("user.dir");
        String pathFile = pathProject.concat("\\src\\" + file);
        String data = new String(Files.readAllBytes(Paths.get(pathFile)));
        return new JSONArray(data);
    }

    public static ArrayList<Student> studentsParse(String file) throws IOException {
        JSONArray jsonArray = arrayFromJsonFile(file);
        ArrayList<Student> students = new ArrayList<>();
        Student tmp = new Student();
        for(Object i :jsonArray) {
            tmp.country = (String) ((JSONObject) i).toMap().get("country");
            tmp.name = (String) ((JSONObject) i).toMap().get("name");
            tmp.city = (String) ((JSONObject) i).toMap().get("city");
            tmp.age = (String) ((JSONObject) i).toMap().get("age");
            students.add(tmp);
        }
        return students;
    }

    public static void calc(Logger logger)  {
        boolean stop = false;
        while (!stop) {
            Integer inp1 = null;
            Integer inp2 = null;
            boolean err = true;
            while(err) {
                System.out.print("input number 1 ");
                try {
                    inp1 = iScanner.nextInt();
                    logger.log(Level.INFO, "input number 1 " + inp1.toString());
                    err = false;
                } catch (Exception ex){
                    logger.log(Level.INFO, "wrong input number 1 ");
                    System.out.print("wrong input");
                    System.out.println();
                    iScanner.next();
                }}
            err = true;
            while(err) {
                System.out.print("input number 2 ");
                try {
                    inp2 = iScanner.nextInt();
                    logger.log(Level.INFO, "input nomber 2 " + inp1.toString());
                    err = false;
                } catch (Exception ex){
                    logger.log(Level.INFO, "wrong input number 2 ");
                    System.out.print("wrong input");
                    System.out.println();
                    iScanner.next();
                }}
            err = true;
            while(err) {
                System.out.print("input action or stop ");
                String act = iScanner.next();
                logger.log(Level.INFO, "input act " + act.toString());
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
                        logger.log(Level.INFO, "wrong input act");
                        System.out.println("wrong input");
                        iScanner.next();
                    }
                }
            }
        }
    }

    public static <K extends Comparable, V> Map<K, V> sortByKeys(Map<K, V> map)
    {
        Map<K, V> treeMap = new TreeMap<>(new Comparator<K>() {
            @Override
            public int compare(K a, K b) {
                return b.compareTo(a);
            }
        });
        treeMap.putAll(map);
        return treeMap;
    }
}
