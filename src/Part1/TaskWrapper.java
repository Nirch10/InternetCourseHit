package Part1;

import java.util.concurrent.*;
import java.util.function.Function;

public class TaskWrapper<V> implements RunnableFuture<V>, Comparable<TaskType> {
    protected BlockingQueue<V> taskQueue;
    protected TaskType taskType;
    protected Function<Runnable, V> defaultFunction;
    protected RunnableFuture<V> runnableFuture;



    public TaskWrapper(RunnableFuture<V> runnableFuture, TaskType taskType){
        this.taskType = taskType;
        this.runnableFuture = runnableFuture;
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
    public int compareTo(TaskType taskType) {
        if(taskType.getPriority() < this.taskType.getPriority()) return 1;
        if(taskType.getPriority() > this.taskType.getPriority()) return -1;
        return 0;
    }
//    public void convertAndAddToQueue(final Runnable runnable, Function<Runnable, V> runnableTFunction) {
//        if (runnableTFunction == null) {
//            runnableTFunction = defaultFunction;
//        }
//        try {
//            taskQueue.offer(runnableTFunction.apply(runnable), 1000, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    public String toString(){
        return taskType.toString();
    }

//
//    public <V> Future<V> convertAndAddToQueue(final Callable<V> callable,
//                                              Function<Runnable,V> runnableTFunction)
//    {
//        FutureTask<V> futureTask = new FutureTask<>(callable);
//        //convertAndAddToQueue(futureTask,runnableTFunction);
//        return futureTask;
//    }

}
