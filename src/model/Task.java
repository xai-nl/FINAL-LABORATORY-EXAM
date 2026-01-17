package model;

public class Task extends AbstractTask {

    private String status;

    public Task() {
        this.status = "NOT STARTED";
    }

    public Task(int taskId, String taskName, String taskDescription, String status) {
        super(taskId, taskName, taskDescription);
        this.status = status;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        if (status.equals("DONE") || status.equals("ONGOING") || status.equals("NOT STARTED")) {
            this.status = status;
        }
    }
}