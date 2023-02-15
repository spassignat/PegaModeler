package net.pega.intellij.modeler.uml.connect;

import net.pega.intellij.modeler.uml.PegaClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.io.PrintStream;

public class Connect extends PegaClient {
	public void analyse(PrintStream out) throws IOException {
		final HttpGet get = createRequest("/");
		try (CloseableHttpResponse response = client.execute(get)) {
			log(response.getStatusLine().toString());
		} catch (Exception e) {
			log(e.toString());
		}
	}

	@Override
	public String getAnalysis() {
		return "Connect";
	}
}
