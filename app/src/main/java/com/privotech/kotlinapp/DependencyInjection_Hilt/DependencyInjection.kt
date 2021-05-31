package com.privotech.kotlinapp.DependencyInjection_Hilt

import android.content.Context
import android.os.Handler
import com.privotech.kotlinapp.Classes.Preferences
import com.privotech.kotlinapp.Classes.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext


@Module
@InstallIn(ActivityComponent::class)
class ActivityInjection{

    @Provides
    fun getHandler(@ActivityContext context : Context) : Handler{

        return Handler(context.mainLooper)

    }

    @Provides
    fun getPref(@ActivityContext context : Context) : Preferences {

        return Preferences(context)

    }

    @Provides
    fun getUser(@ActivityContext context : Context) : User {

        return User(context)

    }

}


@Module
@InstallIn(FragmentComponent::class)
class FragmentInjection{

}