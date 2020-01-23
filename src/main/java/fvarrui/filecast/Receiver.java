package fvarrui.filecast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver {
	private static DatagramSocket socket = null;

	public static void main(String[] args) throws IOException {
		receive();
	}

	public static void receive() throws IOException {
		
		System.out.println("Receiver waiting...");
		
		socket = new DatagramSocket(4445);
		socket.setBroadcast(true);
		
		byte [] buffer = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		socket.receive(packet);
		String message = new String(packet.getData(), 0, packet.getLength());
		socket.close();
		
		System.out.println("Received message: '" + message + "'");		
	}


}
