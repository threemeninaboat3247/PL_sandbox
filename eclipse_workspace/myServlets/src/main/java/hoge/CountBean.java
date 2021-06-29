package hoge;

public class CountBean {
    private int count = 0;
    public int getCount() {
        return count;
    }
    synchronized public void increase(){
        count++;
    }
    synchronized public void decrease(){
        count--;
    }
}



