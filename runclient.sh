#!/bin/bash
java -Djava.rmi.server.codebase=http://$1/~duicul/RMI/ -Djava.security.policy=client.policy ComputeVal $1
