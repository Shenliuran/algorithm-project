import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public final class SortTest {
    public Comparable<?>[] sequence;
    private int seqLength = 0;

    private void init(int seqLength) { sequence = new Comparable[seqLength]; this.seqLength = seqLength; }

    /**
     * default constructor
     */
    public SortTest() {}

    /**
     * 
     * @param seqLength the number of comparable object
     * @param seed  the random seed
     * @param classType the object which extends comparable interface
     */
    public SortTest(int seqLength, Random seed, Comparable<?> classType) {
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

    /**
     * 
     * @param seqLength the number of comparable object
     * @param classType the object which extends comparable interface
     */
    public SortTest(int seqLength, Comparable<?> classType) {
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

    /**
     * 
     * @param sequence
     */
    public static void show(Comparable<?>[] sequence) {
    List<Comparable<?>> list = Arrays.asList(sequence);
        System.out.println(list);
    }

    /**
     * 
     * @return the clone object
     */
    public SortTest copy() {
        SortTest temp = new SortTest();
        temp.seqLength = this.seqLength;
        temp.sequence = Arrays.copyOf(sequence, seqLength);
        return temp;
    }

    /**
     * 
     * @param generator generate comparable sequence
     * @param sortType method of sort
     * @param repeatTimes times of repeat
     */
    public static void test(SortTest generator, BasicSort sortType, int repeatTimes) {
        TaskMonitor monitor = new TaskMonitor();
        sortType.setMonitor(monitor);
        long[] avg = new long[3];
        for (int i = 0; i < 3; i++)
            avg[i] = 0;
        System.out.println("Begin");
        System.out.println(sortType.getClass().getName() + ":");
        System.out.println("Repeat times: " + repeatTimes);
        for (int i = 0; i < repeatTimes; i++) {
            monitor.taskBegin();
            sortType.sort(generator.sequence);
            monitor.taskEnd();
            avg[monitor.SEQ_VISIT_BIT] += monitor.getSeqVisits() / repeatTimes;
            avg[monitor.OPERATE_BIT] += monitor.getOperateTimes() / repeatTimes;
            avg[monitor.TIMECOST_BIT] += monitor.getTimeCost() / repeatTimes;
        }
        System.out.println("average sequence visits: " + avg[monitor.SEQ_VISIT_BIT]);
        System.out.println("average compare times: " + avg[monitor.OPERATE_BIT]);
        System.out.println("average time cost: " + avg[monitor.TIMECOST_BIT]);
        System.out.println("End");
        System.out.println();
    }

    public static void main(String[] args) {
        SortTest test1 = new SortTest(20000, new Random(), new Integer(0));
        SortTest test2 = test1.copy();

        BasicSort sortType = new Insertion();
        test(test1, sortType, 2);

        BasicSort sortType2 = new Selection();
        test(test2, sortType2, 2);
    }
}