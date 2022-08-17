/* MyJNI.cpp : Defines the entry point for the DLL application. */
#include "com_cinjava_poc_five_HelloWorldJNI.h"
#include <stdio.h>
#include <iostream>
JNIEXPORT void JNICALL Java_com_cinjava_poc_five_HelloWorldJNI_sayHello (JNIEnv* env, jobject thisObject) {
    printf("Hello World from CPP");
}