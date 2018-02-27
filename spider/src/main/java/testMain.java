import chen.spider.spiderservice.controller.eastmoneyChartsController;
import chen.spider.spiderservice.entity.eastmoney.yybIncreaseEntity;
import chen.spider.spiderservice.filter.eastmoneyChartsFilter;

import java.util.List;

/**
 * Created by chpy on 18/1/27.
 */
public class testMain {

    public static void main(String[] args) {
        eastmoneyChartsController controller = new eastmoneyChartsController(false);
        controller.addFilter(new eastmoneyChartsFilter());

        List<yybIncreaseEntity> data = controller.getData();
        System.out.print("end");
    }
}
