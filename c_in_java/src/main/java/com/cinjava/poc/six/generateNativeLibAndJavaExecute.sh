# Create the header with javac -h . ClassName.java
# Remember to set your JAVA_HOME env var

clear
echo "Compiling Java File"
javac -h . HelloWorldJNI.java

echo "Compiling C File"
g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux com_cinjava_poc_six_HelloWorldJNI.cpp -o com_cinjava_poc_six_HelloWorldJNI.o

echo "Compiling Library File"
g++ -shared -fPIC -o libnative.so com_cinjava_poc_six_HelloWorldJNI.o -lc

echo "Running Java File"
java -cp ../../../../ -Djava.library.path=/home/${USER}/learning/learnig_or_poc/c_in_java/src/main/java/com/cinjava/poc/six/ com/cinjava/poc/six/HelloWorldJNI