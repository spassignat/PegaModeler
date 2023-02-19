package net.pega.intellij.modeler.uml;

import net.pega.intellij.modeler.config.PegaConfigState;
import net.pega.intellij.modeler.view.MessageCallback;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Base64;

public abstract class PegaClient implements Context {
	protected DefaultHttpClient client;
	protected PegaConfigState state;
	MessageCallback log;
	private String token;

	public abstract void analyse(PrintStream out) throws IOException;

	public HttpGet createRequest(String path) {
		final HttpGet get = new HttpGet(state.connectState.url + path);
		log(get.toString());
		//		final HttpGet get = new HttpGet("http://localhost:8090/prweb/api/UML/1.0/datamodel");
		get.setHeader("Cookie", "JSESSIONID=1234");
		get.setHeader("Accept", "application/json");
		get.setHeader("X-Application", "DataModel");
		get.addHeader("Authorization", "Basic " + token);
		return get;
	}

	@Override
	public CloseableHttpResponse execute(HttpGet get) throws IOException {
		return client.execute(get);
	}

	public abstract String getAnalysis();

	@Override
	public PegaConfigState getState() {
		return state;
	}

	public void init(PegaConfigState state) {
		this.state = state;
		client = new DefaultHttpClient();
		final BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(state.connectState.login, state.connectState.pwd));
		client.setCredentialsProvider(credsProvider);
		token = Base64.getEncoder().encodeToString((state.connectState.login + ":" + state.connectState.pwd).getBytes());
	}

	public void init(PegaConfigState state, MessageCallback log) {
		this.state = state;
		this.log = log;
	}

	@Override
	public void log(String msg) {
		log.log(msg);
	}
}
