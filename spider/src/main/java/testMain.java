import controller.testController;

/**
 * Created by chpy on 18/1/27.
 */
public class testMain {

    public static void main(String[] args) {
        testController controller = new testController(false);

        controller.set_callBackMapper((s) -> {
            return 1;
        });

        try {
            System.out.print(controller.getDataThenMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
