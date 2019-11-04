public class MergenceFromTopToButton extends Mergeable {
    private Comparable<?>[] aux;
    /**
     * this is private assistant array
     * @param a the comparable array
     * @param low the low index of array
     * @param high the high index of array
     */
    private void auxSort(Comparable<?>[] a, int low, int high) {
        monitor.probeIncrement(monitor.SEQ_VISIT_BIT);
        if (high <= low) return;
        int middle = low + (high - low) / 2;
        auxSort(a, low, middle);
        auxSort(a, middle + 1, high);
        merge(a, low, middle, high, aux); }
    
    @Override
    public void sort(Comparable<?>[] a) {
        aux = new Comparable[a.length];
        auxSort(a, 0, a.length - 1);
    }
}
