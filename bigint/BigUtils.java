package com.z0xw1l.rsamin.server.bigint;

public class BigUtils {
	final static long LONG_MASK = 0xffffffffL;
	
	public static int[] add(int[] x, int[] y, final BigIntSettings settings) {
		int carry = 0;
		int[] res = x.clone();
		for (int i = 0; i < x.length; ++i) {
			int sum = carry + res[i] + y[i];
			res[i] = sum % settings.getBase();
			carry = sum / settings.getBase();
		}
		if (carry != 0) {
			for (int i = y.length; i < x.length && carry != 0; ++i) {
				int sum = carry + res[i];
				res[i] = sum % settings.getBase();
				carry = sum / settings.getBase();
			}
		}
		return res;
	}
	public static int[] subtract(final int[] x, final int[] y, final BigIntSettings settings) {
		int carry = 0;
		int[] res = x.clone();
		for(int i=0; i<res.length; ++i) {
			int sub = x[i] - (y[i]+carry);
			if(sub < 0) {
				carry = 1;
				sub = settings.getBase() + sub;
			} else {
				carry = 0;
			}
			res[i] = sub;
		}
		if(carry != 0) {
			System.out.println("substract: array was not big enough!");
		}
		return res;
	}
	public static int[] multiply(int[] x, int[] y, final BigIntSettings settings) {
		int[] result = new int[settings.getArraySize()];
		long[] temp = new long[x.length + y.length];
		for (int i = 0; i < x.length; ++i) {
			for (int j = 0; j < y.length; ++j) {
				temp[i + j] += (long) x[i] * (long) y[j];
			}
		}
		for (int i = 0; i < temp.length; ++i) {
			if (temp[i] >= settings.getBase()) {
				temp[i + 1] += temp[i] / settings.getBase();
				temp[i] = temp[i] % settings.getBase();
			}
		}
		if(vLen(temp) > settings.getArraySize()) {
			System.out.println("multiply: array was not big enough!");
		}
		for (int i = 0; i < result.length; ++i) {
			result[i] = (int) temp[i];
		}
		return result;
	}
	public static int[] divide(int[] x, int[] y, final BigIntSettings settings) {
		return null;
	}
	/**
	 * Compares two number vectors.
	 * @param x
	 * @param y
	 * @return -1: x<y, 1: x>y, 0: equal
	 */
	public static int compare(int[] x, int[] y) {
		int lenX = BigUtils.vLen(x);
		int lenY = BigUtils.vLen(y);
		if(lenX < lenY) {
			return -1;
		} else if (lenX > lenY) {
			return 1;
		}
		for (int i = 0; i < lenX; ++i) {
            int x_ = x[i];
            int y_ = y[i];
            if (x_ != y_) {
                return (x_ < y_)? -1 : 1;
            }
        }
		return 0;
	}
	public static int vLen(int[] x) {
		int len = 0;
		for(int i=0; i<x.length; ++i) {
			if(x[i]>0) len = i;
		}
		return len;
	}
	public static int vLen(long[] x) {
		int len = 0;
		for(int i=0; i<x.length; ++i) {
			if(x[i]>0) len = i;
		}
		return len;
	}
	public static int[] flipSign(int[] x) {
		int[] res = x.clone();
		res[vLen(x)] = -res[vLen(x)];
		return res;
	}
}
