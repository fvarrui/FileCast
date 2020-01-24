package fvarrui.filecast.net.utils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import fvarrui.filecast.net.packets.Packet;

public class UDPUtils {
	
	public static void broadcast(Packet packet, int port) throws IOException {
		InetAddress address = InetAddress.getByName("255.255.255.255");
		DatagramSocket socket = new DatagramSocket();
		socket.setBroadcast(true);
		byte[] buffer = packet.toString().getBytes();
		DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, address, port);
		socket.send(datagramPacket);
		socket.close();
		System.out.println("Packet broadcasted to port " + port + ": " + packet);
	}
	
	public static void send(Packet packet, InetAddress address, int port) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		byte[] buffer = packet.toString().getBytes();
		DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, address, port);
		socket.send(datagramPacket);
		socket.close();
		System.out.println("Packet sent to " + address + " on port " + port + ": " + packet);
	}
	
	public static Packet receive(int port) throws IOException {
		DatagramSocket socket = new DatagramSocket(port);
		byte [] buffer = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		socket.receive(packet);
		String message = new String(packet.getData(), 0, packet.getLength());
		Packet receivedPacket = Packet.fromString(message);
		receivedPacket.setAddress(packet.getAddress().toString().replaceAll("/", ""));
		socket.close();
		System.out.println("Packet received: " + receivedPacket);
		return receivedPacket;
	}

}
