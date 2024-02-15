Fetching Data:
Utilizes coroutines for asynchronous network calls to fetch data from the provided URL.
Ensures clean and production-level networking code.
Displaying Data:
Implements a RecyclerView to display the list of countries.
Orders the countries by their position in the JSON.
Search filtered country by name or capital.
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
