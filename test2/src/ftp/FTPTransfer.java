package ftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

/**
 * Bu class statiktir. direk FTPTransfer.upload ya da download u
 * kullanacaksınız.
 * */
public class FTPTransfer {

	static Toast toast;

	/**
	 * @param applicationContext
	 *            için getApplicationContext() kullan sadece
	 * 
	 * @param sourcePath
	 *            /sdcard/ için
	 *            "Environment.getExternalStorageDirectory().getPath()" kullan.
	 * @param destinationPath
	 *            ise "httpdocs/yeniText.txt" gibi httpdocs klasörünü
	 *            barındırmalı
	 * 
	 * @param connectionString
	 *            [0] = ip adresi,
	 * 
	 * @param connectionString
	 *            [1] = ftp username,
	 * 
	 * @param connectionString
	 *            [2] = ftp şifre
	 */
	public static boolean upload(Context applicationContext, String sourcePath,
			String destinationPath, String[] connectionString) {
		try {
			FTPClient ftpClient = new FTPClient();
			ftpClient.connect(connectionString[0]);
			if (ftpClient.login(connectionString[1], connectionString[2])) {
				ftpClient.enterLocalPassiveMode(); // important!
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				FileInputStream in = new FileInputStream(new File(sourcePath));
				showToast(applicationContext, "Upload Started");
				if (ftpClient.storeFile(destinationPath, in)) {
					showToast(applicationContext, "Upload Completed");
				}
				in.close();
				ftpClient.logout();
				ftpClient.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @param applicationContext
	 *            için getApplicationContext() kullan sadece
	 * 
	 * @param destinationPath
	 *            sdcard için
	 *            "Environment.getExternalStorageDirectory().getPath()" kullan.
	 *            Ve eğer bir klasör içine koyacaksanız o kalsörün
	 *            VAROLDUĞUNDAN emin olun. Kendisi klasör üretemio.
	 * 
	 * @param sourcePath
	 *            ise "httpdocs/yeniText.txt" gibi httpdocs klasörünü
	 *            barındırmalı.
	 * 
	 * @param connectionString
	 *            [0] = ip adresi,
	 * 
	 * @param connectionString
	 *            [1] = ftp username,
	 * 
	 * @param connectionString
	 *            [2] = ftp şifre
	 */
	public static Object download(Context applicationContext,
			String sourcePath, String destinationPath, String[] connectionString) {
		try {
			FTPClient ftpClient = new FTPClient();
			ftpClient.connect(connectionString[0]);
			ftpClient.login(connectionString[1], connectionString[2]);
			ftpClient.changeWorkingDirectory(Environment
					.getExternalStorageDirectory().getAbsolutePath());
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			// FileOutputStream desFileStream = new
			// FileOutputStream("/sdcard/test25.txt");
			BufferedOutputStream desFileStream = new BufferedOutputStream(
					new FileOutputStream(destinationPath), 8 * 1024);

			if (ftpClient.retrieveFile(sourcePath, desFileStream)) {
				showToast(applicationContext, "Download Completed");
			}

			desFileStream.flush();

			ftpClient.logout();
			ftpClient.disconnect();

		}

		catch (IOException e) {
			e.printStackTrace();
			showToast(applicationContext, "error");
		}

		return null;

	}

	private static void showToast(Context applicationContext, String message) {
		toast = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT);
		toast.show();
	}
}
