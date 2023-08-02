package com.druidexample.producer;

import java.util.ArrayList;

public class DeviceRegistary {
	
	private ArrayList<Integer> DeviceIds;
	
	public DeviceRegistary(int firstDeviceId, int lastDeviceId) {
		this.DeviceIds = new ArrayList<Integer>();
		for(int i = firstDeviceId; i < lastDeviceId; i++) {
				this.DeviceIds.add(i);
		} 
	}
	
	public ArrayList<Integer> getDeviceIds() {
		return this.DeviceIds;
				
	}

}
