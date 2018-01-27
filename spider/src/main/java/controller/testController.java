package controller;

/**
 * Created by chpy on 18/1/27.
 */
public class testController extends abstractController {


    public testController(boolean enableLoop) {
        super(enableLoop);
    }

    @Override
    public Object getData() {
        return "test";
    }


}
