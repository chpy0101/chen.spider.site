package chen.site.dao.mysql.idao;

import chen.spider.pojo.stockDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by chpy on 18/3/20.
 */
@Repository
@Table(name = "stockdetail")
public interface stockDetailRepository extends CrudRepository<stockDetail, Long> {
    @Query(value = "select * from spider.stockdetail s where s.stockCode=:code and TO_DAYS(s.date)=TO_DAYS(:time) order by s.date", nativeQuery = true)
    List<stockDetail> getStockTrendAfterTime(@Param("code") String stockCode, @Param("time") String time);

}
