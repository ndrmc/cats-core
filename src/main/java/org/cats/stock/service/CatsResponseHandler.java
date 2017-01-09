package org.cats.stock.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.ParameterizedType;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CatsResponseHandler<T> {
	private final Class<T> type;

	public CatsResponseHandler (Class<T> myType) {
		this.type = myType;
	}

	T getResponse(CloseableHttpClient httpclient,HttpGet httpget ) {
		Class<T> clazz = this.type;

		T responsObj=null;
		ResponseHandler<T> rh = new ResponseHandler<T>() {

			@Override
			public T handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				StatusLine statusLine = response.getStatusLine();
				HttpEntity entity = response.getEntity();
				if (statusLine.getStatusCode() >= 300) {
					throw new HttpResponseException(
							statusLine.getStatusCode(),
							statusLine.getReasonPhrase());
				}
				if (entity == null) {
					throw new ClientProtocolException("Response contains no content");
				}
				ObjectMapper mapper =new ObjectMapper();

				ContentType contentType = ContentType.getOrDefault(entity);
				Charset charset = contentType.getCharset();
				Reader reader = new InputStreamReader(entity.getContent(), charset);
				System.out.println("----------------response---------------- \n"+EntityUtils.toString(entity, "UTF-8"));
				return mapper.readValue(reader, clazz);


			}
		};
		try {
			responsObj = httpclient.execute(httpget, rh);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responsObj;

	}





}
