package chen.site.dao.mysql.idao;

import chen.spider.pojo.yybBuyStock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name = "yybBuyStock")
@Qualifier("yybBuyStockRepository")
public interface yybBuyStockRepository extends CrudRepository<yybBuyStock, Long> {

    @Query(value = "select * from spider.yybbuystock s where TO_DAYS(s.buyDate)=TO_DAYS(?1)", nativeQuery = true)
    List<yybBuyStock> findStockBuyTime(@Param("time") String time);

}
