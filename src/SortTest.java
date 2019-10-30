import java.io.*;
import java.util.*;

public final class SortTest {
    public Comparable[] sequence;
    private int seqLength = 0;
    private void init(int seqLength) { sequence = new Comparable[seqLength]; this.seqLength = seqLength; }

    public SortTest() {}

    public SortTest(int seqLength, Random seed, Comparable classType) {
        init(seqLength);
        if (classType instanceof Number) {
            for (int i = 0; i < seqLength; i++)
                sequence[i] = seed.nextInt(seqLength) + 1;
        }
        else if (classType instanceof String) {
            for (int i = 0; i < seqLength; i++)
                sequence[i] = (char)(seed.nextInt(seqLength) + 97);
        }
    }

    public SortTest(int seqLength, Comparable classType) {
        init(seqLength);
        if (classType instanceof Number) {
            for (int i = 0; i < seqLength; i++)
                sequence[i] = i + 1;
        }
        else if (classType instanceof String) {
            for (int i = 0; i < seqLength; i++)
                sequence[i] = (char)(i + 97);
        }
    }

    public static void show(Comparable[] sequence) {
    List<? extends Comparable> list = Arrays.asList(sequence);
        System.out.println(list);
    }

    public SortTest copy() {
        SortTest temp = new SortTest();
        temp.seqLength = this.seqLength;
        temp.sequence = Arrays.copyOf(sequence, seqLength);
        return temp;
    }

    public static void main(String[] args) {
        SortTest test1 = new SortTest(20000, new Random(), new Integer(0));
        SortTest test2 = test1.copy();



        TaskMonitor monitor = new TaskMonitor();
        BasicSort sortType = new Insertion();
        sortType.setMonitor(monitor);
        monitor.taskBegin("Insertion");
        sortType.sort(test1.sequence);
        monitor.taskEnd(monitor);



        TaskMonitor monitor2 = new TaskMonitor();
        BasicSort sortType2 = new Selection();
        sortType2.setMonitor(monitor2);
        monitor2.taskBegin("Selection");
        sortType2.sort(test2.sequence);
        monitor2.taskEnd(monitor2);
    }
}