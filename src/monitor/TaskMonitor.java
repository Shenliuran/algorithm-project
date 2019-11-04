package monitor;

public final class TaskMonitor  {
    private long timeCost;
    private long beginTime;
    private long endTime;
    public final int SEQ_VISIT_BIT = 0;
    public final int OPERATE_BIT = 1;
    public final int TIMECOST_BIT = 2;
    private long[] probe;

    public TaskMonitor() {
        timeCost = 0;
        beginTime = endTime = System.currentTimeMillis();
        probe = new long[2];
    }

    public void taskBegin() {
        beginTime = System.currentTimeMillis();
    }
    public void taskEnd() {
        endTime = System.currentTimeMillis();
    }
    public boolean probeIncrement(int probeType) {
        if (probeType == SEQ_VISIT_BIT)
            probe[SEQ_VISIT_BIT]++;
        else
            probe[OPERATE_BIT]++;
        return true;
    }


    public long getTimeCost() {
        timeCost = endTime - beginTime;
        return timeCost;
    }
    public long getSeqVisits() { return probe[SEQ_VISIT_BIT]; }
    public long getOperateTimes() { return probe[OPERATE_BIT]; }
}