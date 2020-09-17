package je3.basics;

public class Echo {

    public static void main(String[] args) {
        for(int i=0; i< args.length; i++) {
            System.out.print(args[i] + " ");
        }
    }
}