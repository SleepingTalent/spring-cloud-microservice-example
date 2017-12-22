MODE 1000,1000

cd eureka-registration-service
start "eureka-service" mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=1111'
cd..

TIMEOUT /t 30

cd centralized-config-service
start "config-service" mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=2222'
cd..

TIMEOUT /t 30

cd customer-service
start "customer-service-1" mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=3333'
REM start "customer-service-2" mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=3334'
REM start "customer-service-3" mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=3335'
cd..

TIMEOUT /t 10

cd customer-web
start "customer-web-1" mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=4444'
REM start "customer-web-2" mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=4445'
cd..