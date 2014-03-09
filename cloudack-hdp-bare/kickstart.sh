#!/bin/bash         
sudo su
yum clean all
yum update
yum -y install java-1.7.0-openjdk.x86_64
mkdir /data
mkdir /data/nn
mkdir /data/dd
chown -R vagrant:vagrant /data/nn
chown -R vagrant:vagrant /data/dd
service iptables stop
chkconfig iptables off
sudo su - vagrant
