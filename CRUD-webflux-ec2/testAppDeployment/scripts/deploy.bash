#!/bin/bash
echo "Starting deployment"
ANSIBLE_HOST_KEY_CHECKING=false ansible-playbook -i ansible/hosts.ini -v ansible/httpd.yml