package com.arcs.cibus.server.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.User;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.url.host}")
	private String urlHost;
		
	public boolean sendMailConfirmAccount(User user){
		String sendTo = user.getEmail();
		String name = user.getName();
		Long userId = user.getId();

		boolean statusEmail =  Boolean.TRUE;
		String urlConfirmMail = urlHost + "confirmAccount?i=" + userId.toString();
		
		try{
			MimeMessage msg = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			
	        helper.setTo(sendTo);
	        helper.setSubject("Cibus - Confirm your account");

	        StringBuffer  body = new StringBuffer();

	        body.append("<table width=\"620\" align=\"center\"> ");
	        body.append("        <thead> ");
	        body.append("            <tr> ");
	        body.append("              <td style=\"padding:30px 0;color:#fff;text-transform:uppercase;text-align:center;font-family:Arial,sans-serif;font-size:16px;letter-spacing:8px;border-radius:5px; ");
	        body.append("              background: #092756; ");
	        body.append("              background: -moz-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%),-moz-linear-gradient(top,  rgba(57,173,219,.25) 0%, rgba(42,60,87,.4) 100%), -moz-linear-gradient(-45deg,  #670d10 0%, #092756 100%); ");
	        body.append("              background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -webkit-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -webkit-linear-gradient(-45deg,  #670d10 0%,#092756 100%); ");
	        body.append("              background: -o-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -o-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -o-linear-gradient(-45deg,  #670d10 0%,#092756 100%); ");
	        body.append("              background: -ms-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -ms-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -ms-linear-gradient(-45deg,  #670d10 0%,#092756 100%); ");
	        body.append("              background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), linear-gradient(to bottom,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), linear-gradient(135deg,  #670d10 0%,#092756 100%);\"> ");
	        body.append("                <p>Welcome to Cibus</p> ");
	        body.append("              </td> ");
	        body.append("            </tr> ");
	        body.append("        </thead> ");
	        body.append("        <tbody style=\"text-align:center;\"\"> ");
	        body.append("          <tr> ");
	        body.append("            <td> ");
	        body.append("              <h1 style=\"display:block;margin:0;padding:0;color:#222222;font-family:Helvetica;font-size:40px;font-style:normal;font-weight:bold;line-height:150%;letter-spacing:normal;margin-top:50px\">Hello, " + name + "!</h1><br/> ");
	        body.append("              <p style=\"color:#000000;margin:10px 0;padding:0;font-family:Arial,sans-serif;font-size:16px;line-height:150%\">");
	        body.append("			      Click in the button to confirm your accont.</p><br/> ");
	        body.append("			   </p><br/> ");
	        body.append("              <a href=\"" + urlConfirmMail + "\" style=\"text-decoration:none;\">");
	        body.append("			      <p style=\"text-align:center;color:#ffffff;margin:30px 0 10px 0;padding:0;font-family:Arial,sans-serif;font-size:16px;line-height:150%\">");
	        body.append("			         <span style=\"padding:12px 70px;background:#1F7A93;border-radius:10px;color:#fff\">Confirm</span></a> ");
	        body.append("            </p> ");
	        body.append("          </td> ");
	        body.append("        </tr> ");
	        body.append("      <tr> ");
	        body.append("        <td style=\"padding:20px 0 30px;font-family:Arial,sans-serif;font-size:14px;line-height:28px\"> ");
	        body.append("          <p>© 2020 Cibus - Cibus Address</p> ");
	        body.append("        </td> ");
	        body.append("      </tr> ");
	        body.append("    </tbody> ");
	        body.append("  </table>");
	        
	        helper.setText(body.toString(), true);

	        javaMailSender.send(msg);
		}
		catch(Exception ex){
			statusEmail =  Boolean.FALSE;
		}
		
		return statusEmail;
	}
}
