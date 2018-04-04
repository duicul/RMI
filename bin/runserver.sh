#!/bin/bash
rmiregistry &
java -cp .:mysql-connector-java-5.1.46.jar -Djava.rmi.server.codebase=http://$1/RMI/bin/ -Djava.rmi.server.hostname=$1 -Djava.security.policy=server.policy MainRMI $1 $2 root ""
#./server.jar -cp .:server.jar -Djava.rmi.server.codebase=http://$1/RMI/server.jar -Djava.rmi.server.hostname=$1 -Djava.security.policy=server.policy $1 "Test" root ""
