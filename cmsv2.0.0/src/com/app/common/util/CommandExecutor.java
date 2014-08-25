package com.app.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CommandExecutor {

	/**
	 * 执行cmd命令
	 * @param commend
	 * @throws IOException
	 */
	public static void exec(List<String> commend) throws IOException {
		if( commend != null && commend.size() > 0 ) {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			Process process = builder.start();
			
			InputStream errorStream = process.getErrorStream();
			InputStream inputStream = process.getInputStream();
			loadInputStream(errorStream);
			loadInputStream(inputStream);
			
		}
		
	}
	
	public static void loadInputStream(InputStream is) throws IOException {
		if( is != null ) {
			InputStreamReader inputReader = new InputStreamReader(is);
			BufferedReader bufferedReader = new BufferedReader(inputReader);
			
			String line = null;
			StringBuffer buffer = new StringBuffer();
			while( (line = bufferedReader.readLine()) != null ) {
				buffer.append( line );
			}
	
			if( bufferedReader != null ) {
				bufferedReader.close();
			}
			if( inputReader != null ) {
				inputReader.close();
			}
			if( is != null ) {
				is.close();
			}
		}
	}

}
