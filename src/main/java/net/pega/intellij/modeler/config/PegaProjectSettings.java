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
package net.pega.intellij.modeler.config;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.annotations.Transient;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@State(name = "PegaProjectSettings", storages = @Storage(value = "pega.xml"))
public class PegaProjectSettings implements PersistentStateComponent<PegaConfigState> {
	@Transient
	private final List<ChangeListener> listeners = new ArrayList<>();
	@NotNull
	private final PegaConfigState state = new PegaConfigState();

	public PegaProjectSettings() {
	}

	public void addChangeListener(ChangeListener changeListener) {
		listeners.add(changeListener);
	}

	public CloseableHttpClient createHttpClient() {
		final UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(state.connectState.login, state.connectState.pwd);
		final BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY, credentials);
		final HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setDefaultCredentialsProvider(credsProvider);
		final String token = Base64.getEncoder().encodeToString((state.connectState.login + ":" + state.connectState.pwd).getBytes());
		final List<BasicHeader>
				defaultHeaders =
				Arrays.asList(new BasicHeader("Cookie", "JSESSIONID=1234"),
							  new BasicHeader("Accept", "application/json"),
							  new BasicHeader("X-Application", "DataModel"),
							  new BasicHeader("Authorization", "Basic " + token));
		//		httpClientBuilder.setDefaultHeaders(defaultHeaders);
		CloseableHttpClient client = httpClientBuilder.build();
		return client;
	}

	public HttpGet createRequest(String... context) {
		StringBuilder path = new StringBuilder();
		for (int i = 0; i < context.length; i++) {
			String s = context[i];
			path.append("/").append(URLEncoder.encode(s, StandardCharsets.UTF_8));
		}
		path = new StringBuilder(path.toString().replaceAll("//", "/"));
		path.insert(0, state.connectState.url);
		final HttpGet get = new HttpGet(path.toString());
		final String token = Base64.getEncoder().encodeToString((state.connectState.login + ":" + state.connectState.pwd).getBytes());
		//		log(get.toString());
		//		final HttpGet get = new HttpGet("http://localhost:8090/prweb/api/UML/1.0/datamodel");
		get.setHeader("Cookie", "JSESSIONID=1234");
		get.setHeader("Accept", "application/json");
		get.setHeader("X-Application", "DataModel");
		get.addHeader("Authorization", "Basic " + token);
		return get;
	}

	public void fireChangeEvent() {
		final ChangeEvent e = new ChangeEvent(this);
		for (ChangeListener changeListener : listeners) {
			changeListener.stateChanged(e);
		}
	}

	public @NotNull PegaConfigState getState() {
		return state;
	}

	public void loadState(PegaConfigState state) {
		this.state.connectState.loadState(state.connectState);
		this.state.dataModelState.loadState(state.dataModelState);
		this.state.githubUser = state.githubUser;
		this.state.githubToken = state.githubToken;
	}
}

