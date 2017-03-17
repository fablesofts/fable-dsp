package com.fable.dsp.controller.sysauth;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fable.dsp.common.util.SysAuthExecCmd;
import com.fable.dsp.common.util.SysAuthPngEncoder;
import com.fable.dsp.common.util.SysAuthUtils;

/**
 * 系统授权
 * 
 * @author YiHongWei
 * 
 */

@Controller
@RequestMapping("/sysauth")
public class SystemAuthorityController {
	// 验证授权文件公钥
	private static boolean AUTHORIZATION;
	// 授权到期日期
	private static String EXPIREDATE;
	// 证书有效期
	private static long REMINDDATE = 0;
	// 授权公司
	private static String COMPANY;
	// 授权设备
	private static String FACILITY;
	// 产品授权截至日期，显示到图片
	private static String AUTHDATE;
	// 授权设备特征码
	private static String AUTH_FACILITY;
	// 当前设备特征码
	private static String CURRENT_FACILITY;

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemAuthorityController.class);

	private SystemAuthorityController() {
	}

	@RequestMapping("/maintain")
	public String maintain() {
		return "sysauth/sysauth-maintain";
	}

	/**
	 * 下载特征文件
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bulidCharacter")
	public static void download(HttpServletRequest request, HttpServletResponse response) {
		try {
			LOGGER.info("开始下载特征文件");
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			BufferedInputStream bis = null;
			String ctxPath = request.getSession().getServletContext().getRealPath("/");
			genCharacter(ctxPath);
			String downLoadPath = ctxPath + SysAuthUtils.SYSAUTH_PATH + SysAuthUtils.CHARACTER_FILE;
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(SysAuthUtils.CHARACTER_FILE.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			File file = new File(downLoadPath);
			bis = new BufferedInputStream(new FileInputStream(file));
			// 打印特征文件
			OutputStream out = response.getOutputStream();
			byte[] buf = new byte[2048];
			int start = 0;
			int offset = -1;
			while ((offset = bis.read(buf, start, buf.length)) != -1) {
				out.write(buf, start, offset);
			}
			out.flush();
			bis.close();
			file.delete();
			LOGGER.info("下载特征文件成功");
		} catch (Exception e) {
			LOGGER.error("下载特征文件失败");
		}
	}

	/**
	 * 生成特征文件
	 * 
	 * @param contextPath
	 *            项目的全路径
	 */
	public static void genCharacter(String contextPath) {
		OutputStream out = null;
		CipherOutputStream cos = null;
		try {
			LOGGER.info("开始生成特征文件");
			final Cipher keyCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			keyCipher.init(Cipher.ENCRYPT_MODE, getPubKey(contextPath));
			final KeyGenerator sessionKeyGen = KeyGenerator.getInstance(SysAuthUtils.CIPHER_KEY_ALG);
			sessionKeyGen.init(128);
			final Key sessionKey = sessionKeyGen.generateKey();
			final byte[] encodedKeyBytes = keyCipher.doFinal(sessionKey.getEncoded());
			out = new BufferedOutputStream(new FileOutputStream(new File(contextPath + SysAuthUtils.SYSAUTH_PATH
					+ SysAuthUtils.CHARACTER_FILE)));
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
			new SysAuthExecCmd().exec(SysAuthUtils.getCHARACTER_CMD(), null, null, cos);
			LOGGER.info("生成特征文件成功");
		} catch (final Exception e) {
			LOGGER.error("生成特征文件错误 ");
		} finally {
			if (cos != null)
				try {
					cos.close();
				} catch (final Exception e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 获取公钥文件
	 * 
	 * @param contextPath
	 *            项目的全路径
	 */
	public static PublicKey getPubKey(String contentPath) {
		LOGGER.info("正在获取公钥文件");
		try {
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
			LOGGER.info("成功获取公钥文件");
			return keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			LOGGER.error("公钥文件错误 ");
		}
		return null;
	}

	/**
	 * 上传授权文件
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/uploadLicence")
	@ResponseBody
	public static Map<String, Object> uploadLicenceFile(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {

		// 创建一个通用的多部分解析器.
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession()
				.getServletContext());
		/**
		 * json数据容器
		 */
		Map<String, Object> rootJson = new HashMap<String, Object>();
		try {
			LOGGER.info("开始上传授权文件");
			// 判断 request 是否有文件上传,即多部分请求...
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					MultipartFile file = multiRequest.getFile(iter.next());
					if ((file.getOriginalFilename() == "") || (file.getOriginalFilename().length() == 0)) {
						LOGGER.warn("未选择授权文件");
						rootJson.put("dealFlag", false);
						rootJson.put("msg", "未选择授权文件！");
						return rootJson;
					} else {
						if (!checkLicenceFile(file.getInputStream(), request.getSession().getServletContext()
								.getRealPath("/"))) {
							LOGGER.error("授权文件格式错误");
							rootJson.put("dealFlag", false);
							rootJson.put("msg", "授权文件格式错误！");
							return rootJson;
						} else {
							String fileName = SysAuthUtils.LICENCE_FILE;
							String path = request.getSession().getServletContext().getRealPath("/")
									+ SysAuthUtils.SYSAUTH_PATH + fileName;
							File localFile = new File(path);
							if (!localFile.exists()) {
								localFile.createNewFile();
							}
							file.transferTo(localFile);
							rootJson.put("dealFlag", true);
							LOGGER.info("上传授权文件成功");
						}
					}
				}
			}
		} catch (Exception e) {
			rootJson.put("dealFlag", false);
			rootJson.put("msg", "授权文件格式错误！");
			LOGGER.error("授权文件格式错误");
		}
		return rootJson;
	}

	/**
	 * 验证授权文件
	 * 
	 * @param stream
	 *            授权文件流
	 * @param contextPath
	 *            系统路径
	 * @return 授权文件
	 */
	public static boolean checkLicenceFile(InputStream stream, String contextPath) {
		MessageDigest messageDigest;
		try {
			LOGGER.info("开始验证授权文件");
			messageDigest = MessageDigest.getInstance(SysAuthUtils.DIGEST_ALG);
			final int iDigestLength = messageDigest.getDigestLength();
			final byte[] licence = decryptLicence(getPubKeyAndCheck(contextPath), stream);
			EXPIREDATE = new String(licence, 0, 8);
			LOGGER.info("授权到期日期（yyyymmdd）：" + EXPIREDATE);
			final DateFormat df = new SimpleDateFormat("yyyyMMdd");
			LOGGER.info(df.format(new Date()));
			final byte[] tempArray = new byte[8];
			System.arraycopy(licence, 8, tempArray, 0, 8);
			if (Arrays.equals(SysAuthUtils.LICENCE_NAME_KEY, tempArray)) {
				COMPANY = new String(licence, 8 + 8 + 8, 100).trim();
				LOGGER.info("被授权单位：" + COMPANY);
				FACILITY = SysAuthUtils.bytes2HexString(licence, 8 + 8 + 8 + 100, iDigestLength, "");
				LOGGER.info("授权设备特征码：" + FACILITY);
			} else {
				COMPANY = new String(licence, 8 + 8, 100).trim();
				LOGGER.info("被授权单位：" + COMPANY);
				FACILITY = SysAuthUtils.bytes2HexString(licence, 8 + 8 + 100, iDigestLength, "");
				LOGGER.info("授权设备特征码：" + FACILITY);
			}
			final ByteArrayOutputStream out = new ByteArrayOutputStream();
			new SysAuthExecCmd().exec(SysAuthUtils.getCHARACTER_CMD(), null, null, out);
			out.flush();
			messageDigest.update(out.toByteArray());
			final byte[] digest = messageDigest.digest();
			LOGGER.info("当前设备特征码：" + SysAuthUtils.bytes2HexString(digest, 0, digest.length, ""));
			Date date1 = df.parse(EXPIREDATE);
			Date date2 = df.parse(df.format(new Date()));
			REMINDDATE = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
			if (AUTHORIZATION && (FACILITY.equals(SysAuthUtils.bytes2HexString(digest, 0, digest.length, "")))
					&& (REMINDDATE > 0)) {
				LOGGER.info("完成验证授权文件");
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("验证授权文件错误 ");
		}
		return false;
	}

	/**
	 * 解密授权文件
	 * 
	 * @param publicKey
	 *            公钥
	 * @param stream
	 *            授权文件流
	 * @return 授权文件
	 */
	public static byte[] decryptLicence(final PublicKey publicKey, InputStream stream) {
		Cipher keyCipher;
		try {
			LOGGER.info("开始解析授权文件");
			keyCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			keyCipher.init(Cipher.DECRYPT_MODE, publicKey);
			final DataInputStream dis = new DataInputStream(stream);
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
			dis.close();
			int theByte = 0;
			final ByteArrayOutputStream out = new ByteArrayOutputStream();
			while ((theByte = in.read()) != -1)
				out.write(theByte);
			in.close();
			LOGGER.info("解析授权文件成功");
			return out.toByteArray();
		} catch (Exception e) {
			LOGGER.error("解析授权文件错误 ");
		}
		return null;
	}

	/**
	 * 获取公钥文件并做验证
	 * 
	 * @param contextPath
	 *            项目的全路径
	 * @return
	 * @throws Exception
	 */
	public static PublicKey getPubKeyAndCheck(String contextPath) {
		try {
			LOGGER.info("开始获取公钥文件");
			final FileInputStream fis = new FileInputStream(contextPath + SysAuthUtils.SYSAUTH_PATH
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
			LOGGER.info("成功公钥文件");
			return keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			LOGGER.error("不能获取公钥文件 ");
		}
		return null;
	}

	/**
	 * 验证公钥文件
	 * 
	 * @param keyBytes
	 *            公钥
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static boolean checkPubKey(final byte[] keyBytes) {
		try {
			LOGGER.info("开始验证公钥文件");
			final MessageDigest messageDigest = MessageDigest.getInstance(SysAuthUtils.DIGEST_ALG);
			messageDigest.update(keyBytes);
			final byte[] keyDigest = messageDigest.digest();
			if (Arrays.equals(keyDigest, SysAuthUtils.PUB_KEY_DIGEST)) {
				LOGGER.info("完成验证公钥文件");
				return AUTHORIZATION = true;
			}
		} catch (Exception e) {
			LOGGER.error("公钥文件错误 ");
		}
		return false;
	}

	/**
	 * 展示授权图片
	 * 
	 * @param req
	 * @param rsp
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/viewLicence")
	@ResponseBody
	public void viewLicence(final HttpServletRequest req, final HttpServletResponse rsp) {
		try {
			LOGGER.info("正在生成授权");
			rsp.setContentType("image/png");
			String contextPath = req.getSession().getServletContext().getRealPath("/");
			String imagePath = contextPath + SysAuthUtils.SYSAUTH_PATH;
			FileInputStream fs = new FileInputStream(imagePath + "375X266.jpg");
			final Image image = ImageIO.read(fs);
			final Panel dummy = new Panel();
			final MediaTracker mt = new MediaTracker(dummy);
			mt.addImage(image, 1);
			try {
				mt.waitForID(1);
			} catch (final InterruptedException e) {
				LOGGER.error("生成授权错误");
			}
			final BufferedImage bImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			final Graphics graphics = bImage.getGraphics();
			graphics.drawImage(image, 0, 0, null);
			graphics.setColor(new Color(21, 79, 139));
			final Font font = new Font("Default", Font.BOLD, 16);
			graphics.setFont(font);
			final String strSan = "版权所有  \u00A9  公安部第三研究所开发  ";
			final FontMetrics fm = graphics.getFontMetrics();
			final String strLicence = " 产品授权到 " + getLicenceDesc(req, rsp);
			int iX = 200 - fm.charsWidth(strSan.toCharArray(), 0, strSan.toCharArray().length) / 2;
			graphics.drawString(strSan, iX, 110);
			iX = 200 - fm.charsWidth(strLicence.toCharArray(), 0, strLicence.toCharArray().length) / 2;
			graphics.drawString(strLicence, iX, 150);
			final SysAuthPngEncoder encoder = new SysAuthPngEncoder(bImage);
			rsp.getOutputStream().write(encoder.pngEncode());
			rsp.getOutputStream().flush();
		} catch (Exception e) {
			LOGGER.error("不能生成授权");
		}
	}

	/**
	 * 获得证书中授权到期日期信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static String getLicenceDesc(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("开始获取授权信息");
		String contextPath = request.getSession().getServletContext().getRealPath("/");
		try {
			final byte[] licence = decryptLicence(getPubKey(contextPath), contextPath);
			final DateFormat df = new SimpleDateFormat("yyyyMMdd");
			final Date licenceDate = df.parse(new String(licence, 0, 8));
			EXPIREDATE = new String(licence, 0, 8);
			final DateFormat df2 = new SimpleDateFormat("yyyy年MM月dd日");
			AUTHDATE = df2.format(licenceDate);
			byte[] tempArray = new byte[8];
			System.arraycopy(licence, 8, tempArray, 0, 8);
			if (Arrays.equals(SysAuthUtils.LICENCE_NAME_KEY, tempArray)) {
				tempArray = new byte[100];
				System.arraycopy(licence, 8 + 8 + 8, tempArray, 0, 100);
				COMPANY = new String(tempArray).trim();
			} else {
				tempArray = new byte[100];
				System.arraycopy(licence, 8 + 8, tempArray, 0, 100);
				COMPANY = new String(tempArray).trim();
			}
			if (REMINDDATE > 0) {
				if (AUTHORIZATION) {
					return AUTHDATE;
				} else {
					LOGGER.error("授权文件错误");
				}
			} else {
				LOGGER.error("授权已经过期!");
			}
		} catch (Exception e) {
			LOGGER.error("系统尚未获得授权 ");
		}
		return AUTHDATE;
	}

	public static byte[] decryptLicence(final PublicKey publicKey, String contextPath) throws Exception {
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
	 * 关于授权验证
	 * 
	 * @param request
	 */
	@RequestMapping("/aboutLicence")
	@ResponseBody
	public static String aboutLicence(HttpServletRequest request) {
		LOGGER.info("正在验证系统授权信息");
		String contextPath = request.getSession().getServletContext().getRealPath("/");
		if (!new File(contextPath + SysAuthUtils.SYSAUTH_PATH + SysAuthUtils.LICENCE_FILE).exists()) {
			LOGGER.error("系统尚未获得授权");
			return "";
		} else {
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
				request.getSession().setAttribute("REMINDDATE", REMINDDATE);

				if (Arrays.equals(SysAuthUtils.LICENCE_NAME_KEY, tempArray)) {
					AUTH_FACILITY = SysAuthUtils.bytes2HexString(licence, 8 + 8 + 8 + 100, iDigestLength, "");
					LOGGER.info("授权设备特征码：" + AUTH_FACILITY);
				} else {
					AUTH_FACILITY = SysAuthUtils.bytes2HexString(licence, 8 + 8 + 100, iDigestLength, "");
					LOGGER.info("授权设备特征码：" + AUTH_FACILITY);
				}
				final ByteArrayOutputStream out = new ByteArrayOutputStream();
				new SysAuthExecCmd().exec(SysAuthUtils.getCHARACTER_CMD(), null, null, out);
				out.flush();
				messageDigest.update(out.toByteArray());
				final byte[] digest = messageDigest.digest();
				CURRENT_FACILITY = SysAuthUtils.bytes2HexString(digest, 0, digest.length, "");
				LOGGER.info("当前设备特征码：" + CURRENT_FACILITY);
			} catch (Exception e) {
				LOGGER.error("系统尚未获得授权");
				return "";
			}
		}
		return REMINDDATE + "";
	}
}
