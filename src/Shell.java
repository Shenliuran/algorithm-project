public class Shell extends BasicSort {
    @Override
    public void sort(Comparable<?>[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3)
            h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (less(a[j], a[j - h])) {
                        exch(a, j, j - h);
                        monitor.probeIncrement(monitor.OPERATE_BIT);
                    }
                    monitor.probeIncrement(monitor.SEQ_VISIT_BIT);
                }
            }
            h = h / 3;
        }
    }
}
