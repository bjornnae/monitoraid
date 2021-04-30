# monaide

A Clojure library designed to clean up among log files.

## Usage
1. Make a configuration file (dir-file) listing the folders that you want to monitor on separate rows.
Each line in the dir-file must contain exactly the following three properties separated with a single space:
    
    <path-to-dir> <maximum-file-age-days> <file-name-mask>

where file-name-mask is a regexp in the java pattern standard.

example:

    /absolute/path/to/logdir 30 \w*\.log
    
Note that even if you are on a windows platform the file path must use forward slash as directory separator.

2. Make a directory named "collected-files" in the same directory that the dir-file is placed.

3. Run the Jar file like this:

    $java -jar monaide.jar <absolute-path-to-dir-file> > movescript.bat

This will parse the dir-file and for each line check all files under the directory and mark old files for move. The output will be a windows batch file.

4. When the resulting windows batch file is run the log files found to be too old will be moved to the folder named collected-files.

From there you can choose whether you want to delete them forever or to archive them.

## Improvement suggestions
* The output format is now hard coded. You should be able to configure it in a separate config file. Currently a linux shell script (testdist-linux.sh) can convert the windows batch file to a unix shell script file. This script can be used as a basis for improvement in the output area.

## License

Copyright © 2021 bjornn

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
