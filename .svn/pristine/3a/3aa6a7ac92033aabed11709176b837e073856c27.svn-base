package com.fable.dsp.service.sysauth;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fable.dsp.common.util.SysAuthExecCmd;
import com.fable.dsp.common.util.SysAuthUtils;

public class FableLicenceFilter {
	// 验证授权文件公钥
	private static boolean AUTHORIZATION;
	// 证书有效期
	private static long REMINDDATE = 0;
	// 授权设备特征码
	private static String AUTH_FACILITY;
	// 当前设备特征码
	private static String CURRENT_FACILITY;

	private static final Logger LOGGER = LoggerFactory.getLogger(FableLicenceFilter.class);

	//验证授权入口
	public static boolean checkLicence(HttpServletRequest request) {
		LOGGER.info("正在验证系统授权信息");
		String contextPath = request.getSession().getServletContext().getRealPath("/");
		if (!new File(contextPath + SysAuthUtils.SYSAUTH_PATH + SysAuthUtils.LICENCE_FILE).exists()) {
			LOGGER.error("系统尚未获得授权");
			return false;
		}
		try {
			final MessageDigest messageDigest = MessageDigest.getInstance(SysAuthUtils.DIGEST_ALG);
			final int iDigestLength = messageDigest.getDigestLength();
			final byte[] licence = decryptLicence(getPubKey(contextPath), contextPath);
			final DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String EXPIREDATE = new String(licence, 0, 8);
			byte[] tempArray = new byte[8];
			System.arraycopy(licence, 8, tempArray, 0, 8);

			Date date1 = df.parse(EXPIREDATE);
			Date date2 = df.parse(df.format(new Date()));
			REMINDDATE = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
			
			if (Arrays.equals(SysAuthUtils.LICENCE_NAME_KEY, tempArray)) {
				AUTH_FACILITY = SysAuthUtils.bytes2HexString(licence,
						8 + 8 + 8 + 100, iDigestLength, "");
				LOGGER.info("授权设备特征码：" + AUTH_FACILITY);
			} else {
				AUTH_FACILITY = SysAuthUtils.bytes2HexString(licence, 8 + 8 + 100,
						iDigestLength, "");
				LOGGER.info("授权设备特征码：" + AUTH_FACILITY);
			}
			final ByteArrayOutputStream out = new ByteArrayOutputStream();
			new SysAuthExecCmd().exec(SysAuthUtils.getCHARACTER_CMD(), null,
					null, out);
			messageDigest.update(out.toByteArray());
			final byte[] digest = messageDigest.digest();
			CURRENT_FACILITY = SysAuthUtils.bytes2HexString(digest, 0, digest.length, "");
			LOGGER.info("当前设备特征码：" + CURRENT_FACILITY);
			if (REMINDDATE >= -30) {
				if (AUTHORIZATION && AUTH_FACILITY.equals(CURRENT_FACILITY)) {
					return true;
				} else {
					LOGGER.error("授权文件不正确");
					return false;
				}
			} else {
				LOGGER.error("授权已经过期");
				return false;
			}
		} catch (Exception e) {
			LOGGER.error("系统授权错误", e);
		}
		LOGGER.error("系统尚未获得授权");
		return false;
	}

	private static byte[] decryptLicence(final PublicKey publicKey, String contextPath) throws Exception {
		final Cipher keyCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		keyCipher.init(Cipher.DECRYPT_MODE, publicKey);
		final DataInputStream dis = new DataInputStream(new FileInputStream(new File(contextPath
				+ SysAuthUtils.SYSAUTH_PATH + SysAuthUtils.LICENCE_FILE)));
		final byte[] encryptedKeyBytes = new byte[dis.readInt()];
		dis.readFully(encryptedKeyBytes);
		final byte[] sessionKeyBytes = keyCipher.doFinal(encryptedKeyBytes);
		final SecretKey sessionKey = new SecretKeySpec(sessionKeyBytes, SysAuthUtils.CIPHER_KEY_ALG);
		final byte[] iv = new byte[16];
		dis.read(iv);
		final IvParameterSpec spec = new IvParameterSpec(iv);
		final Cipher cipher = Cipher.getInstance(SysAuthUtils.CIPHER_ALG);
		cipher.init(Cipher.DECRYPT_MODE, sessionKey, spec);
		final CipherInputStream in = new CipherInputStream(dis, cipher);
		int theByte = 0;
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		while ((theByte = in.read()) != -1)
			out.write(theByte);
		return out.toByteArray();
	}

	/**
	 * 获取公钥文件
	 * 
	 * @param contextPath
	 *            项目的全路径
	 */
	private static PublicKey getPubKey(String contentPath) throws Exception {
		final FileInputStream fis = new FileInputStream(contentPath + SysAuthUtils.SYSAUTH_PATH
				+ SysAuthUtils.PUB_KEY_FILE);
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int theByte = 0;
		while ((theByte = fis.read()) != -1)
			baos.write(theByte);
		fis.close();
		final byte[] keyBytes = baos.toByteArray();
		baos.close();
		AUTHORIZATION = checkPubKey(keyBytes);
		final X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(keySpec);
	}

	/**
	 * 验证公钥文件
	 * 
	 * @param keyBytes
	 *            公钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static boolean checkPubKey(final byte[] keyBytes) throws NoSuchAlgorithmException {
		final MessageDigest messageDigest = MessageDigest.getInstance(SysAuthUtils.DIGEST_ALG);
		messageDigest.update(keyBytes);
		final byte[] keyDigest = messageDigest.digest();
		if (Arrays.equals(keyDigest, SysAuthUtils.PUB_KEY_DIGEST)) {
			return AUTHORIZATION = true;
		}
		return false;
	}
}
