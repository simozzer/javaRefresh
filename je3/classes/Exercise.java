package je3.classes;

public class Exercise {

    public static void main(String[] args) {

        int LIST_COUNT = 5;
        int LIST_ENTRIES = 5;
        IntList[] lists = new IntList[LIST_COUNT];
        for (int listIndex = 0; listIndex < LIST_COUNT; listIndex++){
            IntList list = new IntList();
            for (int entryIndex = 0; entryIndex < LIST_ENTRIES; entryIndex++) {
                list.add((int)Math.round(Math.random()* 100));
            }
            lists[listIndex] = list;
        }

        // sort
        for (int i=0; i<lists.length; i++) {
            int min = i;
            for (int j=i ; j< lists.length;j++) {
                if (lists[j].compareTo(lists[min]) < 0) min = j;
            }
            IntList tmp;
            tmp = new IntList(lists[i]);
            lists[i] = lists[min];
            lists[min] = tmp;
        }

        //report
        for (int li = 0; li < LIST_COUNT; li++){
            System.out.println(li);
            System.out.print(lists[li].toString());
            System.out.println();
        }
    }

}
