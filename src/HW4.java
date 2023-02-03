import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.Stack;


public class HW4 {

    public static Scanner iScanner = new Scanner(System.in);

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

    public static void main(String[] args) throws IOException {
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
        Logger calcLogger = Log("logcalc.txt");
        System.out.println("Task2");
        calc(calcLogger);
        iScanner.close();
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

    public static Integer step;

    public static void calc(Logger logger)  {
        boolean stop = false;
        Stack <String> stackVar = new Stack<>();
        Stack <Integer> stackStep = new Stack<>();
        step = 1;
        Integer inp1 = null;
        Integer inp2 = null;
        while (!stop) {
            boolean err = true;
            if(step == 1) {
                inp1 = stepNum(err, logger, inp1, stackVar, stackStep);
                err = true;
            } else if (step == 2) {
                inp2 = stepNum(err, logger, inp2, stackVar, stackStep);
                err = true;
            }
            else if (step == 3) {
                stop = action(err, stop, logger, inp1, inp2, stackVar, stackStep);
                err = true;
            }
        }
    }

    public static boolean action (boolean err, boolean stop, Logger logger, Integer inp1,
                                  Integer inp2, Stack<String> stackVar, Stack<Integer> stackStep){
        while(err) {
            System.out.print("input action or stop or back");
            String act = iScanner.next();
            logger.log(Level.INFO, "input act " + act.toString());
            switch (act) {
                case "+" -> {
                    System.out.println();
                    System.out.println(inp1 + inp2);
                    err = false;
                    stackStep.push(step);
                    stackVar.push("+");
                    step = 1;
                }
                case "-" -> {
                    System.out.println();
                    System.out.println(inp1 - inp2);
                    err = false;
                    stackStep.push(step);
                    stackVar.push("-");
                    step = 1;
                }
                case "*" -> {
                    System.out.println();
                    System.out.println(inp1 * inp2);
                    err = false;
                    stackStep.push(step);
                    stackVar.push("*");
                    step = 1;
                }
                case "/" -> {
                    System.out.println();
                    if (inp2 == 0) {
                        System.out.println("can't divide by null");
                        break;
                    }
                    System.out.println(inp1.floatValue() / inp2.floatValue());
                    stackStep.push(step);
                    stackVar.push("/");
                    err = false;
                    step = 1;
                }
                case "stop" -> {
                    System.out.println();
                    System.out.println("stop");
                    err = false;
                    stop = true;
                }
                case "back" -> {
                    System.out.println();
                    System.out.println("back");
                    err = false;
                    step = stackStep.pop();
                    act = stackVar.pop();
                }
                default -> {
                    logger.log(Level.INFO, "wrong input act");
                    System.out.println("wrong input");
                    iScanner.next();
                }
            }
        }
    return stop;
    }

    public static Integer stepNum (boolean err, Logger logger, Integer inp1,
                                   Stack<String> stackVar, Stack<Integer> stackStep){
        while(err) {
            System.out.print("input number " + step.toString() + " ");
            String inp = iScanner.next();
            try {
                inp1 = Integer.valueOf(inp);
                logger.log(Level.INFO, "input number " + step.toString() +": " + inp1.toString() + " or 'back'");
                stackStep.push(step);
                stackVar.push(inp1.toString());
                err = false;
                step = step + 1;
            } catch (Exception ex){
                if(inp.equals("back")){
                    step = stackStep.pop();
                    try {
                        inp1 = Integer.valueOf(stackVar.pop());
                    }
                    catch (Exception sg){
                        stackVar.pop();
                        err = false;
                    }
                }
                else {
                    logger.log(Level.INFO, "wrong input number " + step.toString());
                    System.out.print("wrong input");
                    System.out.println();
                    iScanner.next();
                }
            }}
    return inp1;
    }


}