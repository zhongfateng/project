package com.nbw.common;

public class Authorization {
	private static final int maxActionNum = 60;

	/**
	 * 根据权限数值和操作数值判断是否有操作权限
	 * 
	 * @param authorizedNum
	 *            权限数值
	 * @param actionNum
	 *            操作数值
	 * @return true|false
	 */
	public static boolean isPossess(long authorizedNum, int actionNum) {
		if (actionNum > maxActionNum) {
			return false;
		}
		long i = (long) 1 << actionNum;
		return (authorizedNum & i) == i;
	}

	/**
	 * 根据操作数值计算权限数值，操作数值不能大于60。
	 * 
	 * @param actionNum
	 *            操作数值
	 * @exception RuntimeException操作权限值过大
	 * @return long 权限数值
	 */
	public static long getAuthorizedNum(int... actionNum) {
		long authorizedNum = 0;
		for (int i = 0; i < actionNum.length; i++) {
			if (actionNum[i] > maxActionNum) {
				throw new RuntimeException("操作权限值过大，允许最大值：" + maxActionNum);
			} else {
				authorizedNum += (long) 1 << actionNum[i];
			}
		}
		return authorizedNum;
	}
}
