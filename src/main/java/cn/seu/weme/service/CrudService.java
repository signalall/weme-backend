package cn.seu.weme.service;

import cn.seu.weme.common.result.ResultInfo;

/**
 * Created by LCN on 2016-12-18.
 */
public interface CrudService<T> {
    public ResultInfo addOne(T data);

    public ResultInfo getOneById(Long id);

    public ResultInfo updateOne(T data);

    public ResultInfo deleteOneById(Long id);

    public ResultInfo getAllData();
}
