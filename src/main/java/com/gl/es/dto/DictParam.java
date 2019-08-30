package com.gl.es.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DictParam {


    @ApiModelProperty(value = "1: 扩展词库 2:停词库")
    private Byte type;

    private String key;

    private Byte status;

}
