public class Insertion extends BasicSort {
    @Override
    public void sort(Comparable<?>[] a) {
        int len = a.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                    monitor.probeIncrement(monitor.SEQ_VISIT_BIT);
                }
                monitor.probeIncrement(monitor.OPERATE_BIT);
            }
        }
    }
}