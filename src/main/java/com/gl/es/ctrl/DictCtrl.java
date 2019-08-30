package com.gl.es.ctrl;

import com.gl.common.CommonResult;
import com.gl.es.dto.DictParam;
import com.gl.es.service.DictService;
import com.gl.es.util.EsConstant;
import com.gl.mbg.model.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.gl.es.util.EsConstant.EXT_DICT;

@Api(tags = "001、DictCtrl", description = "词库控制器")
@Controller
@RequestMapping("/api/dict")
public class DictCtrl {

    private static final Logger logger = LoggerFactory.getLogger(DictCtrl.class);


    @Autowired
    private DictService dictService;

    @ApiOperation("001、查询扩展词库")
    @RequestMapping(value = {"/ext"}, method = RequestMethod.GET)
    @ResponseBody
    public String  loadExtDict(HttpServletRequest request, HttpServletResponse response){
        return this.loadDict(request,response, EXT_DICT);
    }

    @ApiOperation("002、查询停词库")
    @RequestMapping(value = {"/stop"}, method = RequestMethod.GET)
    @ResponseBody
    public String  loadStopDict(HttpServletRequest request, HttpServletResponse response){
        return this.loadDict(request,response, EsConstant.STOP_DICT);
    }

    @ApiOperation("003、添加词库")
    @RequestMapping(value = {""}, method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestBody DictParam dictParam){

        Dict dict = new Dict();
        BeanUtils.copyProperties(dictParam,dict);
        int cnt = dictService.add(dict);
        return CommonResult.success(cnt);
    }

    @ApiOperation("004、删除词库")
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult del(@PathVariable  Long id){
        int cnt =  dictService.delete(id);
        return CommonResult.success(cnt);
    }

    @ApiOperation("006、编辑词库")
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult edit(@PathVariable  Long id,@RequestBody DictParam dictParam){
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictParam,dict);
        int cnt =  dictService.edit(id,dict);
        return CommonResult.success(cnt);
    }



    private String loadDict(HttpServletRequest request, HttpServletResponse response,Byte type){

            String result = "";
            StringBuilder sb = new StringBuilder();
            //获取所有分词，这里可以改进使用缓存等。
            List<Dict> dictList = dictService.queryAllDict(type);

            String eTag = request.getHeader("If-None-Match");

            Long modified= request.getDateHeader("If-Modified-Since");

            //设置头
            if(null == modified || -1 == modified) {
                //如果没有，则使用当前时间
                modified = System.currentTimeMillis();
            }
            // 设置头信息。
            String oldEtag = dictList.size()+ "";

            response.setDateHeader("Last-Modified", Long.valueOf(modified));

            response.setHeader("ETags", dictList.size() + "");

            if(!oldEtag.equals(eTag)) {
                //拼装结果
                for(Dict dict : dictList) {
                    //分词之间以换行符连接
                    if(!StringUtils.isEmpty(sb.toString())) {
                        sb.append("\r\n");
                    }
                    sb.append(dict.getKey());

                }
                //result = new String(result.getBytes("ISO8859-1"), "UTF-8");
                result = sb.toString();
                //更新时间
                response.setDateHeader("Last-Modified", System.currentTimeMillis());
            }

            return result;
        }

}
