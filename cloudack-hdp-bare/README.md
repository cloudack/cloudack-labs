Cloudack  HDP  Cluster
=========

cloudack virtual box hadoop cluster

-if you are a mac/linux user just call ./start.sh script which will spin the cluster
-if you are a windows user you can use "vagrant up" command to up all the boxes

if you want to suspend the VM's you can use "vagrant halt"

if you want to bring up the cluster again "vagrant up"

Installation:
1) Login to one of the machines and setup CDH manager
2) cd /etc/share/vm
3) chmod u+x cloudera-manager-installer.bin
4) sudo ./cloudera-manager-installer.bin
5) goto browser on your machine and login http://hostname:7180/
6) add the other hosts via the manager.
7) install the services as desired.


