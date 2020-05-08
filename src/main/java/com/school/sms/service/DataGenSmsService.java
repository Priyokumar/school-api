package com.school.sms.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.common.services.BeansService;
import com.school.common.vo.DataGenSMSStatus;
import com.school.sms.dto.DatagenResponse;

@Service
public class DataGenSmsService {

	private static final String URL = "https://global.datagenit.com/API/sms-api.php";
	private static final String AUTH_KEY = "D!~3385Zi1T3dpjkk";
	private static final String SENDER_ID = "PESADM";

	@Autowired
	private BeansService beansService;

	public DatagenResponse sendSms(String contactNumber, String message) {

		DatagenResponse smsResponse = new DatagenResponse();
		System.out.println("Sending sms");
		try {
			RestTemplate restTemplate = beansService.getBeans().getRestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.add("cache-control", "no-cache");

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL).queryParam("auth", AUTH_KEY)
					.queryParam("senderid", SENDER_ID).queryParam("msisdn", contactNumber)
					.queryParam("message", message);

			HttpEntity<String> entity = new HttpEntity<String>(headers);

			HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
					String.class);

			String body = response.getBody();

			ObjectMapper mapper = new ObjectMapper();
			smsResponse = mapper.readValue(body, DatagenResponse.class);
			System.out.println("sms response :");
			System.out.println(smsResponse);

		} catch (Exception e) {
			smsResponse.setStatus(DataGenSMSStatus.FAILED_TO_SEND);
			System.out.println(e.getMessage());
		}
		return smsResponse;
	}

}
