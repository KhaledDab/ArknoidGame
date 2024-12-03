package Game;

public class Counter {
    // add number to current count.
    private int count;
    public Counter(){
        this.count = 0;
    }
    public Counter(int count){
        this.count = count;
    }
    void increase(int number){
        this.count += number;
    }
    // subtract number from current count.
    void decrease(int number){
        this.count -= number;
    }
    // get current count.
    int getValue(){
        return this.count;
    }
}