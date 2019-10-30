public final class TaskMonitor  {
    private long timeCost;
    private long beginTime;
    private long endTime;
    public final int SEQ_VISIT_BIT = 0;
    public final int OPERATE_BIT = 1;
    private long[] probe;

    public TaskMonitor() {
        timeCost = 0;
        beginTime = endTime = System.currentTimeMillis();
        probe = new long[2];
    }

    public void taskBegin(String taskName) {
        System.out.println("--------------------------------");
        System.out.println(taskName + ":");
        System.out.println("Test begin:");
        beginTime = System.currentTimeMillis();
    }
    public void taskEnd(TaskMonitor monitor) {
        endTime = System.currentTimeMillis();
        System.out.println("time cost: " + monitor.getTimeCost());
        System.out.println("average visits: " + monitor.getSeqVisits());
        System.out.println("average compare times:" + monitor.getOperateTimes());
        System.out.println("Test end!!");
        System.out.println("-------------------------------");
        System.out.println();
    }
    public void probeIncrement(int probeType) {
        if (probeType == SEQ_VISIT_BIT)
            probe[SEQ_VISIT_BIT]++;
        else
            probe[OPERATE_BIT]++;
    }


    public long getTimeCost() {
        timeCost = endTime - beginTime;
        // if (timeCost > 100)
            // System.out.println("time cost warning: time > 100 millisecond");
        return timeCost;
    }
    public long getSeqVisits() { return probe[SEQ_VISIT_BIT]; }
    public long getOperateTimes() { return probe[OPERATE_BIT]; }
}