package com.qsmng.security.linux.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ServerService {

	private static final Logger Log = LogManager.getLogger(ServerService.class);

	public void shutdown() throws InterruptedException, IOException {
		this.executeCmd(Constants.SHUTDOWN_CMD);
	}

	public void restart() throws InterruptedException, IOException {
		this.executeCmd(Constants.REBOOT_CMD);

	}

	public boolean executeCmd(String cmd) throws InterruptedException, IOException {
		if(StringUtils.isEmpty(cmd))
			return false;
		
		String homeDirectory = System.getProperty("user.home");

		Process	process = Runtime.getRuntime().exec(String.format(cmd, homeDirectory));

		StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), Log::info);
		Executors.newSingleThreadExecutor().submit(streamGobbler);
		int exitCode = process.waitFor();

		return exitCode == 0;
	}

	private static class StreamGobbler implements Runnable {
		private InputStream inputStream;
		private Consumer<String> consumer;

		public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
			this.inputStream = inputStream;
			this.consumer = consumer;
		}

		@Override
		public void run() {
			new BufferedReader(new InputStreamReader(inputStream)).lines().forEach(consumer);
		}
	}
}
