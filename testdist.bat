del C:\tmp2
mkdir C:\tmp2
mkdir C:\tmp2\collected-files
copy .\target\monaide-0.1.0-SNAPSHOT-standalone.jar C:\tmp2
copy testfolderswin.txt C:\tmp2
cd C:\tmp2
java -jar monaide-0.1.0-SNAPSHOT-standalone.jar C:\tmp2\testfolderswin.txt > output.bat
 