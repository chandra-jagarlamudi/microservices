# Support servers
start /D product\support\discovery-server             mvn spring-boot:run
start /D product\support\edge-server                  mvn spring-boot:run

# Core Service
start /D product\core\product-service                 mvn spring-boot:run
start /D product\core\recommendation-service          mvn spring-boot:run
start /D product\core\review-service                  mvn spring-boot:run

# Composite Service
start /D product\composite\product-composite-service  mvn spring-boot:run 