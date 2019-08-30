package com.gl.es.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.gl.es.ctrl.DictCtrl;
import com.gl.es.service.DictService;
import com.gl.mbg.mapper.DictMapper;
import com.gl.mbg.model.Dict;
import com.gl.mbg.model.DictExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gl.es.util.EsConstant.STATUS_ENABLE;

@Service
@CacheConfig(cacheNames = "es:dict")
public class DictServiceImpl implements DictService {
    private static final Logger logger = LoggerFactory.getLogger(DictServiceImpl.class);


    @Autowired
    private DictMapper dictMapper;

    @Cacheable(key = "#type")
    @Override
    public List<Dict> queryAllDict(Byte type){
        String  id = RandomUtil.randomString(32);
        logger.info("{}查询词库：{} 开始",id,type);
        DictExample example = new DictExample();
        example.createCriteria().andTypeEqualTo(type).andStatusEqualTo(STATUS_ENABLE);
        List<Dict> dictList = dictMapper.selectByExample(example);
        logger.info("{}查询词库：{} 结束",id,type);
        return dictList;
    }
    @Override
    @CacheEvict(allEntries = true,beforeInvocation=true)
    public int add(Dict dict){
       return dictMapper.insert(dict);
    }
    @Override
    @CacheEvict(allEntries = true,beforeInvocation=true)
    public int delete(Long  id){
        return dictMapper.deleteByPrimaryKey(id);
    }
    @Override
    @CacheEvict(allEntries = true,beforeInvocation=true)
    public int edit(Long  id,Dict dict){
        Dict dictForUpdate = new Dict();
        BeanUtils.copyProperties(dict,dictForUpdate);
        dictForUpdate.setId(id);
        return dictMapper.updateByPrimaryKeySelective(dictForUpdate);
    }


}
