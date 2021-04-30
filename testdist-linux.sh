#!/bin/zsh
lein uberjar
rm -rf tmp
mkdir tmp
targetfilename=monaid-fileage.jar
cp target/monaide-0.1.0-SNAPSHOT-standalone.jar tmp/$targetfilename
cp testfolders-linux.txt tmp/
cd tmp
mkdir collected-files
currentdir=`pwd`
#echo Current dir is $currentdir
java -jar $targetfilename $currentdir/testfolders-linux.txt > removeolds.bat
# Below are linux extras for converting the resulting windows bat file to a proper linux shell script.
echo '#!/bin/env sh' > copyolds.sh
cat removeolds.bat >> copyolds.sh
sed -i 's/REM/#/g' copyolds.sh
sed -i 's/move/cp/g' copyolds.sh
cat copyolds.sh
#echo COMPLETE.
