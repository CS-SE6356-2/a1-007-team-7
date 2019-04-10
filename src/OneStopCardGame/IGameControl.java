package OneStop;

public interface IGameControl {
	void playGame();
	void runGame();
	void init();
}

interface IGameView{
	void display(String message);
	char getInput();
}
