import java.util.HashMap;

class ARP {
    private HashMap<String, String> arpTable;

    public ARP() {
        arpTable = new HashMap<>();
    }

    // ARP Resolution
    public String resolve(String ipAddress) {
        if (arpTable.containsKey(ipAddress)) {
            return arpTable.get(ipAddress);
        } else {
            return "IP address not found in ARP table";
        }
    }

    // ARP Entry
    public void addEntry(String ipAddress, String macAddress) {
        arpTable.put(ipAddress, macAddress);
    }
}

class RARP {
    private HashMap<String, String> rarpTable;

    public RARP() {
        rarpTable = new HashMap<>();
    }

    // RARP Resolution
    public String resolve(String macAddress) {
        for (String ipAddress : rarpTable.keySet()) {
            if (rarpTable.get(ipAddress).equals(macAddress)) {
                return ipAddress;
            }
        }
        return "MAC address not found in RARP table";
    }

    // RARP Entry
    public void addEntry(String macAddress, String ipAddress) {
        rarpTable.put(ipAddress, macAddress);
    }
}

public class ARP_RARP_Main {
    public static void main(String[] args) {
        ARP arp = new ARP();
        RARP rarp = new RARP();

        // Add ARP entries
        arp.addEntry("192.168.1.1", "00:0a:95:9d:68:16");
        arp.addEntry("192.168.1.2", "00:1b:44:11:3a:b7");
        arp.addEntry("192.168.1.3", "08:00:27:eb:9e:0c");

        // Add RARP entries
        rarp.addEntry("00:0a:95:9d:68:16", "192.168.1.1");
        rarp.addEntry("00:1b:44:11:3a:b7", "192.168.1.2");
        rarp.addEntry("08:00:27:eb:9e:0c", "192.168.1.3");

        // Resolve ARP and RARP
        System.out.println("ARP resolution for 192.168.1.2: " + arp.resolve("192.168.1.2"));
        System.out.println("RARP resolution for 00:1b:44:11:3a:b7: " + rarp.resolve("00:1b:44:11:3a:b7"));
    }
}
