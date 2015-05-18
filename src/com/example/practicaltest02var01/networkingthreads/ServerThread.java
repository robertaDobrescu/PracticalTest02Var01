package com.example.practicaltest02var01.networkingthreads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.http.client.ClientProtocolException;

import com.example.practicaltest02var01.utilities.Constants;
import com.example.practicaltest02var01.utilities.WeatherInfo;

import android.util.Log;

public class ServerThread extends Thread {
	
	private int          port         = 0;
	private ServerSocket serverSocket = null;
	
	private WeatherInfo info = null;
	
	
	public ServerThread(int port) {
		this.port = port;
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException ioException) {
			Log.e(Constants.TAG, "An exception has occurred: " + ioException.getMessage());
			if (Constants.DEBUG) {
				ioException.printStackTrace();
			}
		}
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setServerSocker(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	
	@Override
	public void run() {
		try {		
			while (!Thread.currentThread().isInterrupted()) {
				Log.i(Constants.TAG, "[SERVER] Waiting for a connection...");
				Socket socket = serverSocket.accept();
				Log.i(Constants.TAG, "[SERVER] A connection request was received from " + socket.getInetAddress() + ":" + socket.getLocalPort());
				CommunicationThread communicationThread = new CommunicationThread(this, socket);
				communicationThread.start();
			}			
		} catch (ClientProtocolException clientProtocolException) {
			Log.e(Constants.TAG, "An exception has occurred: " + clientProtocolException.getMessage());
			if (Constants.DEBUG) {
				clientProtocolException.printStackTrace();
			}			
		} catch (IOException ioException) {
			Log.e(Constants.TAG, "An exception has occurred: " + ioException.getMessage());
			if (Constants.DEBUG) {
				ioException.printStackTrace();
			}
		}
	}
	
	public void stopThread() {
		if (serverSocket != null) {
			interrupt();
			try {
				serverSocket.close();
			} catch (IOException ioException) {
				Log.e(Constants.TAG, "An exception has occurred: " + ioException.getMessage());
				if (Constants.DEBUG) {
					ioException.printStackTrace();
				}				
			}
		}
	}

	public WeatherInfo getInfo() {
		return info;
	}

	public void setInfo(WeatherInfo info) {
		this.info = info;
	}

}
