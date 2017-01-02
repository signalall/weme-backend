package cn.seu.weme.service;

import cn.seu.weme.common.result.ResultInfo;
import cn.seu.weme.dto.TopicVo;

/**
 * Created by LCN on 2016-12-18.
 */
public interface TopicService {

    public ResultInfo addPost(TopicVo topicVo);

    public ResultInfo getTopicById(Long id);

    public ResultInfo updateTopicById(Long id);

    public ResultInfo deleteTopicById(Long id);

    public ResultInfo getAllTopics();

}
