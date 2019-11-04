package sortinterface;


import monitor.TaskMonitor;

public interface SortType extends Sortable {
    void setMonitor(TaskMonitor monitor);
}
