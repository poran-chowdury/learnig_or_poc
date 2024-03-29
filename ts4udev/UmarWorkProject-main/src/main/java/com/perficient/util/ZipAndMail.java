package com.perficient.util;

/**
 * Utility to convert a file to .zip and mail the .zip file.
 * @author ameya.pagore
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class ZipAndMail {

	public static ZipFile zipFile;
	static String filePathZip;
	static ArrayList<File> files;
	static File reportFile;
	static File errorImages;

	/**
	 * This method Converts the .html report of test case into a .zip file.
	 * 
	 * @throws ZipException
	 */
	public static void convertToZip() throws ZipException {
		filePathZip = ComplexReportFactory.filePath.replace(".html", ".zip");
		zipFile = new ZipFile(filePathZip);
		files = new ArrayList<>();
		reportFile = new File(ComplexReportFactory.filePath);
		errorImages = new File(TestCaseBase.errorImagesPath);
		files.add(reportFile);
		files.add(errorImages);
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // set compression method to deflate compression
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		zipFile.addFiles(files, parameters);
	}

	/**
	 * This Method Mails the .zip file of report to the Address set in the
	 * properties file. Email id and password of sender must be set in properties
	 * file by name - "myEmail" & "myPassword respectively."
	 * 
	 * @param sendToEmail
	 */
	public static void sendMail(String sendToEmail) {
		Properties props = new Properties();

		// Use These properties for gmail.com
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		// Use this properties for outlook.com
		/*
		 * props.put("mail.smtp.host", "outlook.office365.com");
		 * props.put("mail.smtp.auth", "true"); props.put("mail.smtp.starttls.enable",
		 * "true"); props.put("mail.smtp.port", "587");
		 */

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(TestData.get("fromEmailId"), TestData.get("Password"));
			}
		});
		try {
			// Create object of MimeMessage class
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(TestData.get("fromEmailId")));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sendToEmail));
			message.setSubject("Project Name - Automation Execution Report");

			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("Please find attached automation execution report herewith this email.");

			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// Create data source and pass the filename
			DataSource source = new FileDataSource(filePathZip);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName(filePathZip);

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart1);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport.send(message);

			System.out.println("=====Email Sent=====");

		} catch (MessagingException e) {
			System.out.println("No Recepient Email Ids found.");
		}
	}
}