package Part2;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a Node in a graph
 * contains data of parametric type T and a reference to parent node
 */
public class GraphNode<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private @Nullable
    T data;
    private @Nullable GraphNode<T> parent;

    public GraphNode(){
        this(null);
    }

    public GraphNode(@Nullable final T data){
        this(data,null);
    }

    // create most specific C'tor
    public GraphNode(@Nullable final T data, @Nullable final GraphNode<T> parent){
        this.data = data;
        this.parent = parent;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @NotNull
    public GraphNode<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Nullable
    public GraphNode<T> getParent() {
        return parent;
    }

    @NotNull
    public GraphNode<T> setParent(GraphNode<T> parent) {
        this.parent = parent;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode<?> graphNode = (GraphNode<?>) o;
        return Objects.equals(data, graphNode.data) &&
                Objects.equals(parent, graphNode.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, parent);
    }

    @Override
    public String toString(){
        return data.toString();
    }
}