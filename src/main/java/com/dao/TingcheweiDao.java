package com.dao;

import com.entity.TingcheweiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TingcheweiView;

/**
 * 停车位 Dao 接口
 *
 * @author 
 */
public interface TingcheweiDao extends BaseMapper<TingcheweiEntity> {

   List<TingcheweiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
