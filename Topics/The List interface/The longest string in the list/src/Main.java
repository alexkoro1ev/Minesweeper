import java.util.*;

public class Main {

    static void changeList(List<String> list) {
        int len = -1;
        String s = null;
        for (String str : list) {
            if (str.length() > len) {
                len = str.length();
                s = str;
            }
        }

        Collections.fill(list, s);
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        List<String> lst = Arrays.asList(s.split(" "));
        changeList(lst);
        lst.forEach(e -> System.out.print(e + " "));
    }
}