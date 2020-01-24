package fvarrui.filecast;

import java.util.List;

import fvarrui.filecast.net.FileCast;
import fvarrui.filecast.net.Host;

public class SenderTest {

	public static void main(String[] args) {
		List<Host> receivers = FileCast.waitingForReceivers();
		System.out.println(receivers);
	}

}
