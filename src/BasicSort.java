public abstract class BasicSort implements Sortable {
    private TaskMonitor monitor;
    public void setMonitor(TaskMonitor monitor) {
        this.monitor = monitor;
    }
    public TaskMonitor getMonitor() { return monitor; }
}