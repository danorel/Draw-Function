import drawer.DrawFrame;

public class App {
    public static void main(String[] args) {
        DrawFrame frame = new DrawFrame();
        frame
                .draw("f(x) = sqrt((x^3)/(a-x))")
                .display();
    }
}
