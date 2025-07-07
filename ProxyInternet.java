interface Internet {
    void connectTo(String serverHost) throws Exception;
}

class RealInternet implements Internet {
    public void connectTo(String serverHost) {
        System.out.println("Connecting to " + serverHost);
    }
}

class ProxyInternet implements Internet {
    private RealInternet realInternet = new RealInternet();
    private static java.util.Set<String> bannedSites = new java.util.HashSet<>();
    private String userRole;

    static {
        bannedSites.add("banned.com");
    }

    public ProxyInternet(String userRole) {
        this.userRole = userRole;
    }

    public void connectTo(String serverHost) throws Exception {
        if (userRole.equals("admin") || !bannedSites.contains(serverHost)) {
            realInternet.connectTo(serverHost);
        } else {
            throw new Exception("Access Denied to " + serverHost);
        }
    }
}
