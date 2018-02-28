package pl.otago.restD2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


public class App {

	public static void main(String[] args) throws IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("candidate:abc123"));
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setCredentialsProvider(credentialsProvider);
		HttpGet httpget = new HttpGet("http://dt-gwitczak-recruitment.westeurope.cloudapp.azure.com:8080/rest/task");
		CloseableHttpResponse response = httpclient.execute(httpget, localContext);
		try {
			String stringJSON = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject obj = new JSONObject(stringJSON);
			JSONArray array = obj.getJSONArray("data");

			Integer[] numbers = new Integer[array.length()];

			// Extract numbers from JSON array.
			for (int i = 0; i < array.length(); ++i) {
				numbers[i] = array.optInt(i);
			}
			// usunięcie powtórzeń liczb oraz posortowanie
			Set<Integer> arrayAfterDelete = DeleteRepeatNumbers.arrayMethod(numbers);
			List<Integer> afterDeleteRepeat = new ArrayList<Integer>(arrayAfterDelete);
			Collections.sort(afterDeleteRepeat);
			// wybranie liczb pierwszych
			for (int number : afterDeleteRepeat) {
				if (Prime.isPrime(number)) {
					System.out.println(" " + number);
				}
			}

		} finally {
			response.close();
		}
	}

}
