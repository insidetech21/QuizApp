**Quiz App**

An engaging and interactive Quiz App built using Java, following the MVVM architecture, and leveraging Firebase Firestore as the backend for seamless data management. The app features smooth UI interactions, offline functionality, and real-time data updates, offering users an exceptional quiz experience.

**----Features----**

**Multiple Choice Questions:** Users can answer multiple-choice questions, with their responses being validated and recorded.

**Firebase Integration:** The app uses Firebase Firestore as the backend for storing quiz questions, options, and answers.

**UI Components:** The app uses RecyclerView to display the questions and options in a scrollable view.

**MVVM Architecture:** The app follows the Model-View-ViewModel (MVVM) design pattern for better separation of concerns and improved code maintainability.

**Animations:** Smooth animations for transitions between questions and answers.

**LiveData:** The app uses LiveData to observe changes in the data and update the UI in real time.

**-----Technologies Used----**

**Programming Language:** Java

**Architecture:** MVVM (Model-View-ViewModel)

**UI Components:** RecyclerView, CardView, Button, Material Components.

**Backend:** Firebase Firestore

**State Management:** LiveData

**Offline Functionality:** Firestore's offline caching

**Additional Features:** Animations, color-coded feedback for correct/incorrect answers

**----Steps to Run----**

1. Clone this repository:
2. Open the project in Android Studio.
3. Add your Google Services JSON file for Firebase integration.
4. Sync Gradle and run the app on an emulator or physical device.

**----Future Enhancements----**

Add user authentication with Firebase Authentication.

Introduce difficulty levels for quizzes.

Implement Jetpack Compose for a more modern UI approach.

Include progress tracking and leaderboards.
