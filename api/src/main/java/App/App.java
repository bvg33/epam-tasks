package App;

import logic.Utils;
//todo build from console,gradle build lifecycle
public class App {
    public static void main(String[] args) {
        Utils utils = new Utils();
        System.out.println(utils.isAllPositiveNumbers("12", "79"));
    }
}
