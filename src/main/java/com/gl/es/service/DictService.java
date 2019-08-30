package com.gl.es.service;

import com.gl.mbg.model.Dict;

import java.util.List;

public interface DictService {

    public List<Dict> queryAllDict(Byte type);

    public int add(Dict dict);

    public int delete(Long  id);

    public int edit(Long  id,Dict dict);

}
