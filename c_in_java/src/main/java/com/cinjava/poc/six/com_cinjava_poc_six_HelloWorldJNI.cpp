#include "com_cinjava_poc_six_HelloWorldJNI.h"
#include <iostream>

/*
 * Class:     com_cinjava_poc_six_HelloWorldJNI
 * Method:    sayHello
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_cinjava_poc_six_HelloWorldJNI_sayHello (JNIEnv* env, jobject thisObject) {
	std::string hello = "Hello from C++ !!";
    std::cout << hello << std::endl;
    return env->NewStringUTF(hello.c_str());
}