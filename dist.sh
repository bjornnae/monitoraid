#!/bin/zsh
lein uberjar
rm -rf tmp
mkdir tmp
targetfilename=monaid-fileage.jar
cp target/monaide-0.1.0-SNAPSHOT-standalone.jar tmp/$targetfilename
cp testfolders.txt tmp/
cd tmp
currentdir=`pwd`
echo REM Current dir is $currentdir
java -jar $currentfilename $currentdir/testfolders.txt > removeolds.bat
cat removeolds.bat
echo COMPLETE.
