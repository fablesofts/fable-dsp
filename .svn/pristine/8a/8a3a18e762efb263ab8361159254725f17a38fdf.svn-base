package com.fable.dsp.common.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 生成授权文件.
 * 
 * @author  Yi Hong Wei
 */
public final class SysAuthGenLicenceFile {

	private SysAuthGenLicenceFile() {
	}

	/**
	 * main.
	 * 
	 * @param args
	 *            args
	 */
	public static void main(final String[] args) {
		try {
			final PrivateKey priKey = getPriKey();
			final byte[] character = getCharacter(priKey);
			System.out.println(new String(character));
			genLicence(priKey, character);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private static void genLicence(final PrivateKey priKey, final byte[] character) throws Exception {
		OutputStream out = null;
		CipherOutputStream cos = null;
		try {
			final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("老版授权？");
			final String strOld = in.readLine();
			final boolean bOld = strOld != null && strOld.length() != 0;
			String strShowDate = null;
			if (!bOld) {
				System.out.println("展现授权月数（最多9999）：");
				strShowDate = in.readLine();
				if (strShowDate == null || strShowDate.length() > 4) {
					System.out.println("必须输入一个日期！");
					return;
				}
			}
			System.out.println("实际授权月数（最多9999）：");
			final String strRealDate = in.readLine();
			if (strRealDate == null || strRealDate.length() > 4) {
				System.out.println("必须输入一个日期！");
				return;
			}
			String strLicenceName = null;
			System.out.println("被授权单位名：");
			strLicenceName = in.readLine();
			if (strLicenceName == null || strLicenceName.length() == 0) {
				System.out.println("必须输入单位！");
				return;
			}
			final DateFormat df = new SimpleDateFormat("yyyyMMdd");
			final DateFormat df2 = new SimpleDateFormat("yyyy年MM月dd日");
			Calendar showDate = null;
			if (!bOld) {
				final int iMonths = Integer.parseInt(strShowDate);
				if (iMonths > 9999 || iMonths <= 0) {
					System.out.println("最多授权9999月");
					return;
				}
				showDate = Calendar.getInstance();
				showDate.set(Calendar.DATE, 1);
				showDate.add(Calendar.MONTH, iMonths + 1);
				System.out.println("授权到期日：" + df2.format(showDate.getTime()));
			}
			final int iMonths = Integer.parseInt(strRealDate);
			if (iMonths > 9999 || iMonths <= 0) {
				System.out.println("最多授权9999月");
				return;
			}
			final Calendar realDate = Calendar.getInstance();
			realDate.set(Calendar.DATE, 1);
			realDate.add(Calendar.MONTH, iMonths + 1);
			System.out.println("授权到期日：" + df2.format(realDate.getTime()));
			final Cipher keyCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			keyCipher.init(Cipher.ENCRYPT_MODE, priKey);
			final KeyGenerator sessionKeyGen = KeyGenerator.getInstance(SysAuthUtils.CIPHER_KEY_ALG);
			sessionKeyGen.init(128);
			final Key sessionKey = sessionKeyGen.generateKey();
			final byte[] encodedKeyBytes = keyCipher.doFinal(sessionKey.getEncoded());
			out = new BufferedOutputStream(new FileOutputStream(new File("d:\\" + SysAuthUtils.LICENCE_FILE)));
			out.write(SysAuthUtils.int2byte(encodedKeyBytes.length));
			out.write(encodedKeyBytes);
			final SecureRandom random = new SecureRandom();
			final byte[] iv = new byte[16];
			random.nextBytes(iv);
			out.write(iv);
			final IvParameterSpec spec = new IvParameterSpec(iv);
			final Cipher cipher = Cipher.getInstance(SysAuthUtils.CIPHER_ALG);
			cipher.init(Cipher.ENCRYPT_MODE, sessionKey, spec);
			cos = new CipherOutputStream(out, cipher);
			if (!bOld) {
				cos.write((df.format(showDate.getTime())).getBytes());
				cos.write(SysAuthUtils.LICENCE_NAME_KEY);
			}
			cos.write((df.format(realDate.getTime())).getBytes());
			if (bOld)
				cos.write(new byte[] { (byte) 8, (byte) 7, (byte) 6, (byte) 5, (byte) 4, (byte) 3, (byte) 2, (byte) 1, });
			final byte[] aLicenceName = new byte[100];
			System.arraycopy(strLicenceName.getBytes(), 0, aLicenceName, 0,
					Math.min(aLicenceName.length, strLicenceName.getBytes().length));
			cos.write(aLicenceName);
			cos.write(SysAuthGenLicenceFile.digestCharacter(character));
			cos.write(character);
			cos.flush();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private static byte[] digestCharacter(final byte[] character) throws Exception {
		final MessageDigest messageDigest = MessageDigest.getInstance(SysAuthUtils.DIGEST_ALG);
		messageDigest.update(character);
		return messageDigest.digest();
	}

	private static byte[] getCharacter(final PrivateKey privateKey) throws Exception {
		final Cipher keyCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		keyCipher.init(Cipher.DECRYPT_MODE, privateKey);
		final DataInputStream dis = new DataInputStream(new FileInputStream(new File("d:\\"
				+ SysAuthUtils.CHARACTER_FILE)));
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
		while ((theByte = in.read()) != -1){
			out.write(theByte);
		}
		out.flush();
		return out.toByteArray();
	}

	private static PrivateKey getPriKey() throws Exception {
		final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("输入私钥文件密码：");
		final String strFilePwd = in.readLine();
		if (strFilePwd == null || strFilePwd.length() == 0) {
			System.out.println("必须输入一个私钥文件密码！");
			return null;
		}
		final FileInputStream fis = new FileInputStream("d:\\" + SysAuthUtils.PRI_KEY_FILE);
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int theByte = 0;
		while ((theByte = fis.read()) != -1)
			baos.write(theByte);
		fis.close();
		final byte[] keyBytes = baos.toByteArray();
		baos.close();
		final byte[] salt = new byte[8];
		final ByteArrayInputStream bais = new ByteArrayInputStream(keyBytes);
		bais.read(salt, 0, 8);
		final byte[] remainingCiphertext = new byte[keyBytes.length - 8];
		bais.read(remainingCiphertext, 0, keyBytes.length - 8);
		final PBEKeySpec fileKeySpec = new PBEKeySpec(strFilePwd.toCharArray());
		final SecretKeyFactory fileKeyFactory = SecretKeyFactory.getInstance(SysAuthUtils.PRI_KEY_ALG);
		final SecretKey key = fileKeyFactory.generateSecret(fileKeySpec);
		final PBEParameterSpec paramSpec = new PBEParameterSpec(salt, SysAuthUtils.PBE_ITER_COUNT);
		final Cipher cipher = Cipher.getInstance(SysAuthUtils.PRI_KEY_ALG);
		cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
		final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(cipher.doFinal(remainingCiphertext));
		final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePrivate(keySpec);
	}
}
