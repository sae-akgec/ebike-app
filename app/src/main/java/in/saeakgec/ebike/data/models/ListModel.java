package in.saeakgec.ebike.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListModel<T> {

    @SerializedName("count")
    private int count;

    @SerializedName("next")
    private int next;

    @SerializedName("previous")
    private int previous;

    @SerializedName("results")
    private ArrayList<T> results;

    public ListModel() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public ArrayList<T> getResults() {
        return results;
    }

    public void setResults(ArrayList<T> results) {
        this.results = results;
    }
}
