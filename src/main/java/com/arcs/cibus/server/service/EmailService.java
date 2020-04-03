package com.arcs.cibus.server.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendMailConfirmAccount(String sendTo, String name){
		boolean statusEmail =  Boolean.TRUE;
		String urlConfirmMail = "http://localhost:3000";
		
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
	        body.append("              background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), linear-gradient(to bottom,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), linear-gradient(135deg,  #670d10 0%,#092756 100%); ");
	        body.append("              \"> ");
	        body.append("                <p>Welcome to Cibus</p> ");
	        body.append("              </td> ");
	        body.append("            </tr> ");
	        body.append("        </thead> ");
	        body.append("        <tbody style=\"text-align:center;\"\"> ");
	        body.append("          <tr> ");
	        body.append("            <td> ");
	        body.append("              <h1 style=\"");
	        body.append("display:block;");
	        body.append("margin:0;");
	        body.append("padding:0;");
	        body.append("color:#222222;");
	        body.append("font-");
	        body.append("family:Helvetica;");
	        body.append("font-");
	        body.append("size:40px;");
	        body.append("font-");
	        body.append("style:normal;");
	        body.append("font-");
	        body.append("weight:bold;");
	        body.append("line-");
	        body.append("height:150%;");
	        body.append("letter-");
	        body.append("spacing:normal;");
	        body.append("margin-");
	        body.append("top:50px\">Hello, " + name + "!</h1><br/> ");
	        body.append("              <p style=\"");
	        body.append("color:#000000;");
	        body.append("margin:10px 0;");
	        body.append("padding:0;");
	        body.append("font-");
	        body.append("family:Arial,sans-serif;");
	        body.append("font-");
	        body.append("size:16px;");
	        body.append("line-");
	        body.append("height:150%\">Click in the button to confirm your accont.</p><br/> ");
	        body.append("              <a href=\"");
	        body.append(urlConfirmMail + "\" style=\"text-");
	        body.append("decoration:none;\"");
	        body.append("><p style=\"text-align:center;");
	        body.append("color:#ffffff;");
	        body.append("margin:30px 0 10px 0;");
	        body.append("padding:0;");
	        body.append("font-");
	        body.append("family:Arial,sans-serif;");
	        body.append("font-");
	        body.append("size:16px;");
	        body.append("line-");
	        body.append("height:150%\"><span style=\"");
	        body.append("padding:12px 70px;");
	        body.append("background:#1F7A93;");
	        body.append("border-");
	        body.append("radius:10px;");
	        body.append("color:#fff\">Confirm</span></a> ");
	        body.append("            </p> ");
	        body.append("          </td> ");
	        body.append("        </tr> ");
	        body.append("        <tr> ");
	        body.append("          <td style=\"");
	        body.append("padding:20px 0 30px\"><img src=\"");
	        body.append("https://ci4.googleusercontent.com/proxy/zsAmASjqdEWpheW0XAMXx4Rh_J0qXlzEABeM3UyMox7oTBXSOceSJLetwRlZJGHie22I0_Ed94yLHBQrq1mhs5A=s0-d-e1-");
	        body.append("ft#http://www.welcome.gauge.com.br/_/img/line.png\" alt=\"line.png\" class=\"CToWUd\"> ");
	        body.append("          </td> ");
	        body.append("        </tr> ");
	        body.append("      <tr> ");
	        body.append("        <td style=\"");
	        body.append("padding:0px 0 30px;");
	        body.append("font-");
	        body.append("family:Arial,sans-serif;");
	        body.append("font-");
	        body.append("size:14px;");
	        body.append("line-");
	        body.append("height:28px\"> ");
	        body.append("          <p>© 2020 Cibus - Cibus Address</p> ");
	        body.append("        </td> ");
	        body.append("      </tr> ");
	        body.append("    </tbody> ");
	        body.append("  </table>");
	        
	        helper.setText(body.toString(), true);

			// hard coded a file path
	        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

//	        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

	        javaMailSender.send(msg);
		}
		catch(Exception ex){
			statusEmail =  Boolean.FALSE;
		}
		
		return statusEmail;
	}
}
