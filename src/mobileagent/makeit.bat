set JAVA_HOME=C:\PROGRA~1\Java\jdk1.5.0_22
set AGLET_HOME=C:\aglets
del C:\aglets\public\mobileagent\GUI\*.class C:\aglets\public\mobileagent\bean\*.class C:\aglets\public\mobileagent\agent\*.class C:\aglets\public\mobileagent\library\*.class C:\aglets\public\mobileagent\render\*.class
set CLASSPATH=.
set CLASSPATH=%CLASSPATH%;%AGLET_HOME%\lib\aglets-2.0.2.jar;%CLASSPATH%;%AGLET_HOME%\lib\swing-layout-1.0.4.jar
%JAVA_HOME%\bin\javac -encoding utf8 -d %AGLET_HOME%\public -classpath %CLASSPATH% .\GUI\*.java .\bean\*.java .\agent\*.java .\library\*.java .\render\*.java