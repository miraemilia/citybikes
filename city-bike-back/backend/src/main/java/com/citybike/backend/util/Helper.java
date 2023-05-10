package com.citybike.backend.util;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static List<TopListItem> topListObjectArrayToTopListItem(List<Object[]> topObjectList) {
        List<TopListItem> topList = new ArrayList<TopListItem>();
        for (Object[] resultObject : topObjectList) {
            Integer id = (Integer) resultObject[0];
            Long countLong = (Long) resultObject[1];
            Integer count = countLong.intValue();
            TopListItem topListItem = new TopListItem(id, count);
            topList.add(topListItem);
        }
        return topList;
    }
    
}
