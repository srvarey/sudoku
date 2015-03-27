--------------------------------------------------------------------------------
UNIX:
--------------------------------------------------------------------------------
In top level dir where pom.xml is located

 create runnable jar
mvn package


Copy the batch file src/main/resources/validate.bat and target/sudoku-1.0-SNAPSHOT.jar to the directory where your test input file is
cd to that directory
Enter validate.bat <sudoku input file>
There are 2 test files "goodfile.txt" and "badfile.txt" in src/main/resources




--------------------------------------------------------------------------------
WINDOWS:
--------------------------------------------------------------------------------
In top level dir where pom.xml is located

 create runnable jar
mvn package


Copy the batch file src/main/resources/validate.win.bat and target/sudoku-1.0-SNAPSHOT.jar to the directory where your test input file is
cd to that directory
Enter validate.win.bat <sudoku input file>
There are 2 test files "goodfile.txt" and "badfile.txt" in src/main/resources

