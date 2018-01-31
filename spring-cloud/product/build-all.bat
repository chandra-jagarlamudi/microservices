pushd core\product-service &                call mvn clean install & popd
pushd core\recommendation-service &         call mvn clean install & popd
pushd core\review-service &                 call mvn clean install & popd
pushd composite\product-composite-service & call mvn clean compile & popd

pushd support\discovery-server &            call mvn clean compile & popd
pushd support\edge-server &                 call mvn clean compile & popd
