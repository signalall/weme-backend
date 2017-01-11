package cn.seu.weme.dao;

import cn.seu.weme.entity.FoodCard;
import cn.seu.weme.entity.Report;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Visen (zhvisen@gmail.com) on 2017/1/5.
 */
public interface ReportDao extends CrudRepository<Report, Long> {
}
