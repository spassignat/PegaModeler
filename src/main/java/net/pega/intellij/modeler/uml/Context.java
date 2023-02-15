package net.pega.intellij.modeler.uml;

import net.pega.intellij.modeler.config.PegaProjectSettings;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

public interface Context {
	HttpGet createRequest(String s);

	CloseableHttpResponse execute(HttpGet get) throws IOException;

	PegaProjectSettings.PegaConfigState getState();

	void log(String msg);
}
