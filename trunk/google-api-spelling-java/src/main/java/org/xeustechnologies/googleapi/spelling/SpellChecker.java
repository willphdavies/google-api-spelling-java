/**
 * Copyright 2009 Xeus Technologies 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 * 
 */

package org.xeustechnologies.googleapi.spelling;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.log4j.Logger;

/**
 * This class calls the Google's spell check service
 * 
 * @author Kamran Zafar
 * 
 */
public class SpellChecker {

    protected Configuration config;

    protected Language language;
    protected boolean overHttps;
    protected static final String GOOGLE_SC_URI = "www.google.com/tbproxy/spell";
    protected static Logger logger = Logger.getLogger( SpellChecker.class );

    public SpellChecker() {
        language = Language.ENGLISH;
    }

    public SpellChecker(Configuration config) {
        this.config = config;
        language = Language.ENGLISH;
    }

    /**
     * Checks the spellings of the text passed as argument
     * 
     * @param String
     *            text
     * @return SpellResponse
     */
    public SpellResponse check(String text) {
        return check( new SpellRequest( text ) );
    }

    /**
     * Checks the spellings of the text as part of the request. This method is
     * used to pass extra options in the request
     * 
     * @param SpellRequest
     *            request
     * @return SpellResponse
     */
    public SpellResponse check(SpellRequest request) {
        String uri = buildUri();

        try {
            HttpClient client = buildHttpClient( config );
            HttpPost post = new HttpPost( uri );

            byte[] requestData = marshall( request );

            if( logger.isDebugEnabled() )
                logger.debug( new String( requestData ) );

            InputStreamEntity ise = new InputStreamEntity( new ByteArrayInputStream( requestData ), -1 );
            ise.setContentType( "text/xml" );

            post.setEntity( ise );

            HttpResponse response = client.execute( post );
            HttpEntity entity = response.getEntity();

            int c = 0;
            StringBuffer buff = new StringBuffer();
            BufferedInputStream bis = new BufferedInputStream( entity.getContent() );

            while(( c = bis.read() ) != -1) {
                buff.append( (char) c );
            }

            bis.close();

            if( logger.isDebugEnabled() )
                logger.debug( buff );

            client.getConnectionManager().shutdown();

            return unmarshall( buff.toString().getBytes() );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private byte[] marshall(SpellRequest request) throws JAXBException, IOException {
        JAXBContext requestContext = JAXBContext.newInstance( SpellRequest.class );
        Marshaller m = requestContext.createMarshaller();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        m.marshal( request, bos );

        byte[] rb = bos.toByteArray();

        bos.close();

        return rb;
    }

    private SpellResponse unmarshall(byte[] response) throws JAXBException {
        JAXBContext responseContext = JAXBContext.newInstance( SpellResponse.class );
        Unmarshaller um = responseContext.createUnmarshaller();

        return (SpellResponse) um.unmarshal( new ByteArrayInputStream( response ) );
    }

    private HttpClient buildHttpClient(Configuration config) {
        SchemeRegistry supportedSchemes = new SchemeRegistry();
        supportedSchemes.register( new Scheme( "http", PlainSocketFactory.getSocketFactory(), 80 ) );
        supportedSchemes.register( new Scheme( "https", SSLSocketFactory.getSocketFactory(), 443 ) );

        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion( params, HttpVersion.HTTP_1_1 );
        HttpProtocolParams.setContentCharset( params, "UTF-8" );
        HttpProtocolParams.setUseExpectContinue( params, true );

        ClientConnectionManager ccm = new ThreadSafeClientConnManager( params, supportedSchemes );
        HttpClient client = new DefaultHttpClient( ccm, params );

        if( config.isProxyEnabled() ) {
            HttpHost proxy = new HttpHost( config.getProxyHost(), config.getProxyPort(), config.getProxyScheme() );
            client.getParams().setParameter( ConnRoutePNames.DEFAULT_PROXY, proxy );
        }

        return client;
    }

    private String buildUri() {
        StringBuffer uri = new StringBuffer();

        if( overHttps )
            uri.append( "https://" );
        else
            uri.append( "http://" );

        uri.append( GOOGLE_SC_URI );

        uri.append( "?lang=" + language.code() + "&hl=" + language.code() );

        return uri.toString();
    }

    public SpellChecker(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public boolean isOverHttps() {
        return overHttps;
    }

    public void setOverHttps(boolean overHttps) {
        this.overHttps = overHttps;
    }
}
