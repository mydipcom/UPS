package com.bps.commons;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
public class EMailTool {
	 public void send(JavaMailSender javaMailSenderImpl){
		try {
			MimeMessage message = javaMailSenderImpl.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
			messageHelper.setFrom("宋杰");
			messageHelper.setTo("campray@sina.com");
			messageHelper.setSubject("测试邮件");
			messageHelper.setText("登录成功");
			
			javaMailSenderImpl.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
