package fvarrui.filecast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Sender {

	public static void main(String[] args) throws IOException {
		System.out.println(NetUtils.listAllBroadcastAddresses());
		broadcast("Hello", InetAddress.getByName("255.255.255.255"));
	}

	public static void broadcast(String message, InetAddress address) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		socket.setBroadcast(true);

		byte[] buffer = message.getBytes();

		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 4445);
		socket.send(packet);
		socket.close();
		
		System.out.println("Message '" + message + "' sent");
	}
	
}
