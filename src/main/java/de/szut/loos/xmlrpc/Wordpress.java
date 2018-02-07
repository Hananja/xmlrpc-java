package de.szut.loos.xmlrpc;


import lombok.experimental.var;
import lombok.extern.java.Log;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import static java.lang.System.out;

@Log
public class Wordpress {
    private static String user = "user";
    private static String password = "geheim";

    public static void main(String args[]) {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
            config.setServerURL(new URL("http://localhost:8000/wordpress/xmlrpc.php"));
        } catch (MalformedURLException e) {
            log.severe(e.getLocalizedMessage());
            log.log(Level.SEVERE, "Error", e);
        }
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        Object[] params = new Object[]{1, user, password};
        try {
            Object result = client.execute("wp.getPosts", params);
            Object posts[] = (Object[]) result;
            for( Object post: posts ) {
                out.println("Post:");
                Map<String, String> postMap = (Map<String, String>)post;
                for( String key: postMap.keySet()) {
                    out.println(String.format("  %s: %s", key, postMap.get(key)));
                }
            }
        } catch (XmlRpcException e) {
            log.severe(e.getLocalizedMessage());
            log.log(Level.SEVERE, "Error", e);
        }
    }
}
