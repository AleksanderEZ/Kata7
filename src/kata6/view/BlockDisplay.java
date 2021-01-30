package kata6.view;

public interface BlockDisplay {
    void show(int x, int y);
    void register(Moved moved);
    
    interface Moved {
        void to(int x, int y);
    }
}
