package com.egdfrm.core.service.mail;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.egdfrm.core.model.standard.SimpleMail;

@Service
public class MailService {
	private JavaMailSender mailSender;// 注入Spring封装的javamail，Spring的xml中已让框架装配
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	private TaskExecutor taskExecutor;// 注入Spring封装的异步执行器
	private StringBuffer message = new StringBuffer();
	
	public StringBuffer getMessage() {
		return message;
	}
	
	public void setMessage(StringBuffer message) {
		this.message = message;
	}
	
	public void sendMail(SimpleMail email) throws MessagingException, IOException {
		sendMailBySynchronizationMode(email);
	}
	
	// @Override
	public void sendMailByAsynchronousMode(final SimpleMail email) {
		taskExecutor.execute(new Runnable() {
			public void run() {
				try {
					sendMailBySynchronizationMode(email);
				} catch (Exception e) {
					//
				}
			}
		});
	}
	
	// @Override
	public void sendMailBySynchronizationMode(SimpleMail email) throws MessagingException,
	      IOException {
		MimeMessage mime = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mime, true, "utf-8");
		helper.setFrom(email.getFromAddress());// 发件人
		helper.setTo(email.getToAddress());// 收件人
		//helper.setReplyTo(email.getFromAddress());// 回复到
		helper.setSubject(email.getSubject());// 邮件主题
		helper.setText(email.getContent(), true);// true表示设定html格式
		mailSender.send(mime);
	}
	
}
