package Part1;

import java.util.concurrent.*;

public class TaskWrapper<V> implements RunnableFuture<V>, Comparable<TaskWrapper<? extends V>> {
    protected TaskType taskType;
    protected RunnableFuture<V> runnableFuture;

    public TaskWrapper(Runnable runnable, TaskType taskType, V result){
        this.taskType = taskType;
        this.runnableFuture = new FutureTask<>(runnable, result);
    }

    public TaskWrapper(Callable<V> callable, TaskType taskType){
        this.taskType = taskType;
        this.runnableFuture = new FutureTask<>(callable);
    }

    @Override
    public void run() {
        if(runnableFuture != null)
            runnableFuture.run();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return runnableFuture.cancel(mayInterruptIfRunning);
    }

    @Override
    public boolean isCancelled() {
        return runnableFuture.isCancelled();
    }

    @Override
    public boolean isDone() {
        return runnableFuture.isDone();
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {return runnableFuture.get();}

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return runnableFuture.get(timeout, unit);
    }

    @Override
    public int compareTo(TaskWrapper taskWrapper) {
        return taskWrapper.taskType.getPriority().compareTo(taskType.getPriority());
    }

    @Override
    public String toString(){
        return taskType.toString();
    }

}
