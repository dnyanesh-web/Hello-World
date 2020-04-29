package com.qa.hubspot.test;

import org.testng.annotations.Test;

import com.qa.hubspot.keyword.engine.KeywordEngine;

public class LoginTest {
	
	public KeywordEngine keywordEngine;
	
	@Test
	public void loginTest()
	{
		keywordEngine = new KeywordEngine();
		
		keywordEngine.startExecuttion("login");
	}

}
