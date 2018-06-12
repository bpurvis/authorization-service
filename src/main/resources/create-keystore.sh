#!/bin/bash

keytool -genkey -alias authorization-service.jks -keyalg RSA -keystore authorization-service.jks -dname "CN=Brian Purvis, OU=bpuvris.com, O=bpurvis.com, L=Atlanta, S=Georgia, C=US" -storepass password -keypass password
