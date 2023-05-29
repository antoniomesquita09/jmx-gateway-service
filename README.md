# JMX Gateway Serivce

Service responsible as a proxy to colect metrics from JMX pointing to a java application JMX port.

## Installation

Clone the project and install all dependencies.

## Getting started

```bash
git clone git@github.com:antoniomesquita09/dashboard-service.git
```
```bash
cd <project-directory>
```

## Running locally

1. Run the **MainFibonacci** located in `src/main/java/org/jmx/gateway/service/com/helper/MainFibonacci.java` from terminal or using your favorite IDE.

Ensure run **MainFibonacci** with the Virtual Machine options:
```bash
-Dcom.sun.management.jmxremote
-Dcom.sun.management.jmxremote.port=2578
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.ssl=false
```

2. Run the **Application** method located in `src/main/java/org/jmx/gateway/service/com/Application.java` from terminal or using your favorite IDE.