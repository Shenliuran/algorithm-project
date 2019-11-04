public abstract class BasicSort implements SortType {
    public TaskMonitor monitor;

    @Override
    public void setMonitor(TaskMonitor monitor) {
        this.monitor = monitor;
    }
}