# Remember to set your JAVA_HOME env var

PROJECT_PATH=home/${USER}/learning/learnig_or_poc/c_in_java/src/main/java/ch/company/poc/project
CLASS_PATH=home/${USER}/learning/learnig_or_poc/c_in_java/src/main/java

clear
echo "====================== Compiling Java File ======================"
javac -h /${PROJECT_PATH}/ /${PROJECT_PATH}/MbusApplication.java

echo "====================== Compiling CPP File ======================"
g++ --static -c -Wall -g -DONE=1 -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux /${PROJECT_PATH}/MbusApplication.cpp -o /${PROJECT_PATH}/ch_company_poc_project_MbusApplication.o

echo "====================== Compiling Library File ======================"
g++ -shared -fPIC /${PROJECT_PATH}/ch_company_poc_project_MbusApplication.o -o /${PROJECT_PATH}/libnative.so -lc -lm -L/${PROJECT_PATH}/ -lmbus

echo "====================== Running Java File ======================"
java -cp /${CLASS_PATH} ch/company/poc/project/MbusApplication