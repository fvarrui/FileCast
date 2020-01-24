package fvarrui.filecast;

import java.io.IOException;
import java.util.List;

import fvarrui.filecast.net.FileCast;
import fvarrui.filecast.net.Host;

public class ReceiverTest {

	public static void main(String[] args) throws IOException {
		List<Host> senders = FileCast.findSenders();
		System.out.println(senders);
	}

}
