package com.common.component.parser_eom.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class CollectionUtilsTest {
	
	@Test
	public void testCollectionUtils() {
		List<String> stringList = new ArrayList<String>();
		stringList.add(new String("test"));
		List<String> emptyList = new ArrayList<String>();
		Object[] objArray = new Object[10];
		Object[] objArray2 = null;
		objArray[0] = new Object();
		Assert.assertEquals(CollectionUtils.isEmpty(stringList), false);
		Assert.assertEquals(CollectionUtils.isEmpty(emptyList),true);
		Assert.assertEquals(CollectionUtils.isEmpty(objArray), false);
		Assert.assertEquals(CollectionUtils.isEmpty(objArray2), true);		
	}

}
