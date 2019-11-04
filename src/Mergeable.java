public abstract class Mergeable implements SortType {
    protected TaskMonitor monitor;

    /**
     *
     * @param a a comparable array
     * @param low the low index of array
     * @param middle the middle index of array
     * @param high the high index of array
     */
    protected void merge(Comparable<?>[] a, int low, int middle, int high, Comparable<?>[] aux) {
        int i = low;
        int j = middle + 1;
        for (int k = low; k <= high; k++)
            aux[k] = a[k];

        for (int k = low; k <= high; k++) {
            if (i > middle)         a[k] = aux[j++];
            else if (j > high)      a[k] = aux[i++];
            else if (less(aux[j],aux[i]) && monitor.probeIncrement(monitor.OPERATE_BIT))
                                    a[k] = aux[j++];
            else                    a[k] = aux[i++];
        }
    }

    @Override
    public void setMonitor(TaskMonitor monitor) {
        this.monitor = monitor;
    }
}
