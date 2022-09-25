package com.Test;

import org.testng.annotations.Test;

import base.Base;
import base.KeywordLogic;

public class KeyWordTest extends Base {
	@Test
	
	public void LoginTest() {
		
		keywordLogic=new KeywordLogic();
		keywordLogic.startExcecution("Sheet1");
		
		
		
		
	}

}
