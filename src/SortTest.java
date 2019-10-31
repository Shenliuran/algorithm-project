import java.util.List;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.Random;

public final class SortTest implements Callable<long[]> {
    public Comparable<?>[] sequence;
    private BasicSort sortType;
    private int seqLength = 0;

    private static long[] initLongs(int len) {
        long[] temp = new long[len];
        for (int i = 0; i < len; i++)
            temp[i] = 0;
        return temp;
    }

    private void setSortType(BasicSort aSortType) { sortType = aSortType; }

    @Override
    public long[] call() throws Exception {
        long[] probe = initLongs(3);
        TaskMonitor monitor = new TaskMonitor();
        sortType.setMonitor(monitor);
        monitor.taskBegin();
        sortType.sort(sequence);
        monitor.taskEnd();
        probe[monitor.SEQ_VISIT_BIT] = monitor.getSeqVisits();
        probe[monitor.OPERATE_BIT] = monitor.getOperateTimes();
        probe[monitor.TIMECOST_BIT] = monitor.getTimeCost();
        return probe;
    }

    /**
     * default constructor
     */
    public SortTest() {}

    public SortTest(int seqLength) {
        sequence = new Comparable[seqLength];
        this.seqLength = seqLength;
    }
    /**
     * 
     * @param seqLength the number of comparable object
     * @param seed  the random seed
     * @param classType the object which extends comparable interface
     * @return return SortTest object containing a random sequence
     */
    public static SortTest randSequence(int seqLength, Random seed, Comparable<?> classType) {
        SortTest temp = new SortTest(seqLength);
        if (classType instanceof Number) {
            for (int i = 0; i < seqLength; i++)
                temp.sequence[i] = Integer.valueOf(seed.nextInt(temp.seqLength) + 1);
        }
        else if (classType instanceof String) {
            for (int i = 0; i < seqLength; i++)
                temp.sequence[i] = Integer.valueOf(seed.nextInt(temp.seqLength) + 97);
        }
        return temp;
    }

    /**
     * 
     * @param seqLength the number of comparable object
     * @param classType the object which extends comparable interface
     * @return return SortTest object containing a reverse sequence
     */
    public static SortTest reverseSequence(int seqLength, Comparable<?> classType) {
        SortTest temp = new SortTest(seqLength);
        if (classType instanceof Number) {
            for (int i = 0; i < seqLength; i++)
                temp.sequence[i] = Integer.valueOf(i + 1);
        }
        else if (classType instanceof String) {
            for (int i = 0; i < seqLength; i++)
                temp.sequence[i] = String.valueOf(i + 97);
        }
        return temp;
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
     * @param sortType method of sort
     * @param repeatTimes times of repeat
     */
//    public void test(SortTest generator, BasicSort sortType, int repeatTimes) {
//        setSortType(sortType);
//        TaskMonitor monitor = new TaskMonitor();
//        sortType.setMonitor(monitor);
//        long[] avg = initLongs(3);
//        System.out.println("Begin");
//        System.out.println(sortType.getClass().getName() + ":");
//        System.out.println("Repeat times: " + repeatTimes);
//
//        for (int i = 0; i < repeatTimes; i++) {
//            monitor.taskBegin();
//            sortType.sort(generator.sequence);
//            monitor.taskEnd();
//            avg[monitor.SEQ_VISIT_BIT] += monitor.getSeqVisits() / repeatTimes;
//            avg[monitor.OPERATE_BIT] += monitor.getOperateTimes() / repeatTimes;
//            avg[monitor.TIMECOST_BIT] += monitor.getTimeCost() / repeatTimes;
//        }
//
//        System.out.println("average sequence visits: " + avg[monitor.SEQ_VISIT_BIT]);
//        System.out.println("average compare times: " + avg[monitor.OPERATE_BIT]);
//        System.out.println("average time cost: " + avg[monitor.TIMECOST_BIT]);
//        System.out.println("End");
//        System.out.println();
//    }

    public void test(BasicSort sortType, int repeatTimes) {
        setSortType(sortType);
        TaskMonitor mainMonitor = new TaskMonitor();
        long[] avg = initLongs(3);
        System.out.println("Begin");
        System.out.println(sortType.getClass().getName() + ":");
        System.out.println("Repeat times: " + repeatTimes);
        for (int i = 0; i < repeatTimes; i++) {
            try {
                long[] oneSort = call();
                avg[mainMonitor.SEQ_VISIT_BIT] += oneSort[mainMonitor.SEQ_VISIT_BIT] / repeatTimes;
                avg[mainMonitor.OPERATE_BIT] += oneSort[mainMonitor.OPERATE_BIT] / repeatTimes;
                avg[mainMonitor.TIMECOST_BIT] += mainMonitor.getTimeCost() / repeatTimes;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("average sequence visits: " + avg[mainMonitor.SEQ_VISIT_BIT]);
        System.out.println("average compare times: " + avg[mainMonitor.OPERATE_BIT]);
        System.out.println("average time cost: " + avg[mainMonitor.TIMECOST_BIT]);
        System.out.println("End");
        System.out.println();
    }

    public static void main(String[] args) {
        Integer integer = 0;
        SortTest test1 = randSequence(20000, new Random(), integer);
        SortTest test2 = test1.copy();

        BasicSort sortType = new Insertion();
        test1.test(sortType, 2);

        BasicSort sortType2 = new Selection();
        test2.test(sortType2, 2);
    }
}