package com.fly4j.common.core.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by XianRui on 2020-03-04 11:08
 **/
@Data
@Accessors(chain = true)
public class FileInfo {

    private String filePath;

    private String thumbImagePath;

}
