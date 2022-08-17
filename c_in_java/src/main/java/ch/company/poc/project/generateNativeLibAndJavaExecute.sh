# Remember to set your JAVA_HOME env var

LIBMBUS_PATH=home/${USER}/learning/learnig_or_poc/c_in_java/src/main/java/ch/company/poc/libmbus
PROJECT_PATH=home/${USER}/learning/learnig_or_poc/c_in_java/src/main/java/ch/company/poc/project
CLASS_PATH=home/${USER}/learning/learnig_or_poc/c_in_java/src/main/java

clear
echo "====================== Compiling Java File ======================"
javac -h /${PROJECT_PATH}/ /${PROJECT_PATH}/MbusApplication.java

echo "====================== Compiling CPP File 1 ======================"

g++ -c -fPIC /${LIBMBUS_PATH}/mbus/mbus.c -o /${PROJECT_PATH}/mbus.o

echo "====================== Compiling CPP File 2 ======================"
g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -I/${LIBMBUS_PATH}/ -I/${LIBMBUS_PATH}/mbus -L/${LIBMBUS_PATH}/mbus /${PROJECT_PATH}/MbusApplication.cpp -o /${PROJECT_PATH}/ch_company_poc_project_MbusApplication.o

echo "====================== Compiling Library File ======================"
g++ -shared -fPIC -o /${PROJECT_PATH}/libnative.so /${PROJECT_PATH}/mbus.o /${PROJECT_PATH}/ch_company_poc_project_MbusApplication.o -lc

echo "====================== Debug Shared Library File ======================"
nm /${PROJECT_PATH}/libnative.so | grep "U "

echo "====================== Running Java File ======================"
java -cp /${CLASS_PATH} -Djava.library.path=/${PROJECT_PATH}/ ch/company/poc/project/MbusApplication