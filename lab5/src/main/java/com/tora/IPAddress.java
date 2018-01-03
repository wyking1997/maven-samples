package com.tora;

public class IPAddress {

    private int port;
    private String host;

    public IPAddress(String host, int port) {
        this.port = port;
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IPAddress ipAddress = (IPAddress) o;

        if (port != ipAddress.port) return false;
        return host != null ? host.equals(ipAddress.host) : ipAddress.host == null;
    }

    @Override
    public int hashCode() {
        int result = port;
        result = 31 * result + (host != null ? host.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return host + ":" + port;
    }
}
