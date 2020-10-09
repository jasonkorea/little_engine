package input;

public interface IInput {
    void clicked(int x, int y, int button);
    void pressed(int x, int y, int button);
    void released(int x, int y, int button);
    void entered(int x, int y, int button);
    void exited(int x, int y, int button);
    void drag(int x, int y); 
	
}
