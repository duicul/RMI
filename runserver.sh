#!/bin/bash
#rmiregistry &
java -Djava.rmi.server.codebase=http://$1/RMI/serer.jar -Djava.rmi.server.hostname=$1 -Djava.security.policy=server.policy ComputeEngine $1 "Test" root ""
#./server.jar -cp .:server.jar -Djava.rmi.server.codebase=http://$1/RMI/server.jar -Djava.rmi.server.hostname=$1 -Djava.security.policy=server.policy $1 "Test" root ""
