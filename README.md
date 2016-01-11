GitHub link

https://github.com/VytasHub/WordCloud

External jar files used:

commons-validator-1.4.0.jar
jsoup-1.8.3.jar
junit-4.12.jar
org.eclipse.jdt.junit4.runtime_1.1.500.v20150423-0747.jar
org.hamcrest.core_1.3.0.v201303031735.jar


commons-validator validates the URL before processing it
jsoup connect to website and parses html data
junit-4, junit4.runtime, hamcrest used for test cases


wordcloud.jar can be ran throw command like using run.bat command which contains longer command listed below. Note (If you double click on wordcloud.jar it will open the GUI but wont parse the URL because jar are not referenced).

wordcloudExecutable.jar you can just double click on it and it will work fine; because the jar are inside.


Stopwords.txt needs to be in folder in order to run the program.

In order to run ant script lip folder needs to be there


###Run
####Executable
-  
-

####Command Line
```bash
> java -cp ".\wordcloud.jar;.\lib\jsoup-1.8.3.jar;.\lib\commons-validator-1.4.0.jar;.\lib\junit-4.12;C:\plugins\org.hamcrest.core_1.3.0.v201303031735.jar;.\lib\org.eclipse.jdt.junit4.runtime_1.1.500.v20150423-0747.jar" ie.gmit.sw.Runner
```
###Dependencies
-commons-validator-1.4.0.jar
-jsoup-1.8.3.jar
-junit-4.12.jar
-org.eclipse.jdt.junit4.runtime_1.1.500.v20150423-0747.jar
-org.hamcrest.core_1.3.0.v201303031735.jar
