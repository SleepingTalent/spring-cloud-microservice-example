MODE 1000,1000

cd admin-dashboard
start "admin-dashboard" mvn spring-boot:run -Drun.jvmArguments="-Xmx128m -Dserver.port=3000"
cd..

TIMEOUT /t 30

cd eureka-service
start "eureka-service" mvn spring-boot:run -Drun.jvmArguments="-Xmx128m -Dserver.port=1000"
cd..

TIMEOUT /t 30

cd config-service
start "config-service" mvn spring-boot:run -Drun.jvmArguments="-Xmx128m -Dserver.port=1001"
cd..

TIMEOUT /t 30

cd auth-service
start "auth-service" mvn spring-boot:run -Drun.jvmArguments="-Xmx128m -Dserver.port=1002"
cd..

TIMEOUT /t 30

cd music-repository
start "music-repository" mvn spring-boot:run -Drun.jvmArguments="-Xmx128m -Dserver.port=2000"
cd..

TIMEOUT /t 30

cd music-service
start "music-service-1" mvn spring-boot:run -Drun.jvmArguments="-Xmx128m -Dserver.port=2100"
REM start "music-service-2" mvn spring-boot:run -Drun.jvmArguments="-Xmx128m -Dserver.port=2101"
REM start "music-service-3" mvn spring-boot:run -Drun.jvmArguments="-Xmx128m -Dserver.port=2102"
cd..

TIMEOUT /t 10

cd artist-service
start "artist-service-1" mvn spring-boot:run -Drun.jvmArguments="-Xmx128m -Dserver.port=2200"
REM start "artist-service-2" mvn spring-boot:run -Drun.jvmArguments="-Xmx128m -Dserver.port=2201"
REM start "artist-service-3" mvn spring-boot:run -Drun.jvmArguments="-Xmx128m -Dserver.port=2202"
cd..

TIMEOUT /t 10

cd zuul-api-gateway
start "zuul-api-gateway" mvn spring-boot:run -Drun.jvmArguments="-Xmx128m -Dserver.port=1003"
cd..

REM TIMEOUT /t 10

REM cd music-web
REM start "music-web-1" mvn spring-boot:run -Drun.jvmArguments="-Xmx128m -Dserver.port=2300"
REM start "music-web-2" mvn spring-boot:run -Drun.jvmArguments="-Xmx128m -Dserver.port=2301"
REM cd..
