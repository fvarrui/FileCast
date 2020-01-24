package fvarrui.filecast.net.packets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class Packet {

	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	private String address;

	public Packet() {}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public static Packet fromString(String string) {
		try {
			int index = string.indexOf("\n");
			String clazz = string.substring(0, index);
			String json = string.substring(index + 1, string.length());
			return (Packet) gson.fromJson(json, Class.forName(clazz));
		} catch (JsonSyntaxException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String toString() {
		String clazz = getClass().getName();
		String json = gson.toJson(this);
		return clazz + "\n" + json;
	}
	
}
