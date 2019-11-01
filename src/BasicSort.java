public abstract class BasicSort implements Sortable {
    protected TaskMonitor monitor;
    public void setMonitor(TaskMonitor monitor) {
        this.monitor = monitor;
    }
    public TaskMonitor getMonitor() { return monitor; }
}