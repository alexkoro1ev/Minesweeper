import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] line = s.nextLine().split(" ");
        List<String> list = new ArrayList<>();

        for (int i = line.length - 1; i >= 0; i--) {
            
            if (i % 2 != 0) {
                int n = Integer.parseInt(line[i]);
                list.add(line[i]);
            }
        }

        for (String str : list) {
            System.out.print(str + " ");
        }
    }
}
