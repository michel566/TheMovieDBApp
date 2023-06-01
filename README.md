# TheMovieDBApp

This application aims to list the movies from the well-known public api The Movie DB.

Interface:
  * Used the standard and clean interface of material design.
  * The navigation pattern between fragments was used for screen navigation.
  * Android Jetpack's Paging 3 was used and implemented for paging and infinite scrolling

Architecture:
  * For the development of this project and future expansions, MVVM + Clean is being used, which will also facilitate future unit tests in the project
  * The DI Engine was created through modules using the Dagger Hilt library which has the friendliest understanding of the Kotlin language

Storage:
  * For means of caching and simple data storage, Hawk 2 was used
  * For managing the storage of favorites, Room from Android Jetpack was used.

Tests:
* At the moment it was not possible to apply unit tests, but the intention is to implement JUnit 3 in the future, together with firebase deployment management and CI/CD with Bitrise
