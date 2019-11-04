public interface Sortable {
    public void sort(Comparable<?>[] sequence);
    default public boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) <= 0;
    }
    default public void exch(Comparable<?>[] sequence, int i, int j) {
        Comparable<?> temp;
        temp = sequence[i];
        sequence[i] = sequence[j];
        sequence[j] = temp;
    }
    default public boolean isSorted(Comparable<?>[] v) {
        for (int i = 1; i < v.length; i++)
            if (!less(v[i], v[i - 1]))
                return false;
        return true;
    }
}