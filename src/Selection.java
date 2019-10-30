public class Selection extends BasicSort {
    private TaskMonitor monitor;
    public void setMonitor(TaskMonitor monitor) {
        this.monitor = monitor;
    }
    @Override
    public void sort(Comparable[] sequence) {
        int len = sequence.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (less(sequence[i], sequence[j])) {
                    exch(sequence, i, j);
                    monitor.probeIncrement(monitor.OPERATE_BIT);
                }
                monitor.probeIncrement(monitor.SEQ_VISIT_BIT);
            }
        }
    }
}