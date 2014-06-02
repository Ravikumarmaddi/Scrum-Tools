#!/bin/bash

VERSION_RELEASE=1.2.0
VERSION_DEVELOPMENT=1.2.1-SNAPSHOT
REPO=../../mvn-repo

VERSION_CMD=org.codehaus.mojo:versions-maven-plugin:2.1:set

cd deploy
mvn $VERSION_CMD -DnewVersion=$VERSION_RELEASE
cd ..
mvn $VERSION_CMD -DnewVersion=$VERSION_RELEASE

mvn package
mvn source:jar

mvn install:install-file -Dfile=target/scrumTools-$VERSION_RELEASE.jar -Dsources=target/scrumTools-$VERSION_RELEASE-sources.jar -DpomFile=deploy/pom.xml -DlocalRepositoryPath=$REPO -DcreateChecksum=true

rm deploy/pom.xml.versionsBackup
rm pom.xml.versionsBackup

git add .
git commit -m "changes for version $VERSION_RELEASE"
echo "files to git added"

git tag -a v$VERSION_RELEASE -m "v$VERSION_RELEASE"
echo "git tag $VERSION_RELEASE created"

cd deploy
mvn $VERSION_CMD -DnewVersion=VERSION
cd ..
mvn $VERSION_CMD -DnewVersion=$VERSION_DEVELOPMENT

rm deploy/pom.xml.versionsBackup
rm pom.xml.versionsBackup

git add .
git commit -m "changes for development version $VERSION_DEVELOPMENT"

