# Description
The task involves fetching a list of countries from a provided URL in JSON format and displaying them in a RecyclerView.
The RecyclerView displays each country's name, region, code, and capital in a specific format.
The implementation focuses on robustness, error handling, and supporting device rotation.


Key Features
Fetching Data:
Utilizes coroutines for asynchronous network calls to fetch data from the provided URL.
Ensures clean and production-level networking code.
Displaying Data:
Implements a RecyclerView to display the list of countries.
Orders the countries by their position in the JSON.
Shows country information (name, region, code, capital) in a specified format within each RecyclerView item.
Robust Implementation:
Handles errors and edge cases gracefully, ensuring a smooth user experience.
Incorporates error handling mechanisms to deal with network failures or invalid data.
Supports device rotation without losing data or disrupting the UI state.
Architecture and Design Patterns:
Follows the MVVM (Model-View-ViewModel) architecture pattern for separation of concerns and maintainability.
Emphasizes clean architecture principles and uses appropriate design patterns to enhance code readability and scalability.
Technologies and Tools:
Language: Kotlin
Architecture: MVVM
Concurrency: Coroutines
Networking: Retrofit
Dependency Injection: No usage of Dagger
UI Toolkit: Android SDK (RecyclerView)
