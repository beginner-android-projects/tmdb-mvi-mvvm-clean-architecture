# The Movie Database Api
 
Simple Android application that displays Popular TV Shows and TV Show details from The Movie Database API. 

# Description

This project created with the aim to improve my android fundamentals. In this project, you can find switching between fragments with animations and also on the cutting-edge android application development.

# Design

This Application designed with single activity architecture principles with clean architecture principles. App has 3 screens. The first screen; shows all popular tv series from the remote data source. Favorites screen shows your favorite tv shows that coming from "Room" database and the last screen shows the selected specific tv series details from the remote data source.

# Built with

1. [ViewBinding](https://developer.android.com/topic/libraries/view-binding)
2. [Coroutines](https://developer.android.com/kotlin/coroutines?gclsrc=aw.ds&&gclid=Cj0KCQjw9_mDBhCGARIsAN3PaFPjDxN-1WVXVDQtNX3nunAVvneQSMWxa6rAMitkT80NqruaNrH15bcaApJLEALw_wcB)
3. [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
4. [MVVM](https://developer.android.com/jetpack/guide?gclsrc=aw.ds&&gclid=Cj0KCQjw9_mDBhCGARIsAN3PaFMDd7Q9FSHR3xMlTqenPwtrh02SDz6w3wMZeuJUzq9LbvnEpr-X4XQaAoOLEALw_wcB)
5. [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
6. [Room](https://developer.android.com/jetpack/androidx/releases/room?gclsrc=aw.ds&&gclid=Cj0KCQjw9_mDBhCGARIsAN3PaFMLKCMYAicAyUJZfINDpBhl1_519YPKTb5mGlcM0uLRLpY44_0nWpwaAqSREALw_wcB)
7. [OkHttp](https://square.github.io/okhttp/)
8. [Retrofit2](https://square.github.io/retrofit/)
9. [Glide](https://github.com/bumptech/glide )
10. [Moshi](https://github.com/square/moshi)
11. [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)


# Architecture

# Clean Architecture
![architecture](/images/clean.png)

# Layers Architecture
![architecture](/images/clean2.png)

# Data Layer: Repository
![datalayer](/images/clean3.png)

# USAGE

write your tmdb api key utils/config.kt
```kotlin
api=""
```
