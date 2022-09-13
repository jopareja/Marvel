package com.example.marvel.ui

object Learning {
    /** ARCHITECTURE PRINCIPLES
     *
     * ---Separation of Concerns---
     * + UI Classes should only contain logic that handles UI.
     *    (To avoid issues related to component lifecycle).
     *    (Activity and Fragment represent the contract between OS and your app).
     *    (OS can destroy them at any time based on User Interactions or Low Memory).
     *
     * ---Drive UI from Data Persistent Models---
     *    (These models are independent from the UI and other components).
     *    (User won`t lose data if the OS destroys your app to free up resources).
     *    (App keeps working when a network connection is not available).
     *
     * ---Single Source of Truth (viewModel)---
     * + When a new data type is defined in the app, assign a SSOT
     *    (The SSOT is the owner of the data, only the SSOT can modify it).
     *    (The SSOT exposes the data using an immutable type LIVEDATA)
     *    (Centralizes, Protects, and makes changes to data more traceable. Making bugs easier to spot)
     *
     * ---Unidirectional Data Flow---
     *     (State flows in only one direction).
     *     (The Events that modify the data flow go in the opposite direction).
     *     (A button pressed, is an Event that is triggered from a lower-scope UI, to a SSOT).
     */


    /** RECOMMENDED APP ARCHITECTURE
     *  ---THE UI LAYER---
     *     + UI ELEMENTS that render data on screen. (Activities).
     *         The UI notifies the ViewModel of user events.
     *         When consuming observable data holders, take the lifecycle of the UI into consideration.
     *         The UI should not be observing the state when the view isn’t being displayed to the user. (repeatOnLifecycle(Lifecycle.State.STARTED))
     *
     *     + STATE HOLDERS, hold data and expose it to UI. (ViewModels).
     *          The ViewModel handles the user actions and updates the state.
     *          A UI state object should handle states that are related to each other
     *
     *     + TYPES OF LOGIC
     *         Business Logic: product requirements for app data (in domain layer, never in UI layer).
     *         UI Logic: how to display state changes on the screen.
     *         UI logic that involves UI types like Context, should live in the UI, not in the ViewModel.
     *
     *
     *
     *  ---THE DOMAIN LAYER--- simplify and reuse interactions between UI and DATA.
     *     + encapsulates business logic that is reused by multiple ViewModels
     *     + USE CASES. each of them should have responsibility over a single functionality
     *
     *
     *
     *  ---THE DATA LAYER--- contains the logic of app and exposes app data.
     *     + business logic. Rules of how your app creates, stores, and changes data. Gives value to your App.
     *     Calling data from repositories and data sources should be MAIN SAFE.
     *
     *     + REPOSITORIES (You should create a repository class for each diff type of data).
     *                    (Exposing data to the rest of the app).
     *                    (Resolving conflicts between multiple data sources).
     *                    (Abstracting sources of data from the rest of the app).
     *                    for Example: MoviesRepository for data related to movies,
     *                    PaymentsRepository for payments data.
     *
     *
     *     + DATA SOURCES  Responsible for providing data that app needs to function.
     *                    (Each data source class works with only one source of data).
     *                        "LocalUsersDataSource" or "RemoteMoviesDataSource".
     *                    (It can be a network source or a local database).
     *
     */
}