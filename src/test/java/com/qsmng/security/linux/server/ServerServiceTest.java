package com.qsmng.security.linux.server;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.qsmng.core.BaseTest;

public class ServerServiceTest extends BaseTest {

	@Autowired
	private ServerService serverService;
	
	@Test
	public void testInvalidExecuteCmd() throws InterruptedException, IOException {
		boolean isValid = serverService.executeCmd(null);
		
		Assert.assertFalse(isValid);
		
	}


}
