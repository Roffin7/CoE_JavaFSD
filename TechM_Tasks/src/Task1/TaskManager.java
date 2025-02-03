package Task1;

import java.util.*;
class Task 
{
    private String id;
    private String description;
    private int priority;
    public Task(String id, String description, int priority)
    {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }
    public String get_Id() 
    {
        return id;
    }

    public int get_Priority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Task[ID=" + id + ", Description=" + description + ", Priority=" + priority + "]";
    }
}

class TaskManager1
{
    private PriorityQueue<Task> taskQueue;
    private Map<String, Task> taskMap;
    public TaskManager1() 
    {
        taskQueue = new PriorityQueue<>(Comparator.comparingInt(Task::get_Priority).reversed());
        taskMap = new HashMap<>();
    }
    public void add_Task(String id, String description, int priority)
    {
        Task task = new Task(id, description, priority);
        taskQueue.offer(task);
        taskMap.put(id, task);
    }
    public void remove_Task(String id) 
    {
        if (taskMap.containsKey(id)) 
        {
            taskMap.remove(id);
            rebuild_Queue();
        }
    }
    private void rebuild_Queue()
    {
        taskQueue = new PriorityQueue<>(Comparator.comparingInt(Task::get_Priority).reversed());
        taskQueue.addAll(taskMap.values());
    }

    public Task getHighestPriorityTask() {
        return taskQueue.peek();
    }
}
class TaskManager 
{
    public static void main(String[] args)
    {
        TaskManager1 manager = new TaskManager1();
        manager.add_Task("1", "Fix bug", 3);
        manager.add_Task("2", "Develop feature", 5);
        manager.add_Task("3", "Code review", 2);
        System.out.println("Highest Priority Task: " + manager.getHighestPriorityTask());
        manager.remove_Task("2");
        System.out.println("Highest Priority Task after removal: " + manager.getHighestPriorityTask());
    }
}