package com.citybike.backend;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.citybike.backend.util.Helper;
import com.citybike.backend.util.TopListItem;

@ActiveProfiles("test")
public class HelperTests {

    @Test
    void testObjectToTopListItem() throws Exception {
        List<Object[]> objectArrayList = new ArrayList<Object[]>();
        objectArrayList.add(new Object[]{123,"Asema",6L});
        objectArrayList.add(new Object[]{321,"Pysäkki",4L});
        List<TopListItem> topList = new ArrayList<TopListItem>();
        topList.add(new TopListItem(123, "Asema", 6));
        topList.add(new TopListItem(321, "Pysäkki", 4));
        List<TopListItem> topListFromObjectArray = Helper.topListObjectArrayToTopListItem(objectArrayList);
        assertEquals(topList, topListFromObjectArray);
    }
    
}
