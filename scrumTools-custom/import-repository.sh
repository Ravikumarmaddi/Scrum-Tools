#!/bin/bash

mkdir ./githubrepo

wget https://github.com/kerli81/mvn-repo/archive/master.zip
unzip master.zip -d ./githubrepo
rm master.zip

rm ./githubrepo/mvn-repo-master/R*
cp -rf ./githubrepo/mvn-repo-master/* ~/.m2/repository

rm -rf ./githubrepo

