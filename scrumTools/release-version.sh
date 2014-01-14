#!/bin/bash

VERSION_RELEASE=1.1.1-SNAPSHOT
VERSION_DEVELOPMENT=1.1.1-SNAPSHOT
REPO=../../mvn-repo

VERSION_CMD=org.codehaus.mojo:versions-maven-plugin:2.1:set

cd deploy
mvn $VERSION_CMD -DnewVersion=$VERSION_RELEASE
cd ..

mvn package
mvn source:jar

mvn install:install-file -Dfile=target/scrumTools-$VERSION_RELEASE.jar -Dsources=target/scrumTools-$VERSION_RELEASE-sources.jar -DpomFile=deploy/pom.xml -DlocalRepositoryPath=$REPO -DcreateChecksum=true

cd deploy
mvn $VERSION_CMD -DnewVersion=VERSION
cd ..

mvn $VERSION_CMD -DnewVersion=$VERSION_DEVELOPMENT

rm deploy/pom.xml.versionsBackup
rm pom.xml.versionsBackup

