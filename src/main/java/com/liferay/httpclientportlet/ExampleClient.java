package com.liferay.httpclientportlet;

import java.io.InputStream;
import java.util.Scanner;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ExampleClient {

	public void exerciseClient() throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://example.com/");
		CloseableHttpResponse response = null;

		try {
			response = httpclient.execute(httpget);

			Header[] headers = response.getAllHeaders();
			
			for (Header header : headers) {
				System.out.println(
					"header name: " + header.getName() +
					", header value: " + header .getValue());
			}
			
			HttpEntity httpEntity = response.getEntity();
			
			InputStream httpEntityContent = httpEntity.getContent();
			
			Scanner s = new Scanner(httpEntityContent).useDelimiter("\\A");

			String content = s.hasNext() ? s.next() : "";
			
			System.out.println("content:\n" + content);
		}
		finally {
			if (response != null) {
				response.close();
			}
		}
	}

}
