package Part1;

import java.util.concurrent.*;
import java.util.function.Function;

public class TaskWrapper<V> implements RunnableFuture<V>, Comparable<TaskWrapper<? extends V>> {
    protected TaskType taskType;
    protected RunnableFuture<V> runnableFuture;

    public TaskWrapper(Runnable runnable, TaskType taskType){
        this.taskType = taskType;
        this.runnableFuture = (RunnableFuture<V>) runnable;
    }

    public TaskWrapper(Callable<V> callable, TaskType taskType){
        this.taskType = taskType;
        this.runnableFuture = new FutureTask<>(callable);
    }

    public TaskWrapper() {
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
    public int compareTo(TaskWrapper<? extends V> taskWrapper) {
        return taskWrapper.taskType.getPriority().compareTo(taskType.getPriority());
    }

    @Override
    public String toString(){
        return taskType.toString();
    }


    public static void main(String[] args) {
        TaskWrapper<String> ts = new TaskWrapper<String>();
        TaskWrapper<Number> ts1 = new TaskWrapper<Number>();
        TaskWrapper<Integer> ts2 = new TaskWrapper<Integer>();
        //Integer -> Number
        int comp = ts1.compareTo(ts2);
        //int comp1 = ts1.compareTo(ts);
    }
}


