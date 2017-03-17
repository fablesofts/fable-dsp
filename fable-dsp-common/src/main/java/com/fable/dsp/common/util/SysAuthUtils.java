package com.fable.dsp.common.util;

import java.io.File;

/**
 * 工具.
 * 
 * @author Yi Hong Wei
 */
public final class SysAuthUtils {

	/**
	 * 公钥签名.
	 */
	public static final byte[] PUB_KEY_DIGEST = new byte[] { (byte) 0x48,
			(byte) 0xF8, (byte) 0xE2, (byte) 0x88, (byte) 0xBC, (byte) 0x6B,
			(byte) 0x93, (byte) 0x71, (byte) 0x54, (byte) 0x03, (byte) 0xF6,
			(byte) 0x0B, (byte) 0x33, (byte) 0x76, (byte) 0xAA, (byte) 0xE6,
			(byte) 0x0D, (byte) 0x5E, (byte) 0x7C, (byte) 0x37, (byte) 0x3A,
			(byte) 0x40, (byte) 0x99, (byte) 0xD9, (byte) 0x87, (byte) 0x5B,
			(byte) 0x1C, (byte) 0x1A, (byte) 0x78, (byte) 0xFD, (byte) 0x56,
			(byte) 0x34, (byte) 0x4E, (byte) 0x1C, (byte) 0xE0, (byte) 0x25,
			(byte) 0x8A, (byte) 0xF1, (byte) 0x05, (byte) 0x09, (byte) 0x90,
			(byte) 0xFD, (byte) 0x07, (byte) 0xDC, (byte) 0x6D, (byte) 0x5B,
			(byte) 0x7D, (byte) 0xDC, (byte) 0xD2, (byte) 0xA0, (byte) 0xD6,
			(byte) 0x4C, (byte) 0xCF, (byte) 0x22, (byte) 0xCC, (byte) 0xE3,
			(byte) 0x08, (byte) 0x3A, (byte) 0xDF, (byte) 0x86, (byte) 0x86,
			(byte) 0xE3, (byte) 0x30, (byte) 0xC2, };
	/**
	 * 被授权单位关键字
	 */
	public static final byte[] LICENCE_NAME_KEY = new byte[] { (byte) 1,
			(byte) 2, (byte) 3, (byte) 4, (byte) 4, (byte) 3, (byte) 2,
			(byte) 1, };

	/**
	 * 文件存储位置
	 */
	public static final String SYSAUTH_PATH = File.separator + "sysauth"
			+ File.separator;

	/**
	 * 特征文件名
	 */
	public static final String CHARACTER_FILE = "FableETLCharacter";
	/**
	 * 授权文件名.
	 */
	public static final String LICENCE_FILE = "FableETLLicence";
	/**
	 * pbe算法参数.
	 */
	public static final int PBE_ITER_COUNT = 1000;
	/**
	 * 公钥文件.
	 */
	public static final String PUB_KEY_FILE = "PubKey";
	/**
	 * 私钥文件.
	 */
	public static final String PRI_KEY_FILE = "PriKey";
	/**
	 * 私钥加密算法.
	 */
	public static final String PRI_KEY_ALG = "PBEWITHMD5andDES";
	/**
	 * 数字摘要算法.
	 */
	public static final String DIGEST_ALG = "SHA-512";
	/**
	 * 加密密钥算法.
	 */
	public static final String CIPHER_KEY_ALG = "AES";
	/**
	 * 加密算法.
	 */
	public static final String CIPHER_ALG = "AES/CBC/PKCS5Padding";
	private static final String WINDOWS = "windows";
	private static final String LINUX = "linux";
	/**
	 * 取特征值命令.
	 */
	public static String[] CHARACTER_CMD;
	static {
		final String strOsName = System.getProperty("os.name", "unknown")
				.toLowerCase();
		if (strOsName.startsWith(SysAuthUtils.WINDOWS))
			SysAuthUtils.CHARACTER_CMD = new String[] { "cmd", "/c", "getmac", };
		else if (strOsName.startsWith(SysAuthUtils.LINUX))
			SysAuthUtils.CHARACTER_CMD = new String[] { "/bin/sh", "-c",
					"dmidecode -s system-serial-number", };
		else
			SysAuthUtils.CHARACTER_CMD = null;
	}

	private SysAuthUtils() {
	}

	/**
	 * @return the cHARACTER_CMD
	 */
	public static String[] getCHARACTER_CMD() {
		return SysAuthUtils.CHARACTER_CMD;
	}

	/**
	 * int转换为byte数组.
	 * 
	 * @param src
	 *            src
	 * @return byte数组
	 */
	public static byte[] int2byte(int src) {
		final byte[] result = new byte[4];
		int iMove;
		for (int i = 0; i < 4; i++) {
			iMove = 8 * (3 - i);
			result[i] = (byte) (src >> iMove);
			src -= result[i] << iMove;
		}
		return result;
	}

	/**
	 * 把byte数组转换为十六进制字符.
	 * 
	 * @param src
	 *            src
	 * @param pos
	 *            pos
	 * @param length
	 *            length
	 * @param sep
	 *            字符间的分隔符
	 * @return 十六进制输出
	 */
	public static String bytes2HexString(final byte[] src, final int pos,
			final int length, String sep) {
		if (sep == null)
			sep = " ";
		final StringBuffer strBuf = new StringBuffer();
		int b;
		for (int i = 0; i < length; i++) {
			b = SysAuthUtils.byte2UnsignedInt(src[i + pos]);
			strBuf.append((b < 16 ? "0" : "")
					+ Integer.toHexString(b).toUpperCase() + sep);
		}
		return strBuf.toString();
	}

	/**
	 * byte转换为int.
	 * 
	 * @param src
	 *            src
	 * @return int
	 */
	public static int byte2UnsignedInt(final byte src) {
		int iResult = src;
		if (iResult < 0)
			iResult += 256;
		return iResult;
	}
}
