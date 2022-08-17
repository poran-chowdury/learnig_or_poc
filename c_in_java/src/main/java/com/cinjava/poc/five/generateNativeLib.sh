# Create the header with javac -h . ClassName.java
# Remember to set your JAVA_HOME env var
g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux com_cinjava_poc_five_HelloWorldJNI.cpp -o com_cinjava_poc_five_HelloWorldJNI.o
