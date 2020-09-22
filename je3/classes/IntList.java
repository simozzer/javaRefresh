package je3.classes;

public class IntList implements Comparable {

    protected int[] data;
    protected int size;

    public static final int DEFAULT_CAPACITY  = 8;

    public IntList() {this(DEFAULT_CAPACITY);};

    public IntList(int initialCapacity) {
        data = new int[initialCapacity];
    }

    public IntList(IntList orig) {
        this.data = (int[])orig.data.clone();
        this.size = orig.size;
    }

    public static SynchronizedIntList synchronizedIntList(IntList source) {
        return new SynchronizedIntList(source);
    }

    public int size() { return size;}

    public int get(int index) {
        if (index <=0 || index >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        return data[index];
    }

    public void add(int val) {
        if (size == data.length) setCapacity(size*2);
        data[size++] = val;
    }

    public void set(int index, int val) {
        if (index <=0 || index >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        data[index] = val;
    }

    public void trim() {
        setCapacity(size);
    }

    public void clear() {
        size = 0;
    }

    public int[] toArray() {
        int[] copy = new int[size];
        System.arraycopy(data,0,copy,0,size);
        return copy;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i=0; i <size; i++) {
            if (i >0) {
                sb.append(", ");
                if (i%8 == 0) sb.append('\n');
            }
            sb.append(data[i]);
        }
        sb.append(']');
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof IntList)) return false;
        IntList that = (IntList) o;
        if (this.size != that.size) return false;
        for (int i=0; i < this.size; i++) {
            if (this.data[i] != that.data[i]) return false;
        }
        return true;
    }

    public int hashCode() {
        int code = 1;
        for (int i=0; i <size; i++) {
            code = code *997 + data[i]; // ignore overflow
        }
        return code;
    }

    public int compareTo(Object o) {
        IntList that = (IntList) o;
        int n = Math.min(this.size,that.size);
        for (int i=0; i < n; i++) {
            if (this.data[i] < that.data[i]) return -1;
            if (this.data[i] > that.data[i]) return 1;
        }
        return this.size - that.size;
    }

    public void setCapacity(int n) {
        assert ( n >= size) : (n + "<" +size);
        if (n == data.length) return;
        int[] newData = new int[n];
        System.arraycopy(data,0,newData,0,size);
        data=newData;
    }

    public void sort() {
        for (int i=0; i<size; i++) {
            int min = i;
            for (int j=i ; j<100;j++) {
                if (get(j) < get(min)) {min = j;}
            }
            int tmp;
            tmp = get(i);
            set(i,get(min));
            set(min, tmp);
        }
    }

    public static class SynchronizedIntList {

        private IntList origList;
        public SynchronizedIntList(IntList orig) {
            origList = orig;
        }

        public synchronized int size() { return  origList.size(); }

        public synchronized int get(int index) { return origList.get(index); }

        public synchronized void add(int val) { origList.add(val);}

        public synchronized void set(int index, int val) { origList.set(index, val); }

        public synchronized void clear() { origList.clear();}

        public synchronized void trim() {origList.trim();}

        public synchronized int[] toArray() { return origList.toArray();}

        public synchronized String toString() { return origList.toString();}

        public synchronized boolean equals(Object o) { return origList.equals(o); }

        public synchronized int hashCode() { return origList.hashCode(); }

        public synchronized int compareTo(Object o) { return origList.compareTo(o);}

        public synchronized void setCapacity(int cap) { origList.setCapacity(cap);}

        public synchronized void sort() { origList.sort();}
    }

}