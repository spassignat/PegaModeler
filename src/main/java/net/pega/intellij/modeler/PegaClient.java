/*
 * Copyright (c) 2023-2023 Stephane Passignat - Exygen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is furnished
 * to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.pega.intellij.modeler;

import net.pega.intellij.modeler.config.PegaConfigState;
import net.pega.intellij.modeler.view.MessageCallback;
import net.pega.model.Rule;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Base64;

public abstract class PegaClient implements Context {
	protected CloseableHttpClient client;
	protected PegaConfigState configuration;
	MessageCallback log;
	private String token;

	public abstract void analyse(PrintStream out, Rule rule);

	public HttpGet createRequest(String path) {
		final HttpGet get = new HttpGet(configuration.connectState.url + path);
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
	public PegaConfigState getConfiguration() {
		return configuration;
	}

	public void init(PegaConfigState state, MessageCallback log) {
		this.configuration = state;
		this.log = log;
		final UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(state.connectState.login, state.connectState.pwd);
		final BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY, credentials);
		client = HttpClientBuilder.create().setDefaultCredentialsProvider(credsProvider).build();
		token = Base64.getEncoder().encodeToString((state.connectState.login + ":" + state.connectState.pwd).getBytes());
	}

	@Override
	public void log(String msg) {
		//		log.log(msg);
	}
}
