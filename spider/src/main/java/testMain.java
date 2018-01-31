import controller.eastmoneyChartsController;
import controller.testController;

/**
 * Created by chpy on 18/1/27.
 */
public class testMain {

    public static void main(String[] args) {
        eastmoneyChartsController controller = new eastmoneyChartsController(false);

        controller.getData();
    }
}
