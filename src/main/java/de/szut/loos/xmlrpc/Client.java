package de.szut.loos.xmlrpc;

import lombok.extern.java.Log;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import static java.lang.System.out;

@Log
public class Client {
    public static void main(String args[]) {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
            config.setServerURL(new URL("http://127.0.0.1:8080/xmlrpc"));
        } catch (MalformedURLException e) {
            log.severe(e.getLocalizedMessage());
            log.log(Level.SEVERE, "Error", e);
        }
        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        Object[] params = new Object[]{33, 9};
        try {
            Integer result = (Integer) client.execute("Calculator.add", params);
            out.println(String.format("Result: %d", result) );
        } catch (XmlRpcException e) {
            log.severe(e.getLocalizedMessage());
            log.log(Level.SEVERE, "Error", e);
        }
    }
}
