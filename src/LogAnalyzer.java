import java.io.*;
import java.util.*;

public class LogAnalyzer {

    public static void main(String[] args) {

        if (args.length == 0) {
    System.out.println("Usage: java LogAnalyzer <log_file_path>");
    return;
}

/*String logFile = args[0];*/

        Map<String, Integer> statusCount = new HashMap<>();
        Map<String, Integer> ipCount = new HashMap<>();
        Map<String, Integer> ip404Count = new HashMap<>();

       List<String> logFiles = Arrays.asList(args);

for (String logFile : logFiles) {

    try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {

        String line;
        while ((line = br.readLine()) != null) {

            String[] parts = line.split(" ");

            String ip = parts[0];
            String status = parts[parts.length - 1];

            statusCount.put(status, statusCount.getOrDefault(status, 0) + 1);
            ipCount.put(ip, ipCount.getOrDefault(ip, 0) + 1);

            if (status.equals("404")) {
                ip404Count.put(ip, ip404Count.getOrDefault(ip, 0) + 1);
            }
        }

    } catch (IOException e) {
        System.out.println("Error reading file: " + logFile);
    }
}


        // Output Results
        System.out.println("\n=== Status Code Count ===");
        statusCount.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("\n=== IP Address Count ===");
        ipCount.forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println("\n=== Suspicious IPs (404 > 2) ===");
       Map<String, Integer> suspiciousIPs = new HashMap<>();

ip404Count.forEach((k, v) -> {
    if (v > 2) {
        suspiciousIPs.put(k, v);
        System.out.println(k + " -> " + v + " failed requests");
    }
});

exportToCSV(statusCount, ipCount, suspiciousIPs);

    }
    @SuppressWarnings({ "unused", "unused" })
    private static void exportToCSV(
        Map<String, Integer> statusCount,
        Map<String, Integer> ipCount,
        Map<String, Integer> suspiciousIPs) {

    try (PrintWriter writer = new PrintWriter(new File("report.csv"))) {

        writer.println("=== Status Codes ===");
        writer.println("Status,Count");
        statusCount.forEach((k, v) -> writer.println(k + "," + v));

        writer.println("\n=== IP Addresses ===");
        writer.println("IP,Count");
        ipCount.forEach((k, v) -> writer.println(k + "," + v));

        writer.println("\n=== Suspicious IPs ===");
        writer.println("IP,404_Count");
        suspiciousIPs.forEach((k, v) -> writer.println(k + "," + v));

        System.out.println("\nCSV report generated: report.csv");

    } catch (Exception e) {
        System.out.println("Error writing CSV: " + e.getMessage());
    }
}

}
