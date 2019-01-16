package com.dlh.dubbo.group.merge.impl;

import com.dlh.dubbo.group.merge.api.MergeService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutao
 * @email wutao56789@gmail.com
 * @date 2019/1/15 21:38
 */
public class MergeServiceImpl implements MergeService {
    @Override
    public List<String> mergeResult() {
        List<String> menus = new ArrayList<>();
        menus.add("group-1.1");
        menus.add("group-1.2");
        return menus;
    }
}
