package chen.site.dao.idao;

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

	@Query("select s from yybBuyStock s where to_days/(s.buyDate)=to_days/(:time)")
	List<yybBuyStock> findStockBuyTime(@Param("time") String time);
}
