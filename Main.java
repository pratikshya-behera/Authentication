import views.Welcome;

public class Main {

	public static void main(String[] args) {
		Welcome w = new Welcome();
		do {
			try {
				w.welcomeScreen();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}while(true);

	}

}
