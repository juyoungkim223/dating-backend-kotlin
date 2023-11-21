package com.riti.backendforfrontend.service

import com.riti.backendforfrontend.config.MailConfig
import com.riti.data.dto.SendEmailDto
import jakarta.mail.internet.MimeMessage
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service


@Service
class EmailService (private val javaMailSender: MailConfig){
    //https://www.google.com/search?q=spring+boot+mail+sender+non+blocking&oq=spring+boot+mail+sender+non+blo&aqs=chrome.1.69i57j33i160l3j33i21.9073j0j9&sourceid=chrome&ie=UTF-8
    // non-blocking
    @Async
    fun sendEmail(randomKey: String, request: SendEmailDto.SendEmailRequest) {
        val mimeMessage: MimeMessage = javaMailSender.javaMailService().createMimeMessage()
        val mimeMessageHelper = MimeMessageHelper(mimeMessage, true)
        mimeMessageHelper.setSubject("[리티] 이메일 인증")
        mimeMessageHelper.setText("<html><head><style>" +
                "body { font-family: Arial, sans-serif; font-size: 14px; }" +
                "div { text-decoration: none; color: #000000;}" +
                "div.button { display: block; background-color: #00AE68; position: relative; float: left; width: 120px; padding: 0; margin: 10px 10px 10px 10px; font-weight: 600; text-align: center; line-height: 50px; color: #FFF; border-radius: 5px; transition: all 0.2s; }" +
                ".clear { clear: both; }" +
                "</style></head><body>" +
                "<p>" + "인증 코드를 입력해주세요." + "</p>" +
                "<div class=\"container\">" +
                "<div title=\"Button fade blue/green\" class=\"button \">"+ "인증 코드: $randomKey" +"</a>" +
                "<div class=\"clear\"></div>" +
                "</div></body></html>", true)
        mimeMessageHelper.setTo(request.email)
        //javaMailSender.javaMailService().send(mimeMessage)
        return;
    }


}
