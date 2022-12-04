package com.z0xw1l.rsamin.server.bigint;

public class BigIntL {
	
	public final BigIntSettings settings;
	public int[] values;
	
	public BigIntL(BigIntSettings settings) {
		this.settings = settings;
	}
	public BigIntL(BigIntSettings settings, int size) {
		this.settings = settings;
		this.values = new int[size];
	}
	public BigIntL(BigIntSettings settings, int[] values) {
		this.settings = settings;
		this.values = values.clone();
	}
	public BigIntL(BigIntL x) {
		this.settings = x.settings;
		this.values = x.values.clone();
	}
	public void setValue(String val) {
		int len = val.length();
		int digitsLength = (len - 1) / settings.getBaseDigits() + 1;
		int head = len % settings.getBaseDigits();
		for (int i = 0; i < digitsLength; ++i) {
			String block = val.substring(Math.max(head + (i - 1) * settings.getBaseDigits(), 0),
					head + i * settings.getBaseDigits());
			this.values[digitsLength - i - 1] = Integer.parseInt(block);
		}
	}
	public static BigIntL add(BigIntL x, BigIntL y) { return new BigIntL(x.settings, BigUtils.add(x.values, y.values, x.settings)); }
	public static BigIntL substract(BigIntL x, BigIntL y) { return new BigIntL(x.settings, BigUtils.subtract(x.values, y.values, x.settings)); }
//	public static BigIntL mod(BigIntL x, BigIntL y) { return new BigIntL(x.settings, BigUtils.mod(x.values, y.values, x.settings)); }
	
}
