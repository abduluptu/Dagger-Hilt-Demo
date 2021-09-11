package com.sohainfotech.daggerhiltdemo

import android.app.Application
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp

//Step 01.
//Now, to begin working with Dagger we need to annotate the application class with @HiltAndroidApp.
//It generates all the component classes which we have to do manually while using Dagger.

@HiltAndroidApp
class App : Application(){

}