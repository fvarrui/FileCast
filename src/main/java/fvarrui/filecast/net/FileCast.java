package fvarrui.filecast.net;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import fvarrui.filecast.net.packets.HelloPacket;
import fvarrui.filecast.net.packets.Packet;
import fvarrui.filecast.net.utils.ThreadUtils;
import fvarrui.filecast.net.utils.UDPUtils;

public class FileCast {
	
	public static final int SENDER_PORT = 4445;
	public static final int RECEIVER_PORT = 4446;

	public static void helloSender() throws IOException {
		UDPUtils.broadcast(new HelloPacket(), SENDER_PORT);
	}

	public static void helloReceiver(InetAddress address) throws IOException {
		UDPUtils.send(new HelloPacket(), address, RECEIVER_PORT);
	}
	
	public static List<Host> findSenders() throws IOException {
		List<Host> senders = new ArrayList<>();

		// espera respuesta de los senders
		Thread t = ThreadUtils.start(() -> {
			System.out.println("Waiting for senders...");			
			try { 
				while (true) {
					
					// espera un pasquete del sender
					Packet packet = UDPUtils.receive(RECEIVER_PORT);
					Host sender = new Host();
					sender.setAddress(InetAddress.getByName(packet.getAddress()));
					senders.add(sender);
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		// envía un mensaje broadcast a todos los senders
		helloSender();

		// tras un tiempo de espera, devuelve los senders que han contestado
		ThreadUtils.sleep(5000L);
		t.stop();

		return senders;
	}

	
	public static List<Host> waitingForReceivers() {
		List<Host> receivers = new ArrayList<>();
		
		Thread t = ThreadUtils.start(() -> {
			System.out.println("Waiting for receivers...");			
			try { 
				while (true) {
					
					// espera la recepción de un paquete del receiver 
					Packet packet = UDPUtils.receive(SENDER_PORT);
					Host receiver = new Host();
					receiver.setAddress(InetAddress.getByName(packet.getAddress()));
					receivers.add(receiver);
					
					// envía un mensaje al receiver
					helloReceiver(receiver.getAddress());

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		// tras un tiempo de espera, devuelve los receivers que han contestado
		ThreadUtils.sleep(60000L);
		t.stop();
		
		return receivers;
	}

}
