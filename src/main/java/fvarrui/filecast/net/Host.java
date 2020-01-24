package fvarrui.filecast.net;

import java.net.InetAddress;

public class Host {
	private InetAddress address;

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Host [address=" + address + "]";
	}

}
