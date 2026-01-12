# Java Log File Analyzer

A Core Java–based command-line tool to analyze server access logs, detect suspicious IP activity, and generate CSV reports.

## Features
- Analyze one or multiple log files
- Count HTTP status codes (200, 403, 404, 500)
- Track IP address activity
- Detect suspicious IPs based on failed request threshold
- Export analysis results to CSV
- Packaged as an executable JAR

## Tech Stack
- Java (Core Java)
- File I/O
- Collections Framework
- Command-line interface (CLI)

## Project Structure
```text
java-log-file-analyzer/
├── logs/
│   ├── access.log
│   └── access2.log
├── src/
│   └── LogAnalyzer.java
├── report.csv
├── manifest.txt
├── log-analyzer.jar
└── README.md
```

## How to Run

### Compile (optional)
```bash
javac src/LogAnalyzer.java
```

## Run using classpath
```bash
java -cp src LogAnalyzer logs/access.log logs/access2.log
```

## Run using executable JAR
```bash
java -jar log-analyzer.jar logs/access.log logs/access2.log
```

### Output
The tool provides a comprehensive analysis including:

* **Console Summary:**
    * **HTTP Status Code Counts:** Breakdown of 200, 403, 404, and 500 errors.
    * **IP Address Activity:** Total request counts per unique IP.
    * **Suspicious IPs:** Identification of potential threats based on failed request thresholds.
* **File Export:**
    * A detailed CSV report is automatically generated as `report.csv`.
