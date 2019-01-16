package com.dlh.dubbo.group.merge.impl;

import com.dlh.dubbo.group.merge.api.MergeService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/15 21:53
 */
public class MergeServiceImpl2 implements MergeService {
    @Override
    public List<String> mergeResult() {
        List<String> menus = new ArrayList<>();
        menus.add("group-2.1");
        menus.add("group-2.2");
        return menus;
    }
}
