package elastic.searchguard;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.floragunn.searchguard.ssl.SearchGuardSSLPlugin;
import com.floragunn.searchguard.ssl.util.SSLConfigConstants;

@SuppressWarnings("unused")
public class EsCall {
	
	private static String clusterName = "SearchGuardTest";
	private static int port = 9300;
	private static String hostName = "localhost";   
    public static Client client = null;

    @SuppressWarnings({ "resource", "unchecked" })
	public static Client getConnection() throws Exception{
         				if (client == null) {
       Settings settingsBuilder = Settings.builder().put("cluster.name", clusterName)
     		.put("path.home", ".")
     		.put(SSLConfigConstants.SEARCHGUARD_SSL_TRANSPORT_ENABLED, true)
        	.put(SSLConfigConstants.SEARCHGUARD_SSL_TRANSPORT_KEYSTORE_FILEPATH, 
        			"C:/Users/ssamnaboina/Desktop/ELK Stack/elasticsearch-5.1.2/config/CN=sgadmin-keystore.jks")
        	.put(SSLConfigConstants.SEARCHGUARD_SSL_TRANSPORT_TRUSTSTORE_FILEPATH, 
        			"C:/Users/ssamnaboina/Desktop/ELK Stack/elasticsearch-5.1.2/config/truststore.jks")
        	.put(SSLConfigConstants.SEARCHGUARD_SSL_TRANSPORT_KEYSTORE_PASSWORD, "15b47af5872c169efd8d")
        	.put(SSLConfigConstants.SEARCHGUARD_SSL_TRANSPORT_TRUSTSTORE_PASSWORD, "7f22fac1e4b4be34db99")
        	.put(SSLConfigConstants.SEARCHGUARD_SSL_TRANSPORT_ENFORCE_HOSTNAME_VERIFICATION, false)
        	.build();
			       
   Client client = new PreBuiltTransportClient(settingsBuilder,SearchGuardSSLPlugin.class).
		   addTransportAddress(new InetSocketTransportAddress
		   (InetAddress.getByName("localhost"),9300));
    }
		return client; 
    }
    public static String getClusterName() {
         return clusterName;
    }

    public static void setClusterName(String clusterName) {
         EsCall.clusterName = clusterName;
    }

    public static int getPort() {
         return port;
    }

    public static void setPort(int port) {
    	EsCall.port = port;
    }
   public static void closeConnection() {
         client.close();
    }
   
}
