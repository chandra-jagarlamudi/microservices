pushd product\core\product-service &                call mvn clean install & popd
pushd product\core\recommendation-service &         call mvn clean install & popd
pushd product\core\review-service &                 call mvn clean install & popd
pushd product\composite\product-composite-service & call mvn clean install & popd

pushd product\support\discovery-server &            call mvn clean compile & popd
pushd product\support\edge-server &                 call mvn clean compile & popd