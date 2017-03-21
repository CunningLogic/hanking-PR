package com.hanking.pr.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;



public class HttpWorker {
	
	private DefaultHttpClient httpclient =new DefaultHttpClient();
	private AuthScope mAuthScope;
	public HttpWorker(){
		
	}
	
	public HttpWorker(String username,String password){
		UsernamePasswordCredentials creds = new 
		UsernamePasswordCredentials(username, password); 
		httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, creds); 
		BasicHttpContext localcontext = new BasicHttpContext(); 
		BasicScheme basicAuth = new BasicScheme(); 
		localcontext.setAttribute("preemptive-auth", basicAuth); 
		httpclient.addRequestInterceptor(preemptiveAuth, 0); 
	}
	
	public String httpGet(String url) {  
        try {  
            HttpGet httpGet = new HttpGet(url);  
            HttpResponse resp = httpclient.execute(httpGet);  
            HttpEntity entity = resp.getEntity();  
            String respContent = EntityUtils.toString(entity , "UTF-8").trim();  
            httpGet.abort(); 
            httpclient.getConnectionManager().shutdown();
            return respContent;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }
	
	public String httpPost(String url,Map<String,String>params){
		try {
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
			for(String key:params.keySet()){
				formparams.add(new BasicNameValuePair(key, params.get(key)));  
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");  
			HttpPost httppost = new HttpPost(url);  
			httppost.setEntity(entity); 
			HttpResponse resp=null;
			resp=httpclient.execute(httppost);
			HttpEntity reqEntity = resp.getEntity();  
            String respContent = EntityUtils.toString(reqEntity , "UTF-8").trim();  
            httppost.abort();  
            return respContent;  
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public String httpPosts(String url,String params){
		try {
			StringEntity entity=new StringEntity(params,"UTF-8");
			HttpPost httppost = new HttpPost(url);  
			httppost.setEntity(entity); 
			HttpResponse resp=null;
			resp=httpclient.execute(httppost);
			HttpEntity reqEntity = resp.getEntity();  
            String respContent = EntityUtils.toString(reqEntity , "UTF-8").trim();  
            httppost.abort();  
            return respContent;  
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String httpsPost(String url,String params){
		try {
			
			
			HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

			DefaultHttpClient client = new DefaultHttpClient();

			SchemeRegistry registry = new SchemeRegistry();
			SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
			socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
			registry.register(new Scheme("https", socketFactory, 443));
			SingleClientConnManager mgr = new SingleClientConnManager(client.getParams(), registry);
			DefaultHttpClient httpClient = new DefaultHttpClient(mgr, client.getParams());

			// Set verifier     
			HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
			
			StringEntity entity=new StringEntity(params,"UTF-8");
			HttpPost httppost = new HttpPost(url);  
			httppost.setEntity(entity); 
			HttpResponse resp=null;
			resp=httpClient.execute(httppost);
			HttpEntity reqEntity = resp.getEntity();  
            String respContent = EntityUtils.toString(reqEntity , "UTF-8").trim();  
            httppost.abort();  
            return respContent;  
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	// Do not do this in production!!!
	
	
	
	public void close(){
		httpclient.getConnectionManager().shutdown();
	}
	
	private void prepareHttpClient() {

        // Create and initialize HTTP parameters
        HttpParams params = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(params, 10);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

        // Create and initialize scheme registry
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), 9092));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory
                .getSocketFactory(), 443));

        // Create an HttpClient with the ThreadSafeClientConnManager.
        ClientConnectionManager cm = new ThreadSafeClientConnManager(params,
                schemeRegistry);
         httpclient = new DefaultHttpClient(cm, params);

        // TODO: need to release this connection in httpRequest()
        // cm.releaseConnection(conn, validDuration, timeUnit);

        // Setup BasicAuth
        BasicScheme basicScheme = new BasicScheme();
        mAuthScope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT);

        // httpclient.setAuthSchemes(authRegistry);
        httpclient.setCredentialsProvider(new BasicCredentialsProvider());

        // Generate BASIC scheme object and stick it to the local
        // execution context
        BasicHttpContext localcontext = new BasicHttpContext();
        localcontext.setAttribute("preemptive-auth", basicScheme);

        // first request interceptor
        httpclient.addRequestInterceptor(preemptiveAuth, 0);
    }
	/**
     * HttpRequestInterceptor for DefaultHttpClient
     */
    private HttpRequestInterceptor preemptiveAuth = new HttpRequestInterceptor() {
        @Override
        public void process(final HttpRequest request, final HttpContext context) {
            AuthState authState = (AuthState) context
                    .getAttribute(ClientContext.TARGET_AUTH_STATE);
            CredentialsProvider credsProvider = (CredentialsProvider) context
                    .getAttribute(ClientContext.CREDS_PROVIDER);
            HttpHost targetHost = (HttpHost) context
                    .getAttribute(ExecutionContext.HTTP_TARGET_HOST);

            if (authState.getAuthScheme() == null) {
                AuthScope authScope = new AuthScope(targetHost.getHostName(),
                        targetHost.getPort());
                Credentials creds = credsProvider.getCredentials(authScope);
                if (creds != null) {
                    authState.setAuthScheme(new BasicScheme());
                    authState.setCredentials(creds);
                }
            }
        }
    };
    
    /**
     * Setup Credentials for HTTP Basic Auth
     *
     * @param username
     * @param password
     */
    public void setCredentials(String username, String password) {
        httpclient.getCredentialsProvider().setCredentials(mAuthScope,
                new UsernamePasswordCredentials(username, password));
    }
}
