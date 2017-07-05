package com.chat_sever;

import org.apache.commons.mail.HtmlEmail;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class Sendcode 
{
	public static boolean send(String phoneNumber, String code) {

		try {
			TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23434458",
					"beedcab9cf9af63d1b7a502b140d4372");
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setExtend("123456");
			req.setSmsType("normal");
			req.setSmsFreeSignName("����ѧ��");
			req.setSmsParamString("{\"code\":\"" + code + "\"}");
			req.setRecNum(phoneNumber);
			req.setSmsTemplateCode("SMS_13060260");
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			if  (!(rsp.getBody().indexOf("\"success\":true") >= 0)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean sendEmail(String emailaddress, String code) {

		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.163.com");
			email.setCharset("UTF-8");
			email.addTo(emailaddress);///�ռ���ַ

			email.setFrom("15062201995@163.com", "PP�ٷ��ʼ�");

			email.setAuthentication("15062201995@163.com", "yl123456");

			email.setSubject("PP�ٷ���֤");
			email.setMsg("������֤����:" + code+"  �����ƹ����������֤�룬�Է�������");
			email.send();
			return true;
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}

	}

}
