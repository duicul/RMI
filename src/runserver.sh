#!/bin/bash
rmiregistry &
java -Djava.rmi.server.codebase=http://$1/RMI/src/ -Djava.rmi.server.hostname=$1 -Djava.security.policy=server.policy ComputeEngine $1 root "";

