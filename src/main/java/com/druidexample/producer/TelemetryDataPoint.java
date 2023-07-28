package com.druidexample.producer;

public class TelemetryDataPoint {
	private String time;
	
	private int DeviceId;
	
	private double TempValue;
	
	private double PressureValue;
	
	TelemetryDataPoint(int DeviceId) {
		this.DeviceId = DeviceId;
	}
		
	public double getPressureValue() {
		return PressureValue;
	}
	public void setPressureValue(double pressureValue) {
		PressureValue = pressureValue;
	}
	public double getTempValue() {
		return TempValue;
	}
	public void setTempValue(double tempValue) {
		TempValue = tempValue;
	}
	public int getDeviceId() {
		return DeviceId;
	}
	public void setDeviceId(int deviceId) {
		this.DeviceId = deviceId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
