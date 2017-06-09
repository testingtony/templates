package templates;


import java.util.LinkedList;
import java.util.List;

public class Pojo {
    private int id;
    private String name;

    public Pojo(int id, String name) {
        setId(id);
        setName(name);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        if (name == null) {
            throw new NullPointerException("Name can not be null");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getDoubleId() {
        return id * 2.00001;
    }

    public String getLowerName() {
        return name.toLowerCase();
    }

    public List<Integer> fibonacci(int size) {
        List<Integer> series = new LinkedList<>();
        int last = 1;
        int cur = 1;
        series.add(cur);

        for(int i = 1; i < size; i++) {
            int temp = last + cur;
            last = cur;
            cur = temp;

            series.add(last);
        }

        return series;
    }
}
