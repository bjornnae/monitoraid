# monaide

A Clojure library designed to clean up among log files.

## Usage
List the directories containing log files that you wish to remove in a file (dir-file) below.
Each line in the dir-file must contain exactly the following:
    
    <path to dir> <maximum file age>

example:

    /absolute/path/to/logdir 30

Make a directory named "collected-files" in the same directory that the dir-file is placed.

Run the Jar file like this:

    $java -jar monaide.jar <absolute-path-to-dir-file> > movescript.bat

This will parse the dir-file and for each line check all files under the directory and mark old files for move. The output will be a windows batch file.

When the resulting windows batch file is run the log files found to be too old will be moved to the folder named collected-files.

From there you can choose whether you want to delete them forever or to archive them.

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
