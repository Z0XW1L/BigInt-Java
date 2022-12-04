package com.z0xw1l.rsamin.server.bigint;

public class BigIntSettings {
	private int base = 1000000000;
	private int baseDigits = 9;
	private int arraySize = 1;
	public BigIntSettings() {
		
	}
	public BigIntSettings(int base, int baseDigits) {
		super();
		this.base = base;
		this.baseDigits = baseDigits;
	}
	public BigIntSettings(int base, int baseDigits, int arraySize) {
		super();
		this.base = base;
		this.baseDigits = baseDigits;
		this.arraySize = arraySize;
	}
	public int getBase() {
		return base;
	}
	public void setBase(int base) {
		this.base = base;
	}
	public int getBaseDigits() {
		return baseDigits;
	}
	public void setBaseDigits(int baseDigits) {
		this.baseDigits = baseDigits;
	}
	public int getArraySize() {
		return arraySize;
	}
	public void setArraySize(int arraySize) {
		this.arraySize = arraySize;
	}
}
