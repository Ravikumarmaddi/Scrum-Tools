#!/bin/bash

VERSION_RELEASE=1.1.1
VERSION_DEVELOPMENT=1.1.2-SNAPSHOT
REPO=/home/neo/ws-git/mvn-repo

mvn versions:set -DnewVersion=$VERSION_RELEASE

mvn package
mvn source:jar

mvn install:install-file -Dfile=target/scrumTools-$VERSION_RELEASE.jar -Dsources=target/scrumTools-$VERSION_RELEASE-sources.jar -DpomFile=pom.xml -DlocalRepositoryPath=$REPO

mvn versions:set -DnewVersion=$VERSION_DEVELOPMENT
