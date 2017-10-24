# KotlinSample
Sample Android app written in [Kotlin](https://kotlinlang.org/docs/reference/android-overview.html).

### About HotelsApp
A simple app that loads hotels data. 

The app has two screens: the main screen for the list of hotels and a details screen for displaying the info about a specific hotel. 

Loaded data is also cached in a local database.

### Implementation
The app implements [Model-View-Presenter](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) pattern. 
The loading/caching of data is handled in a Repository.

### Used libraries
* [Dagger 2](https://google.github.io/dagger/android.html) for dependency injection.
* [RxAndroid](https://github.com/ReactiveX/RxAndroid) for performing asynchronous tasks.
* [Retrofit](http://square.github.io/retrofit/) for making HTTP requests to the server.
* [Room](https://developer.android.com/topic/libraries/architecture/room.html) for caching data in a local database.
* [Picasso](http://square.github.io/picasso/) for loading images.
